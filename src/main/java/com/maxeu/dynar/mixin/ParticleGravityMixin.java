package com.maxeu.dynar.mixin;

import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Particle.class)
public interface ParticleGravityMixin {
    @Accessor("gravityStrength")
    void setGravityStrength(float gravityStrength);
}
