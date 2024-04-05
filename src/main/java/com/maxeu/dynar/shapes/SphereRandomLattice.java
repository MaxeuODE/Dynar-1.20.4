package com.maxeu.dynar.shapes;

import com.maxeu.dynar.network.NetworkHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereRandomLattice {
    public static void generateRandomPoints(int numPoints, float radius, Vec3d center, Vec3d velocity, MinecraftServer server, boolean bl) {
        Random rand = new Random(0); // 为了可重复性设置随机种子
        List<long[]> vec = new ArrayList<>();
        //获取ServerPlayerEntity
        List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
        if (bl) {
            for (int i = 0; i < numPoints; i++) {
                double u = rand.nextDouble();
                double v = rand.nextDouble();
                double theta = 2 * Math.PI * u;
                double phi = Math.acos(2 * v - 1);
                double vX = radius * Math.sin(phi) * Math.cos(theta);
                double vY = radius * Math.sin(phi) * Math.sin(theta);
                double vZ = radius * Math.cos(phi);
                double x = center.x + vX;
                double y = center.y + vY;
                double z = center.z + vZ;
                long[] pos = new long[]{
                        Double.doubleToLongBits(x),
                        Double.doubleToLongBits(y),
                        Double.doubleToLongBits(z),
                        Double.doubleToLongBits(velocity.x + vX),
                        Double.doubleToLongBits(velocity.y + vY),
                        Double.doubleToLongBits(velocity.z + vZ)};
                vec.add(pos);
            }
        } else for (int i = 0; i < numPoints; i++) {
            double u = rand.nextDouble();
            double v = rand.nextDouble();
            double theta = 2 * Math.PI * u;
            double phi = Math.acos(2 * v - 1);
            double x = center.x + radius * Math.sin(phi) * Math.cos(theta);
            double y = center.y + radius * Math.sin(phi) * Math.sin(theta);
            double z = center.z + radius * Math.cos(phi);
            long[] pos = new long[]{
                    Double.doubleToLongBits(x),
                    Double.doubleToLongBits(y),
                    Double.doubleToLongBits(z),
                    Double.doubleToLongBits(velocity.x),
                    Double.doubleToLongBits(velocity.y),
                    Double.doubleToLongBits(velocity.z)};
            vec.add(pos);
        }

        for (ServerPlayerEntity player : players) {
            NetworkHandler.sendLongList(player, vec);
        }
    }
}
