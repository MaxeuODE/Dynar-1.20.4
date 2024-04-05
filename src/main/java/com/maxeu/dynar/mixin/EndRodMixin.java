package com.maxeu.dynar.mixin;

import net.minecraft.client.particle.EndRodParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EndRodParticle.class)
public class EndRodMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/AnimatedParticle;<init>(Lnet/minecraft/client/world/ClientWorld;DDDLnet/minecraft/client/particle/SpriteProvider;F)V"), index = 5)
    private static float gravitySet(float upwardsAcceleration) {
        return 1.1F;
    }
}
