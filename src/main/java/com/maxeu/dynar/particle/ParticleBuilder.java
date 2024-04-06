package com.maxeu.dynar.particle;

import com.maxeu.dynar.mixin.DustParticleMixin;
import com.maxeu.dynar.mixin.DustTransitionMixin;
import com.maxeu.dynar.network.NetworkHandler;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.particle.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class ParticleBuilder {
    /**
     * Calculate the position of the particles on a sphere. Then send them to the client.
     *
     * @param amount   the amount of particles to spawn
     * @param radius   the radius of the sphere
     * @param center   the center position of the sphere
     * @param velocity the velocity vector of the particles
     * @param server   player's server
     * @param dynamic  whether the particles should be dynamic or not
     * @param rand     controls the randomness of the particles. {@code null} - if the particles should be static
     */
    public static void sphere(int amount, float radius, Vec3d center, Vec3d velocity, ParticleEffect effect, MinecraftServer server, boolean dynamic, Random rand) {
        //initialize the list
        List<long[]> posList = new ArrayList<>();
        List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
        final double thetaFactor = Math.PI * (3 - Math.sqrt(5));
        //calculate the position of the particle
        for (int i = 0; i < amount; i++) {
            final double phi = rand == null ? Math.acos(1 - 2 * (i + 0.5) / amount) : Math.acos(-1 + 2 * rand.nextDouble());
            final double theta = rand == null ? thetaFactor * (i + 0.5) : 2 * Math.PI * rand.nextDouble();
            final double sinPhi = Math.sin(phi);
            final double vx = radius * Math.cos(theta) * sinPhi;
            final double vy = radius * Math.sin(theta) * sinPhi;
            final double vz = radius * Math.cos(phi);
            final double x = center.x + vx;
            final double y = center.y + vy;
            final double z = center.z + vz;
            long[] pos;
            if (dynamic) {
                pos = new long[]{
                        Double.doubleToLongBits(x),
                        Double.doubleToLongBits(y),
                        Double.doubleToLongBits(z),
                        Double.doubleToLongBits(velocity.x + vx),
                        Double.doubleToLongBits(velocity.y + vy),
                        Double.doubleToLongBits(velocity.z + vz)};
            } else {
                pos = new long[]{
                        Double.doubleToLongBits(x),
                        Double.doubleToLongBits(y),
                        Double.doubleToLongBits(z),
                        Double.doubleToLongBits(velocity.x),
                        Double.doubleToLongBits(velocity.y),
                        Double.doubleToLongBits(velocity.z)};
            }
            posList.add(pos);

        }
        //send the list to the client
        for (ServerPlayerEntity player : players) {
//            NetworkHandler.sendLongList(player, posList);
            NetworkHandler.sendParticleInfo(player, posList, toParticleInfo(effect));
        }
    }

    private static ParticleInfo toParticleInfo(@NotNull ParticleEffect effect) {
        final double[] info;
        if (effect instanceof DustColorTransitionParticleEffect transition) {
            final Vector3f v3f = ((DustParticleMixin) transition).color();
            final Vector3f v3f2 = ((DustTransitionMixin) transition).toColor();
            info = new double[]{v3f.x, v3f.y, v3f.z, ((DustParticleMixin) transition).scale(), v3f2.x, v3f2.y, v3f2.z};
        } else if (effect instanceof DustParticleEffect dust) {
            final Vector3f v3f = ((DustParticleMixin) dust).color();
            info = new double[]{v3f.x, v3f.y, v3f.z, ((DustParticleMixin) dust).scale()};
        } else {
            info = null;
        }
        return new ParticleInfo(effect.getType(), info);
    }
}
