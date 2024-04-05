package com.maxeu.dynar.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.lang.Math.floor;

@Mixin(Particle.class)
public class ParticleBoundsMixin {
    @Shadow
    protected double x;
    @Shadow
    protected double y;
    @Shadow
    protected double z;
    @Shadow
    @Final
    protected ClientWorld world;
    @Shadow
    protected double velocityY;
    @Shadow
    protected double velocityZ;
    @Shadow
    protected double velocityX;

    @Inject(method = "tick", at = @At("TAIL"))
    private void addCollision(CallbackInfo ci) {
        double nextPosX = x + velocityX;
        double nextPosY = y + velocityY;
        double nextPosZ = z + velocityZ;
        Vec3d originPos = new Vec3d(x, y, z);
        Vec3d nextPos = new Vec3d(nextPosX, nextPosY, nextPosZ);
        BlockPos nextBlockPos = new BlockPos((int) floor(nextPosX), (int) floor(nextPosY), (int) floor(nextPosZ));
        BlockState nextBlockState = world.getBlockState(nextBlockPos);
        VoxelShape nextBlockVoxel = nextBlockState.getCollisionShape(world, nextBlockPos);
        BlockHitResult result = world.raycastBlock(originPos, nextPos, nextBlockPos, nextBlockVoxel, nextBlockState);

        if (result != null) {
            if (result.getSide() == Direction.DOWN || result.getSide() == Direction.UP) {
                velocityY = -velocityY;
            } else if (result.getSide() == Direction.EAST || result.getSide() == Direction.WEST) {
                velocityX = -velocityX;
            } else if (result.getSide() == Direction.NORTH || result.getSide() == Direction.SOUTH) {
                velocityZ = -velocityZ;
            }
        }
    }
}



