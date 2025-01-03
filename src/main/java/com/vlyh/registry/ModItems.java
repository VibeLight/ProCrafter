package com.vlyh.procrafter.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item MAGIC_DUST = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(64));
    public static final Item NEOMAGIC_DUST = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)/maxCount(64));
    public static final Item GRINGHAM_SMALLORE = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(64));
    public static final Item MAGIC_SWORD = new SwordItem(ToolMaterials.IRON, 10, -2.4f, new FabricItemSettings().group(ItemGroup.COMBAT).maxCount(1));
    public static final Item MAGIC_CHOCO = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).alwaysEdible().build()).maxCount(64));
    public static final Item CABBAGE = new PlaceableCropItem(ModBlocks.CABBAGE, new FabricItemSettings().group(ItemGroup.MATERIALS).maxCount(64));
    public static final Item FIREBALL_MAGICBOOK = new Item(new FAbricItemSettings().group(ItemGroup.MATERIALS).maxCount(64);
    public static final Item WATER_MAGICBOOK = new Item(new FabricItemSettings().group(ItemGroup.MATERIAL).maxCount(64));

    
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("procrafter", "magic_dust"), MAGIC_DUST);
        Registry.register(Registry.ITEM, new Identifier("procrafter", "neomagic_dust"), NEOMAGIC_DUST);
        Registry.register(Registry.ITEM, new Identifier("procrafter", "magic_sword"), MAGIC_SWORD);
        Registry.register(Registry.ITEM, new Identifier("procrafter","gringham_smallore"),GRINGHAM_SMALLORE);
        Registry.register(Registry.TIME, new Identifier("procrafter","magic_choco"),MAGIC_CHOCO);
        Registry.register(Registry.ITEM, new Identifier("procrafter","cabbage"),CABBAGE);
        Registry.register(Registry.ITEM, new Identifier("procrafter","fireball_magicbook"),FIREBALL_MAGICBOOK);
        Registry.register(Registry.ITEM, new Identifier("procrafter","water_magicball"),WATER_MAGICBOOK);
    }
}
