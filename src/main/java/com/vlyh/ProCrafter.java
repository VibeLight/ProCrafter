package com.vlyh.procrafter;

import com.vlyh.procrafter.registry.ModBlocks;
import com.vlyh.procrafter.registry.ModItems;
import com.vlyh.procrafter.registry.ModEntities;
import com.vlyh.procrafter.registry.ModDimensions;
import com.vlyh.procrafter.registry.ModSound;
import net.fabricmc.api.ModInitializer;

public class ProCrafterMod implements ModInitializer {
    public static final String MOD_ID = "procrafter";

@Override
    public void onInitialize() {
        // レジストリを初期化
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModEntities.registerEntities();
        ModSounds.registerSounds();
        ModDimensions.registerDimensions();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();
    }
}
