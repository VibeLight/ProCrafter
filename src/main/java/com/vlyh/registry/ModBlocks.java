package com.vlyh.procrafter.registry;

import com.vlyh.procrafter.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block MAGIC_MACHINE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f));
    
    public static final BlockEntityType<MagicMachineBlockEntity> MAGIC_MACHINE_ENTITY = Registry.register(
        Registry.BLOCK_ENTITY_TYPE,
        new Identifier("procrafter", "magic_machine"),
        BlockEntityType.Builder.create(MagicMachineBlockEntity::new, MAGIC_MACHINE_BLOCK).build(null)
    );
    
    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("procrafter", "magic_machine_block"), MAGIC_MACHINE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("procrafter", "magic_machine_block"), new BlockItem(MAGIC_MACHINE_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
    }
    public static final Block CABBAGE = new CustomCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT));

    public static void registerBlocks() {
        Registry.register(Registries.BLOCK, new Identifier("procrafter", "cabbage"), CABBAGE);
    }
    

}
