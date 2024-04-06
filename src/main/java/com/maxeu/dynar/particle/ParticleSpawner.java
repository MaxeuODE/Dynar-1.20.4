package com.maxeu.dynar.particle;

import net.minecraft.client.MinecraftClient;

import java.util.List;

public class ParticleSpawner {
    public static void spawnParticle(List<long[]> poses, ParticleInfo info, MinecraftClient client) {
        if (client.world != null) {
            for (long[] pos : poses) {
                client.world.addParticle(
                        info.getGeneralEffect(), true,
                        Double.longBitsToDouble(pos[0]),
                        Double.longBitsToDouble(pos[1]),
                        Double.longBitsToDouble(pos[2]),
                        Double.longBitsToDouble(pos[3]),
                        Double.longBitsToDouble(pos[4]),
                        Double.longBitsToDouble(pos[5]));
            }
        }
    }
}
