package com.maxeu.dynar.command;

import com.maxeu.dynar.particle.OlaStaticSphere;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ParticleCommand {
    public static void particleCommand(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
        dispatcher.register(literal("ola")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("int1", IntegerArgumentType.integer())
                .then(argument("float2", FloatArgumentType.floatArg())
                .then(argument("float3", FloatArgumentType.floatArg())
                .then(argument("float4", FloatArgumentType.floatArg())
                .then(argument("float5", FloatArgumentType.floatArg())
                .executes(context -> {
                    int numPoint = IntegerArgumentType.getInteger(context, "int1");
                    float radium = FloatArgumentType.getFloat(context, "float2");
                    float[] center = {  FloatArgumentType.getFloat(context, "float3"),
                                        FloatArgumentType.getFloat(context, "float4"),
                                        FloatArgumentType.getFloat(context, "float5")};
                    OlaStaticSphere.OlaParticle(numPoint,radium,center);
                    return 1;
                })))))));
            }
        );
    }
}
