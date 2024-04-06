package com.maxeu.dynar.command;

import com.maxeu.dynar.mixin.ParticleGravityMixin;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.ParticleEffect;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;
import static com.mojang.brigadier.arguments.FloatArgumentType.floatArg;
import static com.mojang.brigadier.arguments.FloatArgumentType.getFloat;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.command.argument.ParticleEffectArgumentType.getParticle;
import static net.minecraft.command.argument.ParticleEffectArgumentType.particleEffect;
import static net.minecraft.command.argument.Vec3ArgumentType.getVec3;
import static net.minecraft.command.argument.Vec3ArgumentType.vec3;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ParticleGravityCommand {
//    public static void particleCommand() {
//        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment)
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
