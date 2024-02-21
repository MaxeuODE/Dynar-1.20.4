package com.maxeu.dynar.particle;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
public class OlaStaticSphere {
    public  static void OlaParticle(int numPoints,float radius,float[] center){
        List<double[]> vertices = generatePolyhedronVertices(numPoints, radius, center);
        PlayerEntity player = MinecraftClient.getInstance().player;
        World world = null;
        if (player != null) {
            world = player.getEntityWorld();
        }
        if (world != null) {
            for (double[] vertex : vertices) {
                world.addParticle(ParticleTypes.END_ROD,true,vertex[0],vertex[1],vertex[2],0,0,0);
            }
        }

    }
    private static List<double[]> generatePolyhedronVertices(int numPoints, float radius, float[] center) {
        List<double[]> vertices = new ArrayList<>();
        double sqrt5 = Math.sqrt(5);
        double thetaFactor = Math.PI * (3 - sqrt5);

        for (int i = 0; i < numPoints; i++) {
            double phi = Math.acos(1 - 2 * (i + 0.5) / numPoints);
            double theta =thetaFactor * (i + 0.5);
            double cosPhi =Math.cos(phi);
            double sinPhi = Math.sin(phi);
            double cosTheta = Math.cos(theta);
            double sinTheta = Math.sin(theta);
            double x = center[0] + radius * cosTheta * sinPhi;
            double y = center[1] + radius * sinTheta * sinPhi;
            double z = center[2] + radius * cosPhi;
            vertices.add(new double[]{x, y, z});
        }
        return vertices;
    }
}
