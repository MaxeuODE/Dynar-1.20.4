package com.maxeu.dynar.mixin;

import com.maxeu.dynar.interfaces.GravityAccessor;
import net.minecraft.client.particle.EndRodParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EndRodParticle.class)
public abstract class EndRodMixin implements GravityAccessor {
    @Unique
    private static float gravity = 1;
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/AnimatedParticle;<init>(Lnet/minecraft/client/world/ClientWorld;DDDLnet/minecraft/client/particle/SpriteProvider;F)V"), index = 5)
    private static float setGravity(float upwardsAcceleration) {
        return gravity;
    }

    @Override
    @Unique
    public void setGravity$0(float gravity) {
        EndRodMixin.gravity = gravity;
    }

    @Override
    @Unique
    public float getGravity$1() {
        return EndRodMixin.gravity;
    }
}
