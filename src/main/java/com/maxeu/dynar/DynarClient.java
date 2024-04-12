package com.maxeu.dynar;

import com.maxeu.dynar.config.ModConfig;
import com.maxeu.dynar.network.NetworkHandler;
import net.fabricmc.api.ClientModInitializer;

public class DynarClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        NetworkHandler.init();
        ModConfig.init();
    }
}
