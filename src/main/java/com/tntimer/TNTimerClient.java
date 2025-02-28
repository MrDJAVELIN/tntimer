package com.tntimer;

import net.fabricmc.api.ClientModInitializer;

public class TNTimerClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("\nClient initialized!\n");
    }
}
