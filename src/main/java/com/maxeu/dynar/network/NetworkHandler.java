package com.maxeu.dynar.network;

import com.maxeu.dynar.Dynar;
import com.maxeu.dynar.particle.ParticleSpawner;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

public class NetworkHandler {
    public static final Identifier POSITION = new Identifier(Dynar.MOD_ID, "pos");

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(POSITION, (client, handler, buf, responseSender) ->
                ParticleSpawner.spawnCommonParticle(buf, client));
    }

    public static void sendLongList(ServerPlayerEntity player, List<long[]> vec) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeCollection(vec, PacketByteBuf::writeLongArray);
        ServerPlayNetworking.send(player, POSITION, buf);
    }
}
