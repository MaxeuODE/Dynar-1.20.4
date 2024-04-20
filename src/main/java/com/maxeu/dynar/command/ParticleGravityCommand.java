package com.maxeu.dynar.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static com.mojang.brigadier.arguments.FloatArgumentType.floatArg;
import static com.mojang.brigadier.arguments.FloatArgumentType.getFloat;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ParticleGravityCommand {
    public static void particleGravityCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("particle-gravity")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(argument("particle", word())
                            .then(argument("gravity", floatArg())
                                    .executes(context -> setGravity(getString(context, "particle"), getFloat(context, "gravity")))
                            )
                    )
            );
        });
    }
    private static int setGravity(String particle, float value) {
        switch (particle) {
            case "end_rod":
        }
        return 1;
    }
}
