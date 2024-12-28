package com.vlyh.procrafter.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class ModEvents {
    public static void registerEvents() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            System.out.println("Server tick event triggered!");
        });
    }
}
