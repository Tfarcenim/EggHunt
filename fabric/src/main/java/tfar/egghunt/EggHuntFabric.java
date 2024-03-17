package tfar.egghunt;

import com.mojang.brigadier.CommandDispatcher;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.MinecraftServer;
import tfar.egghunt.config.EggHuntClothConfig;

public class EggHuntFabric implements ModInitializer {
    public static EggHuntClothConfig CONFIG;

    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        AutoConfig.register(EggHuntClothConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(EggHuntClothConfig.class).getConfig();

        CommandRegistrationCallback.EVENT.register(this::registerCommands);
        ServerTickEvents.START_SERVER_TICK.register(this::serverTick);

        // Use Fabric to bootstrap the Common mod.
        EggHunt.init();
    }

    private void serverTick(MinecraftServer server) {
        EggHunt.serverTick(server);
    }

    private void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection commandSelection) {
        EggHuntCommand.register(dispatcher);
    }

}
