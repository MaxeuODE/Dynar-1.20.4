package com.maxeu.dynar.network;

import com.maxeu.dynar.particle.ParticleInfo;
import com.maxeu.dynar.particle.ParticleSpawner;
import com.maxeu.dynar.utils.Util;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class NetworkHandler {
    public static final Identifier PARTICLE_INFO = new Identifier(Util.MOD_ID, "particle_info");

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(PARTICLE_INFO, (client, handler, buf, responseSender) -> {

            /* read the position info from the buf */
            /* idk why. but it only works at the top of this method. */
            /* otherwise this will throw an io exception */
            List<long[]> poslist = buf.readList(PacketByteBuf::readLongArray);

            /* read the particle id & particle's extra info from the buf */
            final int id = buf.readInt();
            ParticleType<?> type = Registries.PARTICLE_TYPE.get(id);
            double[] extraInfo;
            if (buf.readByte() == -1) {
                extraInfo = Arrays.stream(buf.readLongArray()).mapToDouble(Double::longBitsToDouble).toArray();
            } else {
                extraInfo = null;
            }
            ParticleInfo particleInfo = new ParticleInfo(type, extraInfo);

            ParticleSpawner.spawnParticle(poslist, particleInfo, client);
        });
    }

    public static void sendParticleInfo(ServerPlayerEntity player, List<long[]> posList, ParticleInfo info) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        /* write the position info to the buf */
        buf.writeCollection(posList, PacketByteBuf::writeLongArray);

        /* write the particle id & particle's extra info to the buf */
        final int id = Registries.PARTICLE_TYPE.getRawId(info.type());
        buf.writeInt(id);
        if (info.hasExtraInfo()) {
            buf.writeByte(-1);
            buf.writeLongArray(info.toLongArray());
        } else {
            buf.writeByte(0);
        }

        ServerPlayNetworking.send(player, PARTICLE_INFO, buf);
    }
}
