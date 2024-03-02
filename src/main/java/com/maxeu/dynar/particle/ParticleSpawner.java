package com.maxeu.dynar.particle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleTypes;

import java.util.ArrayList;
import java.util.List;

public class ParticleSpawner {
    public static void spawnCommonParticle(PacketByteBuf buf, MinecraftClient client) {
        //获得包并生成粒子
        List<long[]> poses = buf.readCollection(ArrayList::new, PacketByteBuf::readLongArray);
        if (client.world != null) {
            for (long[] pos : poses) {
                client.world.addParticle(
                        ParticleTypes.END_ROD, true,
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
