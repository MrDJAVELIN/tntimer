package com.tntimer;

import com.tntimer.commands.TNTCommand;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TNTimerMod implements ModInitializer {
	public static final String MOD_ID = "tntimer";

	public static int globalTntFuseTicks = 80;
	public static int TntFuseTicks = 80;

	@Override
	public void onInitialize() {
		System.out.println("TNTimer loaded.");
		TNTCommand.register();

	}
}