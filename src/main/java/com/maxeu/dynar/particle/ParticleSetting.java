package com.maxeu.dynar.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ParticleSetting {
    private static int particleLimit = -1;
    private static float gravityStrength = 0;
    private static boolean gravitySwitch = false;
    private static boolean collisionSwitch = false;

    public static int getParticleLimit() {
        return particleLimit;
    }

    public static void setParticleLimit(int particleLimit) {
        ParticleSetting.particleLimit = particleLimit;
    }

    public static boolean isCollisionSwitch() {
        return collisionSwitch;
    }

    public static void setCollisionSwitch(boolean shouldCollision) {
        ParticleSetting.collisionSwitch = shouldCollision;
    }

    public static float getGravityStrength() {
        return gravityStrength;
    }

    public static void setGravityStrength(float gravityStrength) {
        ParticleSetting.gravityStrength = gravityStrength;
    }

    public static boolean isGravitySwitch() {
        return gravitySwitch;
    }

    public static void setGravitySwitch(boolean gravitySwitch) {
        ParticleSetting.gravitySwitch = gravitySwitch;
    }
}
