package com.maxeu.dynar.mixin;

import com.google.common.collect.EvictingQueue;
import com.google.common.collect.Maps;
import com.maxeu.dynar.particle.ParticleInstance;
import com.maxeu.dynar.particle.ParticleSetting;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {
    @Final
    @Shadow
    private final Map<ParticleTextureSheet, Queue<Particle>> particles = Maps.newIdentityHashMap();

    @Shadow
    public abstract @Nullable Particle addParticle(ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ);

    @Shadow public abstract CompletableFuture<Void> reload(ResourceReloader.Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor);

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;"), index = 1)
    private Function<ParticleTextureSheet, EvictingQueue<?>> changeLimit(Function<? super ParticleTextureSheet, ? extends EvictingQueue<?>> mappingFunction) {
        final int amount = ParticleSetting.getParticleLimit() != -1 ? ParticleSetting.getParticleLimit() : 16384;
        return sheet -> EvictingQueue.create(amount);
    }

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/Queue;add(Ljava/lang/Object;)Z"), index = 0)
    private Object particle(Object obj) {
        Particle particle = (Particle) obj;
        //do anything
//        if (ParticleInstance.particle != null) {
//            return ParticleInstance.particle;
//        }
        return particle;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void getParticle(CallbackInfo ci) {
        if (ParticleInstance.bl) {
            Particle particle = this.addParticle(ParticleInstance.effect, ParticleInstance.pos.x, ParticleInstance.pos.y, ParticleInstance.pos.z, ParticleInstance.velocity.x, ParticleInstance.velocity.y, ParticleInstance.velocity.z);
            this.particles.computeIfAbsent(particle.getType(), sheet -> EvictingQueue.create(16384)).add(particle);
        }
        return;
    }
}
