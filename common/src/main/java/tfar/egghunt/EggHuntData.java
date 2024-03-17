package tfar.egghunt;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EggHuntData extends SavedData {

    private final CustomBossEvent customBossEvent = new CustomBossEvent(new ResourceLocation(EggHunt.MOD_ID,"egg_hunt"), Component.literal("Egg Hunt"));
    private int total_time;
    private int elapsed;
    Map<UUID, BlockPos> originalPos = new HashMap<>();
    private boolean active;

    @Override
    public CompoundTag save(CompoundTag var1) {
        return var1;
    }

    public void load(CompoundTag tag) {
        active = tag.getBoolean("active");
        total_time = tag.getInt("total_time");
        elapsed = tag.getInt("elapsed");

        ListTag posTag = tag.getList("original_pos",Tag.TAG_COMPOUND);
        for (Tag tag1 : posTag) {
            CompoundTag compoundTag = (CompoundTag) tag1;
            UUID uuid = compoundTag.getUUID("uuid");
            int[] pos = compoundTag.getIntArray("pos");
            originalPos.put(uuid,new BlockPos(pos[0],pos[1],pos[2]));
        }

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

    public void end(ServerLevel level) {
        active = false;
        customBossEvent.removeAllPlayers();
        for (Map.Entry<UUID,BlockPos> entry : originalPos.entrySet()) {
            UUID uuid = entry.getKey();
            BlockPos pos = entry.getValue();
            ServerPlayer player = level.getServer().getPlayerList().getPlayer(uuid);
            if (player != null) {
                player.teleportTo(level,pos.getX(),pos.getY(),pos.getZ(),player.getYRot(),player.getXRot());
            }
        }
    }

    public void start(int ticks, BlockPos teleportTo, ServerLevel level) {
        active = true;
        elapsed = 0;
        total_time = ticks;
        customBossEvent.setMax(total_time);
        customBossEvent.setValue(total_time);

        List<ServerPlayer> players = level.players();

        for (ServerPlayer serverPlayer : players) {
            originalPos.put(serverPlayer.getUUID(),serverPlayer.blockPosition());
            serverPlayer.teleportTo(level,teleportTo.getX(),teleportTo.getY(),teleportTo.getZ(),serverPlayer.getYRot(),serverPlayer.getXRot());
            customBossEvent.addPlayer(serverPlayer);
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
