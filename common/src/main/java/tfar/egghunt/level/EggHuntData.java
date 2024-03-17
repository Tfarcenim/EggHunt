package tfar.egghunt.level;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.bossevents.CustomBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.saveddata.SavedData;
import tfar.egghunt.EggHunt;
import tfar.egghunt.platform.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EggHuntData extends SavedData {

    private final CustomBossEvent customBossEvent = new CustomBossEvent(new ResourceLocation(EggHunt.MOD_ID,"egg_hunt"), Component.literal("Egg Hunt"));
    private int total_time;
    private int elapsed;
    Map<UUID,PlayerEggHuntData> playerData = new HashMap<>();
    private boolean active;

    @Override
    public CompoundTag save(CompoundTag var1) {
        var1.putBoolean("active",active);
        var1.putInt("total_time",total_time);
        var1.putInt("elapsed",elapsed);

        ListTag listTag = new ListTag();
        for(Map.Entry<UUID,PlayerEggHuntData> entry : playerData.entrySet()) {
            CompoundTag tag = new CompoundTag();
            tag.putUUID("uuid",entry.getKey());
            tag.put("player_data",entry.getValue().save());
            listTag.add(tag);
        }
        var1.put("player_datas",listTag);

        return var1;
    }

    public void load(CompoundTag tag) {
        active = tag.getBoolean("active");
        total_time = tag.getInt("total_time");
        elapsed = tag.getInt("elapsed");

        ListTag posTag = tag.getList("player_datas",Tag.TAG_COMPOUND);
        for (Tag tag1 : posTag) {
            CompoundTag compoundTag = (CompoundTag) tag1;
            UUID uuid = compoundTag.getUUID("uuid");
            PlayerEggHuntData playerEggHuntData = PlayerEggHuntData.load(compoundTag.getCompound("player_data"));
            playerData.put(uuid,playerEggHuntData);
        }

    }

    public boolean isActive() {
        return active;
    }

    public int foundEggs(ServerPlayer player) {
        PlayerEggHuntData playerEggHuntData = playerData.get(player.getUUID());
        return playerEggHuntData == null ? 0 : playerEggHuntData.getFound();
    }

    public void tick(ServerLevel level) {
        if (active) {
            elapsed++;
            customBossEvent.setValue(total_time - elapsed);
            if (elapsed >= total_time) {
                end(level);
            }
            setDirty();
        }
    }

    public boolean discover(ServerPlayer player,BlockPos pos) {
        PlayerEggHuntData playerEggHuntData = playerData.get(player.getUUID());

        if (playerEggHuntData != null) {
            boolean discover = playerEggHuntData.discover(pos);
            if (discover) {
                Services.PLATFORM.refreshTabListName(player);
                setDirty();
            }
            return discover;
        } else {
            return false;
        }
    }

    public void end(ServerLevel level) {
        active = false;
        customBossEvent.removeAllPlayers();
        for (Map.Entry<UUID, PlayerEggHuntData> entry : playerData.entrySet()) {
            UUID uuid = entry.getKey();
            PlayerEggHuntData playerEggHuntData = entry.getValue();
            ServerPlayer player = level.getServer().getPlayerList().getPlayer(uuid);
            if (player != null) {
                BlockPos pos = playerEggHuntData.getOriginalPos();
                player.teleportTo(level,pos.getX(),pos.getY(),pos.getZ(),player.getYRot(),player.getXRot());
            }
        }
        playerData.clear();
    }

    public void start(int ticks, BlockPos teleportTo, ServerLevel level) {
        active = true;
        elapsed = 0;
        total_time = ticks;
        customBossEvent.setMax(total_time);
        customBossEvent.setValue(total_time);

        List<ServerPlayer> players = level.players();

        for (ServerPlayer serverPlayer : players) {
            playerData.put(serverPlayer.getUUID(),new PlayerEggHuntData(serverPlayer.blockPosition()));
            serverPlayer.teleportTo(level,teleportTo.getX(),teleportTo.getY(),teleportTo.getZ(),serverPlayer.getYRot(),serverPlayer.getXRot());
            customBossEvent.addPlayer(serverPlayer);
            Services.PLATFORM.refreshTabListName(serverPlayer);
        }

        setDirty();

    }

    public static EggHuntData loadStatic(CompoundTag compoundTag) {
        EggHuntData id = new EggHuntData();
        id.load(compoundTag);
        return id;
    }

    public static EggHuntData getInstance(ServerLevel serverLevel) {
        return serverLevel.getDataStorage()
                .computeIfAbsent(EggHuntData::loadStatic, EggHuntData::new, "deferredevents");
    }

}
