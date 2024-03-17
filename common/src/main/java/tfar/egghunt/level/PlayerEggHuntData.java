package tfar.egghunt.level;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import tfar.egghunt.EggHunt;

import java.util.HashSet;
import java.util.Set;

public class PlayerEggHuntData {
    private final BlockPos originalPos;

    private final Set<BlockPos> foundEggs;

    public PlayerEggHuntData(BlockPos original) {
        this(original,new HashSet<>());
    }

    public PlayerEggHuntData(BlockPos original,Set<BlockPos> foundEggs) {
        this.originalPos = original;
        this.foundEggs = foundEggs;
    }

    public BlockPos getOriginalPos() {
        return originalPos;
    }

    public boolean discover(BlockPos pos) {
        return foundEggs.add(pos);
    }

    public CompoundTag save() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putIntArray("original_pos", EggHunt.from(originalPos));
        ListTag listTag = new ListTag();
        for (BlockPos pos : foundEggs) {
            listTag.add(new IntArrayTag(EggHunt.from(pos)));
        }
        compoundTag.put("found",listTag);
        return compoundTag;
    }

    public static PlayerEggHuntData load(CompoundTag tag) {
        BlockPos original_pos = EggHunt.to(tag.getIntArray("original_pos"));
        ListTag foundTag = tag.getList("found", Tag.TAG_INT_ARRAY);
        Set<BlockPos> found = new HashSet<>();
        for (Tag tag1 : foundTag) {
            IntArrayTag intArrayTag = (IntArrayTag) tag1;
            found.add(EggHunt.to(intArrayTag.getAsIntArray()));
        }
        return new PlayerEggHuntData(original_pos,found);
    }

}
