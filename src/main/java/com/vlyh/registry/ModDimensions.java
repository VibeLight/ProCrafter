package com.vlyh.procrafter.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.EndDimension;

public class ModDimensions {
    // ディメンションの定義
    public static final DimensionType MYSTIC_REALM = DimensionType.register(
        "procrafter:mystic_realm", new Identifier("procrafter", "mystic_realm"), DimensionType.OVERWORLD
    );

    // 登録処理
    public static void registerDimensions() {
        Registry.register(Registry.DIMENSION_TYPE, new Identifier("procrafter", "mystic_realm"), MYSTIC_REALM);
    }
}
