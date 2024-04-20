package com.maxeu.dynar.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ParticleLimit {
    private static int particleLimit = -1;


    public static int getParticleLimit() {
        return particleLimit;
    }

    public static void setParticleLimit(int particleLimit) {
        ParticleLimit.particleLimit = particleLimit;
    }
}
