package com.maxeu.dynar.mixin;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Maps;
import com.maxeu.dynar.particle.ParticleLimit;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.ParticleTextureSheet;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
    @Final
    @Shadow
    private final Map<ParticleTextureSheet, Queue<Particle>> particles = Maps.newIdentityHashMap();
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;"), index = 1)
    private Function<ParticleTextureSheet, EvictingQueue<?>> changeLimit(Function<? super ParticleTextureSheet, ? extends EvictingQueue<?>> mappingFunction) {
        final int amount = ParticleLimit.getParticleLimit() != -1 ? ParticleLimit.getParticleLimit() : 16384;
        return sheet -> EvictingQueue.create(amount);
    }

    @ModifyArg(method = "tick", at= @At(value = "INVOKE", target = "Ljava/util/Queue;add(Ljava/lang/Object;)Z"),index = 0)
    private Object particle(Object obj){
        Particle particle = (Particle) obj;
        //do anything
        return particle;
    }
//    @Inject(method = "tick", at = @At("TAIL"))
//    private void addParticle(CallbackInfo ci){
//        this.particles.add(particle);
//    }
}
