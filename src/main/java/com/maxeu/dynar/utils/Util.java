package com.maxeu.dynar.utils;

import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * Util class for general constants and methods
 */
public class Util {
    public static final String MOD_ID = "dynar";
    public static final Logger LOGGER = LoggerFactory.getLogger("dynar");

    /**
     * <p>Used to send a log info for debug.
     * <p>用于发送调试信息
     * @param message the message to be sent
     * @param <T> any parameter
     */
    public static <T> void sendLogInfo(T message) {
        LOGGER.info(message.toString());
    }

    /**
     * <p>Used to generate a command suggestion using {@code int...} value
     * <p>用于根据{@code int...}值生成命令建议
     * @param builder the {@link SuggestionsBuilder}
     * @param value the value array
     * @return the completed suggestions
     */
    public static CompletableFuture<Suggestions> buildSuggestions(SuggestionsBuilder builder, int... value) {
        for (int i : value) {
            builder.suggest(i);
        }
        return builder.buildFuture();
    }

    /**
     * <p>Used to generate a command suggestion using {@link String}... value
     * <p>用于根据{@link String}...值生成命令建议
     * @param builder the {@link SuggestionsBuilder}
     * @param value the value array
     * @return the completed suggestions
     */
    public static CompletableFuture<Suggestions> buildSuggestions(SuggestionsBuilder builder, String... value) {
        for (String s : value) {
            builder.suggest(s);
        }
        return builder.buildFuture();
    }
}
