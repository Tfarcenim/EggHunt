package tfar.egghunt;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tfar.egghunt.datagen.ModDatagen;

@Mod(EggHunt.MOD_ID)
public class EggHuntForge {
    
    public EggHuntForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(ModDatagen::gather);
    
        // Use Forge to bootstrap the Common mod.
        EggHunt.init();
        
    }
}