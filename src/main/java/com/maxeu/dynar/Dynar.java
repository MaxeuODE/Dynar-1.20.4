package com.maxeu.dynar;

import com.maxeu.dynar.command.ClientParticleCommand;
import com.maxeu.dynar.command.ParticleCommand;
import com.maxeu.dynar.command.ParticleSettingCommand;
import com.maxeu.dynar.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;

public class Dynar implements ModInitializer {
    @Override
    public void onInitialize() {
        ParticleCommand.init();
        ClientParticleCommand.init();
        NetworkHandler.init();
        ParticleSettingCommand.init();
    }
}