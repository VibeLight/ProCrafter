package com.vlyh.procrafter.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModConfig {
    public static boolean enableMagic = true;
    public static int maxEnergy = 1000;

    public static void loadConfig() {
        Path configPath = Paths.get("config/procrafter_config.json");
        if (!Files.exists(configPath)) {
            saveDefaultConfig(configPath);
        }
        // JSON読み込み処理を追加
    }

    private static void saveDefaultConfig(Path path) {
        // 初期値を保存
    }
}
