package com.vlyh.procrafter.registry;

import com.vlyh.procrafter.items.ModItems;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item MAGIC_WAND = new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
    public static final Item MAGIC_SWORD = new SwordItem(ToolMaterials.IRON, 10, -2.4f, new FabricItemSettings().group(ItemGroup.COMBAT));
    
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier("procrafter", "magic_wand"), MAGIC_WAND);
        Registry.register(Registry.ITEM, new Identifier("procrafter", "magic_sword"), MAGIC_SWORD);
    }
}
