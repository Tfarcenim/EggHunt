package tfar.egghunt.datagen;

import com.google.gson.JsonElement;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import tfar.egghunt.EggHunt;
import tfar.egghunt.init.ModBlocks;

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
        createTrivialBlock(ModBlocks.EASTER_EGG1_1,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG1_2,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG1_3,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG1_4,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG1_5,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG2_1,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG2_2,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG2_3,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG2_4,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG2_5,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG3_1,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG3_2,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG3_3,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG3_4,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG3_5,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG4_1,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG4_2,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG4_3,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG4_4,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG4_5,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
        createTrivialBlock(ModBlocks.EASTER_EGG5,TexturedModel.createDefault(TextureMapping::cube,EASTER_EGG_TEMPLATE));
    }

    public static final ModelTemplate EASTER_EGG_TEMPLATE = create(new ResourceLocation(EggHunt.MOD_ID,"block/easter_egg"), TextureSlot.ALL,TextureSlot.PARTICLE);

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
