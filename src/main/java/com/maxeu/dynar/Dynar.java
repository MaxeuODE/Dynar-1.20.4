package com.maxeu.dynar;

import com.maxeu.dynar.command.ParticleCommand;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dynar implements ModInitializer {
    public static final String MOD_ID = "dynar";
	public static final Logger LOGGER = LoggerFactory.getLogger("dynar");

//	public static final Identifier NETWORK_PARTICLE = new Identifier(MOD_ID, "PARTICLE");

	@Override
	public void onInitialize() {
//		ItemRegister.onRegistry();
//		CustomCommand.addCommand();
		ParticleCommand.particleCommand();
	}
}