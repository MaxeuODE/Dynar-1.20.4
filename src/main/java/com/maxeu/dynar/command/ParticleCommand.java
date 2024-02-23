package com.maxeu.dynar.command;

import com.maxeu.dynar.particle.OlaSphere;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static com.mojang.brigadier.arguments.FloatArgumentType.floatArg;
import static com.mojang.brigadier.arguments.FloatArgumentType.getFloat;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ParticleCommand {
    public static void particleCommand(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
        dispatcher.register(literal("sphere-static")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("int1", integer())
                .then(argument("float2", floatArg())
                .then(argument("float3", floatArg())
                .then(argument("float4", floatArg())
                .then(argument("float5", floatArg())
                .executes(context -> {
                    int numPoint = getInteger(context, "int1");
                    float radium = getFloat(context, "float2");
                    float[] center = {  getFloat(context, "float3"),
                                        getFloat(context, "float4"),
                                        getFloat(context, "float5")};
                    OlaSphere.olaStatic(numPoint,radium,center, context.getSource().getServer());
                    return 1;
                })))))));
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
