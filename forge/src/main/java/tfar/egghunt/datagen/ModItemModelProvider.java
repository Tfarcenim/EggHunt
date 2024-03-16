package tfar.egghunt.datagen;

import com.google.gson.JsonElement;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelGenerators {

    public ModItemModelProvider(BiConsumer<ResourceLocation, Supplier<JsonElement>> pOutput) {
        super(pOutput);
    }

    @Override
    public void run() {
     /*   generateArmorTrims(Init.CHICKEN_HELMET);
        generateArmorTrims(Init.CHICKEN_CHESTPLATE);
        generateArmorTrims(Init.CHICKEN_LEGGINGS);
        generateArmorTrims(Init.CHICKEN_BOOTS);

        generateFlatItem(Init.IRON_SEEDS, ModelTemplates.FLAT_ITEM);
        generateFlatItem(Init.GOLD_SEEDS, ModelTemplates.FLAT_ITEM);
        generateFlatItem(Init.DIAMOND_SEEDS, ModelTemplates.FLAT_ITEM);
        generateFlatItem(Init.NETHERITE_SEEDS, ModelTemplates.FLAT_ITEM);
        generateFlatItem(Init.ENDER_EGG,ModelTemplates.FLAT_ITEM);

        this.generateCompassItem(Init.CHICKEN_COMPASS);*/


        //      generateChickItem(Init.CHICKEN_PICKAXE,"",CHICK_PICK);
    }



 //   public static final ModelTemplate CHICK_PICK = create(new ResourceLocation(ChickenVsHunter.MOD_ID,"chicken_pickaxe"), TextureSlot.TEXTURE);

   // public void generateChickItem(Item pItem, String pModelLocationSuffix, ModelTemplate pModelTemplate) {
  //      pModelTemplate.create(ModelLocationUtils.getModelLocation(pItem, pModelLocationSuffix), TextureMapping.layer0(TextureMapping.getItemTexture(pItem, pModelLocationSuffix)), this.output);
  //  }


  //  private static ModelTemplate create(ResourceLocation pBlockModelLocation, TextureSlot... pRequiredSlots) {
  //      return new ModelTemplate(Optional.of(new ResourceLocation(pBlockModelLocation.getNamespace(), "block/" + pBlockModelLocation.getPath())), Optional.empty(), pRequiredSlots);
  //  }


}
