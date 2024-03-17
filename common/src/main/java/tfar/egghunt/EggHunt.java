package tfar.egghunt;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tfar.egghunt.init.ModBlocks;
import tfar.egghunt.init.ModItems;
import tfar.egghunt.platform.Services;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class EggHunt {

    public static final String MOD_ID = "egghunt";
    public static final String MOD_NAME = "EggHunt";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {

        Services.PLATFORM.superRegister(ModBlocks.class, BuiltInRegistries.BLOCK, Block.class);
        Services.PLATFORM.superRegister(ModItems.class, BuiltInRegistries.ITEM, Item.class);
        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
    }

    public static void serverTick(MinecraftServer server) {
        EggHuntData eggHuntData = EggHuntData.getInstance(server.overworld());
        eggHuntData.tick(server.overworld());
    }

}