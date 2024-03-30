package com.maxeu.dynar.particle;

import com.maxeu.dynar.network.NetworkHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public class PointsOnOlaSphere {
    public static void olaStatic(int numPoints, float radius, Vec3d center, Vec3d velocity, MinecraftServer server){
        generatePointsOnOlaSphere(numPoints,radius,center,velocity,server);
    }
    public static void generatePointsOnOlaSphere(int numPoints, float radius, Vec3d center, Vec3d velocity, MinecraftServer server) {
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
            double vX = radius * cosTheta * sinPhi;
            double vY = radius * sinTheta * sinPhi;
            double vZ = radius * cosPhi;
            double x = center.x + vX;
            double y = center.y + vY;
            double z = center.z + vZ;
            long[] pos = new long[]{
                    Double.doubleToLongBits(x),
                    Double.doubleToLongBits(y),
                    Double.doubleToLongBits(z),
                    Double.doubleToLongBits(velocity.x+vX),
                    Double.doubleToLongBits(velocity.y+vY),
                    Double.doubleToLongBits(velocity.z+vZ)};
            vec.add(pos);
        }
        //网络通信
        for (ServerPlayerEntity player : players) {
            NetworkHandler.sendLongList(player,vec);
        }
    }
}
