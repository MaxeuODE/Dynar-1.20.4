package com.maxeu.dynar.particle;

import com.maxeu.dynar.network.NetworkHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class RandomPointOnSphere {
    public static void randomStatic(int numPoints, float radius, Vec3d center, Vec3d velocity, MinecraftServer server){
        generateRandomPoints(numPoints,radius,center,velocity,server);
    }
    private static void generateRandomPoints(int numPoints, float radius, Vec3d center, Vec3d velocity, MinecraftServer server) {
        Random rand = new Random(0); // 为了可重复性设置随机种子
        List<long[]> vec = new ArrayList<>();
        //获取ServerPlayerEntity
        List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
        for (int i = 0; i < numPoints; i++) {
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
        //网络通信
        for (ServerPlayerEntity player : players) {
            NetworkHandler.sendLongList(player, vec);
        }
    }
}