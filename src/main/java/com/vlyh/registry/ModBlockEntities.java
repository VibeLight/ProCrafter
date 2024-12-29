package com.vlyh.procrafter.registry;

import com.vlyh.procrafter.blocks.entity.MagicMachineBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static final BlockEntityType<MagicMachineBlockEntity> MAGIC_MACHINE_ENTITY = Registry.register(
        Registry.BLOCK_ENTITY_TYPE,
        new Identifier("procrafter", "magic_machine"),
        BlockEntityType.Builder.create(MagicMachineBlockEntity::new, ModBlocks.MAGIC_MACHINE_BLOCK).build(null)
    );

    public static void registerBlockEntities() {
        // BlockEntityTypeの登録はModBlocksで行っているため、ここでは特に呼び出しなし
    }
}
