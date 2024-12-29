package com.vlyh.procrafter;

import com.vlyh.procrafter.registry.ModBlocks;
import com.vlyh.procrafter.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class ProCrafterMod implements ModInitializer {
    public static final String MOD_ID = "procrafter";

@Override
    public void onInitialize() {
        // レジストリを初期化
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();
    }
}
