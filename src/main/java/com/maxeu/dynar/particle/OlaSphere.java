package com.maxeu.dynar.particle;

import com.maxeu.dynar.network.NetworkHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class OlaSphere {
    public static void olaStatic(int numPoints, float radius, float[] center, MinecraftServer server) {
        generatePolyhedronVertices(numPoints, radius, center, server);
    }
    private static void generatePolyhedronVertices(int numPoints, float radius, float[] center, MinecraftServer server) {
        List<long[]> vec = new ArrayList<>();
        //获取ServerPlayerEntity
        List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
        //欧拉球相关计算
        double sqrt5 = Math.sqrt(5);
        double thetaFactor = Math.PI * (3 - sqrt5);
        for (int i = 0; i < numPoints; i++) {
            double phi = Math.acos(1 - 2 * (i + 0.5) / numPoints);
            double theta = thetaFactor * (i + 0.5);
            double cosPhi = Math.cos(phi);
            double sinPhi = Math.sin(phi);
            double cosTheta = Math.cos(theta);
            double sinTheta = Math.sin(theta);
            double x = center[0] + radius * cosTheta * sinPhi;
            double y = center[1] + radius * sinTheta * sinPhi;
            double z = center[2] + radius * cosPhi;
            long[] pos = new long[]{Double.doubleToLongBits(x), Double.doubleToLongBits(y), Double.doubleToLongBits(z), 0, 0, 0};
            vec.add(pos);
        }
        //网络通信
        for (ServerPlayerEntity player : players) {
            NetworkHandler.sendLongList(player,vec);
        }
    }
}

