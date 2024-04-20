package com.maxeu.dynar.particle;

public class ParticleSwitch {
    private static boolean collisionSwitch = false;
    public static boolean isCollisionSwitch() {
        return collisionSwitch;
    }
    public static void setCollisionSwitch(boolean shouldCollision) {
        ParticleSwitch.collisionSwitch = shouldCollision;
    }
}
