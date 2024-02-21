package com.maxeu.dynar.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class CustomCommand {
    public static void addCommand(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("remainit")
                    .requires(source -> source.hasPermissionLevel(2))
                    .requires(ServerCommandSource::isExecutedByPlayer)
                    .executes(context -> CommandUtils.showPlayers(context.getSource()))
                    .then(argument("KotLin", StringArgumentType.string())
                        .suggests((context, builder) -> CommandUtils.getSuggestions(builder))
                    )
                );
            }
        );
    }
}
