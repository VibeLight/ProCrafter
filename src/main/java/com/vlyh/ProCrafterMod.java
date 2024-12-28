package com.vlyh.procrafter;

import com.vlyh.procrafter.registry.ModBlocks;
import com.vlyh.procrafter.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class ProCrafterMod implements ModInitializer {
    public static final String MOD_ID = "procrafter";

    @Override
    public void onInitialize() {
        // アイテムやブロックの登録
        ModItems.registerItems();
        ModBlocks.registerBlocks();

        System.out.println("ProCrafter API initialized!");
    }
}
