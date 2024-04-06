package com.maxeu.dynar.mixin;

import net.minecraft.particle.DustColorTransitionParticleEffect;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DustColorTransitionParticleEffect.class)
public interface DustTransitionMixin {
    @Accessor("toColor")
    Vector3f toColor();
}
