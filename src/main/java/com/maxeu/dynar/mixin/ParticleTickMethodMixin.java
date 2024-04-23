package com.maxeu.dynar.mixin;

import com.maxeu.dynar.particle.ParticleSetting;
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
public class ParticleTickMethodMixin {
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

    @Shadow
    protected float gravityStrength;

    @Inject(method = "tick", at = @At("TAIL"))
    private void addCollision(CallbackInfo ci) {
        if (ParticleSetting.isCollisionSwitch()) {
            final double nextPosX = x + velocityX;
            final double nextPosY = y + velocityY;
            final double nextPosZ = z + velocityZ;
            final Vec3d originPos = new Vec3d(x, y, z);
            final Vec3d nextPos = new Vec3d(nextPosX, nextPosY, nextPosZ);
            final BlockPos nextBlockPos = new BlockPos((int) floor(nextPosX), (int) floor(nextPosY), (int) floor(nextPosZ));
            final BlockState nextBlockState = world.getBlockState(nextBlockPos);
            final VoxelShape nextBlockVoxel = nextBlockState.getCollisionShape(world, nextBlockPos);
            final BlockHitResult result = world.raycastBlock(originPos, nextPos, nextBlockPos, nextBlockVoxel, nextBlockState);
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

    @Inject(method = "tick", at = @At("HEAD"))
    private void setGravity(CallbackInfo ci) {
        if (ParticleSetting.isGravitySwitch()) {
            this.gravityStrength = ParticleSetting.getGravityStrength();
        }
    }
}



