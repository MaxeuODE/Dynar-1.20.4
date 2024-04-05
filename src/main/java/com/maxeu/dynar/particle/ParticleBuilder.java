package com.maxeu.dynar.particle;

import com.maxeu.dynar.shapes.OlaSphereLattice;
import com.maxeu.dynar.shapes.SphereRandomLattice;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;

public class ParticleBuilder {
    public static void olaSphere(int numPoints, float radius, Vec3d center, Vec3d velocity, MinecraftServer server, Boolean shouldDynamic){
        OlaSphereLattice.generatePointsOnOlaSphere(numPoints,radius,center,velocity,server,shouldDynamic);
    }
    public static void randomSphere(int numPoints, float radius, Vec3d center, Vec3d velocity, MinecraftServer server, Boolean shouldDynamic){
        SphereRandomLattice.generateRandomPoints(numPoints,radius,center,velocity,server,shouldDynamic);
    }
}
