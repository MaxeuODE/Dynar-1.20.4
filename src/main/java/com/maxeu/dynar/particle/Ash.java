/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.maxeu.dynar.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AscendingParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.random.Random;

@Environment(value = EnvType.CLIENT)
public class Ash extends AscendingParticle {
    private static final int COLOR = 12235202;

    protected Ash(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, float scaleMultiplier, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.1f, -0.1f, 0.1f, velocityX, velocityY, velocityZ, scaleMultiplier, spriteProvider, 0.0f, 20, 0.0125f, false);
        this.red = (float) ColorHelper.Argb.getRed(12235202) / 255.0f;
        this.green = (float) ColorHelper.Argb.getGreen(12235202) / 255.0f;
        this.blue = (float) ColorHelper.Argb.getBlue(12235202) / 255.0f;
    }
    @Environment(value = EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            Random random = clientWorld.random;
            double j = (double) random.nextFloat() * -1.9 * (double) random.nextFloat() * 0.1;
            double k = (double) random.nextFloat() * -0.5 * (double) random.nextFloat() * 0.1 * 5.0;
            double l = (double) random.nextFloat() * -1.9 * (double) random.nextFloat() * 0.1;
            return new Ash(clientWorld, d, e, f, j, k, l, 1.0f, this.spriteProvider);
        }

        public /* synthetic */ Particle createParticle(ParticleEffect particleEffect, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return this.createParticle((DefaultParticleType) particleEffect, clientWorld, d, e, f, g, h, i);
        }
    }
}

