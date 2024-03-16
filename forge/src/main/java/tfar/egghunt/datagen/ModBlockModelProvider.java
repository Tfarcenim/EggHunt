package tfar.egghunt.datagen;

import com.google.gson.JsonElement;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import tfar.egghunt.EggHunt;
import tfar.egghunt.init.ModBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class ModBlockModelProvider extends BlockModelGenerators {

    public ModBlockModelProvider(Consumer<BlockStateGenerator> pBlockStateOutput, BiConsumer<ResourceLocation, Supplier<JsonElement>> pModelOutput, Consumer<Item> pSkippedAutoModelsOutput) {
        super(pBlockStateOutput, pModelOutput, pSkippedAutoModelsOutput);
    }



    @Override
    public void run() {
        skipAutoItemBlock(ModBlocks.EASTER_EGG);
        createTrivialBlock1(ModBlocks.EASTER_EGG);
    }

    private static final int variants = 4;
    private static final int sub_variants = 5;

    public void createTrivialBlock1(Block pBlock) {
        //TexturedModel.CUBE.create(pBlock, this.modelOutput);

        ResourceLocation resourcelocation = new ResourceLocation(EggHunt.MOD_ID,"block/easter_egg");
        ModelTemplate template = create(resourcelocation,TextureSlot.ALL,TextureSlot.PARTICLE);

        List<ResourceLocation> text_variants = new ArrayList<>();

        for (int i = 1; i < variants+1;i++) {
            for (int j = 1; j < sub_variants+1;j++) {
                ResourceLocation var = resourcelocation.withSuffix(i +"-"+j);

                text_variants.add(var);

                TexturedModel.Provider provider = TexturedModel.createDefault(pBlock1 ->
                        withTexture(var), template);
                provider.createWithSuffix(pBlock,i +"-"+j,modelOutput);
            }
        }



        this.blockStateOutput.accept(createSimpleBlock1(pBlock,text_variants));
    }

    public static MultiVariantGenerator createSimpleBlock1(Block pBlock,List<ResourceLocation> variants) {
        Variant[] variants1 = variants.stream().map( r -> Variant.variant().with(VariantProperties.MODEL,r)).toArray(i -> new Variant[i]);
        return MultiVariantGenerator.multiVariant(pBlock, variants1);
    }

    public static final ModelTemplate EASTER_EGG_TEMPLATE = create(new ResourceLocation(EggHunt.MOD_ID,"block/easter_egg"), TextureSlot.ALL,TextureSlot.PARTICLE);
    public static final TexturedModel.Provider EASTER_EGG = TexturedModel.createDefault(pBlock ->
                    withTexture(new ResourceLocation(EggHunt.MOD_ID,"block/easter_egg1-1")),
            EASTER_EGG_TEMPLATE);

    public static TextureMapping withTexture(ResourceLocation texture) {
        //should be all
        TextureMapping textureMapping = TextureMapping.cube(texture);
        textureMapping.put(TextureSlot.PARTICLE,texture);
        return textureMapping;
    }


    private static ModelTemplate create(ResourceLocation pBlockModelLocation, TextureSlot... pRequiredSlots) {
        return new ModelTemplate(Optional.of(pBlockModelLocation),
                Optional.empty(), pRequiredSlots);
    }

}
