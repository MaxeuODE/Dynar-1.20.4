package com.maxeu.dynar;

import com.maxeu.dynar.registry.ItemRegister;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Dynar implements ModInitializer {
    public static final String MOD_ID = "dynar";
	public static final Logger LOGGER = LoggerFactory.getLogger("dynar");

	@Override
	public void onInitialize() {
		ItemRegister.onRegistry();
	}
}