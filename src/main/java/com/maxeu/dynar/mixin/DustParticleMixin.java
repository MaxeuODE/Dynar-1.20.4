package com.maxeu.dynar.mixin;

import net.minecraft.particle.AbstractDustParticleEffect;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractDustParticleEffect.class)
public interface DustParticleMixin {
    @Accessor("color")
    Vector3f color();

    @Accessor("scale")
    float scale();
}
