package com.maxeu.dynar;

import com.maxeu.dynar.command.ParticleCommand;
import com.maxeu.dynar.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dynar implements ModInitializer {
    public static final String MOD_ID = "dynar";
    public static final Logger LOGGER = LoggerFactory.getLogger("dynar");

    @Override
    public void onInitialize() {
        ParticleCommand.particleCommand();
        NetworkHandler.init();
    }
}