package com.maxeu.dynar.command;

import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CommandUtils {
    public static int showPlayers(ServerCommandSource source) {
        World world = source.getWorld();
        PlayerEntity player = source.getPlayer();
        if (world != null && player != null) {
            player.sendMessage(Text.literal(world.getPlayers().toString()));
        }
        return 1;
    }

    public static CompletableFuture<Suggestions> getSuggestions(SuggestionsBuilder builder) {
        final String[] agt = {"type", "notType", "suggest"};
        for (String string : agt) {
            builder.suggest(string);
        }
        return builder.buildFuture();
    }
}