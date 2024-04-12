package com.maxeu.dynar.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ParticleLimits {
    private static int particleLimit = 16384;


    public static int getParticleLimit() {
        return particleLimit;
    }

    public static void setParticleLimit(int particleLimit) {
        ParticleLimits.particleLimit = particleLimit;
    }
}
