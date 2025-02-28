package com.tntimer.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.tntimer.TNTimerMod;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class TNTCommand {
    public static void register() {
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandRegistryAccess, registrationEnvironment) ->
                        commandDispatcher.register((CommandManager.literal("tnttime").then(CommandManager.argument("ticks", IntegerArgumentType.integer(0))
                            .executes(TNTCommand::setGlobalTntTimer)).executes(commandContext -> {
                        commandContext.getSource().sendFeedback(() -> Text.literal(String.format("Current TNT Time: %d ticks.", TNTimerMod.globalTntFuseTicks)), false);
                        return 1;
                    }
            )
            )
            )
        )
        );
    }

    private static int setGlobalTntTimer(CommandContext<ServerCommandSource> context) {
        int ticks = IntegerArgumentType.getInteger(context, "ticks");

        TNTimerMod.globalTntFuseTicks = ticks;
        TNTimerMod.TntFuseTicks = ticks;

        context.getSource().sendFeedback(() -> Text.literal(String.format("New TNT Time: %d ticks.", ticks)), false);
        return 1;
    }
}
