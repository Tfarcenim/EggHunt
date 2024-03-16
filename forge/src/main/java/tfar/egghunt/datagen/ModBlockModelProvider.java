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
        //createTrivialBlock(Init.GOLDEN_EGG, DRAGON_EGG);
    }

    public static final ModelTemplate DRAGON_EGG_TEMPLATE = create(new ResourceLocation("block/dragon_egg"), TextureSlot.ALL,TextureSlot.PARTICLE);
    public static final TexturedModel.Provider DRAGON_EGG = TexturedModel.createDefault(pBlock ->
                    withTexture(new ResourceLocation("block/gold_block")),
            DRAGON_EGG_TEMPLATE);

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
