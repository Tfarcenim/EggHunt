package tfar.egghunt;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.TimeArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import tfar.egghunt.level.EggHuntData;

import java.util.List;

public class EggHuntCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(EggHunt.MOD_ID)
                .then(Commands.literal("start").requires(commandSourceStack -> commandSourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .then(Commands.argument("position", BlockPosArgument.blockPos())
                                .then(Commands.argument("time", TimeArgument.time()).executes(EggHuntCommand::start)

                                )
                        )
                )
                .then(Commands.literal("leaderboard")
                        .executes(EggHuntCommand::leaderboard)
                )
        );
    }

    private static int leaderboard(CommandContext<CommandSourceStack> context) {
        CommandSourceStack commandSourceStack = context.getSource();
        EggHuntData eggHuntData = EggHuntData.getInstance(commandSourceStack.getServer().overworld());
        if (eggHuntData.isActive()) {
            List<Component> leaderboard = eggHuntData.compileLeaderBoard(commandSourceStack.getLevel());
            for (Component component : leaderboard) {
                commandSourceStack.sendSystemMessage(component);
            }
            return 1;
        }

        commandSourceStack.sendFailure(Component.literal("No egg hunt active!"));

        return 0;
    }

    private static int start(CommandContext<CommandSourceStack> context) {
        BlockPos teleportTo = BlockPosArgument.getBlockPos(context, "position");
        int ticks = IntegerArgumentType.getInteger(context, "time");

        CommandSourceStack commandSourceStack = context.getSource();

        EggHuntData eggHuntData = EggHuntData.getInstance(commandSourceStack.getServer().overworld());

        eggHuntData.start(ticks, teleportTo, commandSourceStack.getLevel());

        return 1;
    }

}
