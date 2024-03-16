package tfar.egghunt.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import tfar.egghunt.EggHunt;


public class ModLangProvider extends LanguageProvider {
    public ModLangProvider(PackOutput output) {
        super(output, EggHunt.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {

    }
}
