package tfar.egghunt.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import tfar.egghunt.block.EasterEggBlock;

public class ModBlocks {

    private static final BlockBehaviour.Properties egg_properties = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(-1, 3600000)
            .noOcclusion()
            .noLootTable();
    public static final Block EASTER_EGG1_1 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG1_2 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG1_3 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG1_4 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG1_5 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG2_1 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG2_2 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG2_3 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG2_4 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG2_5 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG3_1 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG3_2 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG3_3 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG3_4 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG3_5 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG4_1 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG4_2 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG4_3 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG4_4 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG4_5 = new EasterEggBlock(egg_properties);
    public static final Block EASTER_EGG5 = new EasterEggBlock(egg_properties);
}
