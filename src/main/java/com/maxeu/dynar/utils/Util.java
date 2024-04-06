package com.maxeu.dynar.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
