package com.maxeu.dynar.mixin;

import com.google.common.collect.EvictingQueue;
import com.maxeu.dynar.particle.ParticleLimits;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.ParticleTextureSheet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Function;

@Mixin(ParticleManager.class)
public class ParticleLimitMixin {
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;"), index = 1)
    private Function<ParticleTextureSheet, EvictingQueue<?>> changeLimit(Function<? super ParticleTextureSheet, ? extends EvictingQueue<?>> mappingFunction) {
        final int amount = ParticleLimits.getParticleLimit() != -1 ? ParticleLimits.getParticleLimit() : 16384;
        return sheet -> EvictingQueue.create(amount);
    }
}
