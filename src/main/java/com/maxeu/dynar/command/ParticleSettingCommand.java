package com.maxeu.dynar.command;

import com.maxeu.dynar.particle.ParticleSetting;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;
import static com.mojang.brigadier.arguments.FloatArgumentType.floatArg;
import static com.mojang.brigadier.arguments.FloatArgumentType.getFloat;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ParticleSettingCommand {
    private static boolean shouldGravity = false;
    private static boolean shouldCollision = false;
    private static float gravityStrength = 0;

    public static void init() {
        particleSettingCommand();
    }

    private static void particleSettingCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("dynar-particle-setting")
                .requires(source -> source.hasPermissionLevel(2))
                .then(literal("switches")
                        .then(literal("gravity")
                                .then(argument("gravity", bool())
                                        .executes(context -> {
                                            ParticleSetting.setGravitySwitch(getBool(context, "gravity"));
                                            return 0;
                                        })))
                        .then(literal("collision")
                                .then(argument("collision", bool())
                                        .executes(context -> {
                                            ParticleSetting.setCollisionSwitch(getBool(context, "collision"));
                                            return 0;
                                        }))))
                .then(literal("figures")
                        .then(literal("gravity-strength")
                                .then(argument("strength", floatArg())
                                        .executes(context -> {
                                            ParticleSetting.setGravityStrength(getFloat(context, "strength"));
                                            return 0;
                                        })))
                )
        ));
    }
}
