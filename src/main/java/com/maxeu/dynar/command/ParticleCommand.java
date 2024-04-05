package com.maxeu.dynar.command;

import com.maxeu.dynar.particle.ParticleBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.util.math.Vec3d;

import static com.mojang.brigadier.arguments.FloatArgumentType.floatArg;
import static com.mojang.brigadier.arguments.FloatArgumentType.getFloat;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ParticleCommand {
    public static void particleCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
                    dispatcher.register(literal("sphere-static-ola")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(argument("int1", integer())
                                    .then(argument("float2", floatArg())
                                            .then(argument("center", Vec3ArgumentType.vec3(false))
                                                    .then(argument("velocity", Vec3ArgumentType.vec3(false))
                                                            .executes(context -> {
                                                                int numPoint = getInteger(context, "int1");
                                                                float radium = getFloat(context, "float2");
                                                                Vec3d center = Vec3ArgumentType.getVec3(context, "center");
                                                                Vec3d velocity = Vec3ArgumentType.getVec3(context, "velocity");
                                                                ParticleBuilder.olaSphere(numPoint, radium, center, velocity, context.getSource().getServer(),false);
                                                                return 1;
                                                            }))))));
                    dispatcher.register(literal("sphere-dynamic-ola")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(argument("int1", integer())
                                    .then(argument("float2", floatArg())
                                            .then(argument("center", Vec3ArgumentType.vec3(false))
                                                    .then(argument("velocity", Vec3ArgumentType.vec3(false))
                                                            .executes(context -> {
                                                                int numPoint = getInteger(context, "int1");
                                                                float radium = getFloat(context, "float2");
                                                                Vec3d center = Vec3ArgumentType.getVec3(context, "center");
                                                                Vec3d velocity = Vec3ArgumentType.getVec3(context, "velocity");
                                                                ParticleBuilder.olaSphere(numPoint, radium, center, velocity, context.getSource().getServer(),true);
                                                                return 1;
                                                            }))))));
                    dispatcher.register(literal("sphere-static-random")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(argument("numPoints", integer())
                                    .then(argument("radius", floatArg())
                                            .then(argument("center", Vec3ArgumentType.vec3(false)))
                                            .then(argument("velocity", Vec3ArgumentType.vec3(false)))
                                            .executes(context -> {
                                                int numPoint = getInteger(context, "numPoints");
                                                float radium = getFloat(context, "radius");
                                                Vec3d center = Vec3ArgumentType.getVec3(context, "center");
                                                Vec3d velocity = Vec3ArgumentType.getVec3(context, "velocity");
                                                ParticleBuilder.randomSphere(numPoint, radium, center, velocity, context.getSource().getServer(),false);
                                                return 1;
                                            }))));
            dispatcher.register(literal("sphere-dynamic-random")
                    .requires(source -> source.hasPermissionLevel(2))
                    .then(argument("numPoints", integer())
                            .then(argument("radius", floatArg())
                                    .then(argument("center", Vec3ArgumentType.vec3(false)))
                                    .then(argument("velocity", Vec3ArgumentType.vec3(false)))
                                    .executes(context -> {
                                        int numPoint = getInteger(context, "numPoints");
                                        float radium = getFloat(context, "radius");
                                        Vec3d center = Vec3ArgumentType.getVec3(context, "center");
                                        Vec3d velocity = Vec3ArgumentType.getVec3(context, "velocity");
                                        ParticleBuilder.randomSphere(numPoint, radium, center, velocity, context.getSource().getServer(),true);
                                        return 1;
                                    }))));
                }
        );
    }
}
