package com.maxeu.dynar.command;

import com.maxeu.dynar.particle.PointsOnOlaSphere;
import com.maxeu.dynar.particle.RandomPointOnSphere;
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
                                            .then(argument("center", Vec3ArgumentType.vec3())
                                                    .then(argument("velocity", Vec3ArgumentType.vec3())
                                                            .executes(context -> {
                                                                int numPoint = getInteger(context, "int1");
                                                                float radium = getFloat(context, "float2");
                                                                Vec3d center = Vec3ArgumentType.getVec3(context,"center");
                                                                Vec3d velocity = Vec3ArgumentType.getVec3(context,"velocity");
                                                                        PointsOnOlaSphere.olaStatic(numPoint, radium, center, velocity, context.getSource().getServer());
                                                                return 1;
                                                            }))))));
                    dispatcher.register(literal("sphere-static-random")
                            .requires(source -> source.hasPermissionLevel(2))
                            .then(argument("numPoints", integer())
                                    .then(argument("radius", floatArg())
                                            .then(argument("center", Vec3ArgumentType.vec3()))
                                            .then(argument("velocity", Vec3ArgumentType.vec3()))
                                            .executes(context -> {
                                                int numPoint = getInteger(context, "numPoints");
                                                float radium = getFloat(context, "radius");
                                                Vec3d center = Vec3ArgumentType.getVec3(context, "center");
                                                Vec3d velocity = Vec3ArgumentType.getVec3(context, "velocity");
                                                RandomPointOnSphere.randomStatic(numPoint, radium, center, velocity, context.getSource().getServer());
                                                return 1;
                                            }))));
//        dispatcher.register(literal("sphere-dynamic")
//                .requires(source -> source.hasPermissionLevel(2))
//                .then(argument("numPoint", integer())
//                .then(argument("startRadium", floatArg())
//                .then(argument("endRadium", floatArg())
//                .then(argument("step", floatArg())
//                .then(argument("x", floatArg())
//                .then(argument("y", floatArg())
//                .then(argument("z", floatArg())
//                .then(argument("time", integer())
//                .executes(context -> {
//                    int numPoint = getInteger(context, "numPoint");
//                    float radiumStart = getFloat(context, "startRadium");
//                    float radiumEnd = getFloat(context, "endRadium");
//                    float step = getFloat(context, "step");
//                    float[] center = {  getFloat(context, "x"),
//                                        getFloat(context, "y"),
//                                        getFloat(context, "z")};
//                    OlaSphere.olaDynamic(numPoint,radiumStart,radiumEnd,step,center,(long) getInteger(context, "time"));
//                    return 1;
//                }))))))))));
                }
        );
    }
}
