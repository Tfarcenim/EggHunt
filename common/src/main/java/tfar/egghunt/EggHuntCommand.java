package tfar.egghunt;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.TimeArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;

public class EggHuntCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal(EggHunt.MOD_ID)
                .requires(commandSourceStack -> commandSourceStack.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(Commands.literal("start")
                        .then(Commands.argument("position", BlockPosArgument.blockPos())
                                .then(Commands.argument("time", TimeArgument.time()).executes(EggHuntCommand::start)

                                )
                        )
                )
        );
    }

    private static int start(CommandContext<CommandSourceStack> context) {
        BlockPos teleportTo = BlockPosArgument.getBlockPos(context,"position");
        int ticks = IntegerArgumentType.getInteger(context,"time");

        CommandSourceStack commandSourceStack = context.getSource();

        EggHuntData eggHuntData = EggHuntData.getInstance(commandSourceStack.getServer().overworld());

        eggHuntData.start(ticks,teleportTo,commandSourceStack.getLevel());

        return 1;
    }

}
