package com.maxeu.dynar.command;

import com.maxeu.dynar.particle.ParticleLimits;
import com.maxeu.dynar.utils.Util;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ClientParticleCommand {
    public static void init() {
        particleTweakCommand();
    }

    /**
     * <p>Get the limit of the particle: /dynar-client particleLimit
     * <p>Set the limit of the particle: /dynar-client particleLimit [amount: {@code int}]
     */
    protected static void particleTweakCommand() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("dynar-client")
                    .then(literal("particleLimit")
                            .executes(ClientParticleCommand::getCurrentLimit)
                            .then(argument("amount", IntegerArgumentType.integer(0))
                                    .suggests((context, builder) -> Util.buildSuggestions(builder, 16384, 32768, 65536, 131072))
                                    .executes(ClientParticleCommand::setLimit)
                            )
                    )
//                    .then(literal("")
//                    )
            );
        });
    }

    private static int getCurrentLimit(CommandContext<FabricClientCommandSource> context) {
        context.getSource().getPlayer().sendMessage(Text.literal("Current particle limit is ").append(Text.literal(String.valueOf(ParticleLimits.getParticleLimit()))));
        return 1;
    }

    private static int setLimit(CommandContext<FabricClientCommandSource> context) {
        final int value = getInteger(context, "amount");
        ParticleLimits.setParticleLimit(value);
        context.getSource().getPlayer().sendMessage(Text.literal("The limit has been successfully modified to ").append(Text.literal(String.valueOf(ParticleLimits.getParticleLimit()))));
        context.getSource().getPlayer().sendMessage(Text.literal("Rejoin the world to apply."));
        return 1;
    }
}
