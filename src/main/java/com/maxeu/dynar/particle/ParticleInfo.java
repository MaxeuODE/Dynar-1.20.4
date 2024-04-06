package com.maxeu.dynar.particle;

import net.minecraft.particle.*;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.Arrays;

public record ParticleInfo(ParticleType<?> type, double[] info) {
    public boolean isDefault() {
        return type instanceof DefaultParticleType;
    }

    public boolean isDust() {
        return type.equals(ParticleTypes.DUST);
    }

    public boolean isDustTransition() {
        return type.equals(ParticleTypes.DUST_COLOR_TRANSITION);
    }

    public DustParticleEffect dust() {
        if (!type.equals(ParticleTypes.DUST) || info.length != 4) {
            throw new IllegalArgumentException("the type is not dust particle effect.");
        }
        return new DustParticleEffect(new Vec3d(info[0], info[1], info[2]).toVector3f(), (float) info[3]);
    }

    public DustColorTransitionParticleEffect dustTransition() {
        if (!type.equals(ParticleTypes.DUST_COLOR_TRANSITION) || info.length != 7) {
            throw new IllegalArgumentException("the type is not dust color transition effect.");
        }
        return new DustColorTransitionParticleEffect(new Vec3d(info[0], info[1], info[2]).toVector3f(), new Vec3d(info[3], info[4], info[5]).toVector3f(), (float) info[6]);
    }

    public ParticleEffect getGeneralEffect() {
        if (isDustTransition()) {
            return dustTransition();
        }
        if (isDust()) {
            return dust();
        }
        if (isDefault()) {
            return (DefaultParticleType) type;
        }
        return null;
    }

    public long[] toLongArray() {
        return Arrays.stream(info).mapToLong(Double::doubleToLongBits).toArray();
    }

    public boolean hasExtraInfo() {
        return info != null;
    }
}
