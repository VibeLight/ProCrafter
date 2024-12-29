package com.vlyh.procrafter.registry.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    // モブの定義
    public static final EntityType<ZombieEntity> MAGIC_ZOMBIE = Registry.register(
        Registry.ENTITY_TYPE, new Identifier("procrafter", "magic_zombie"),
        EntityType.Builder.create(ZombieEntity::new, EntityCategory.MONSTER).build("magic_zombie")
    );

    // 登録処理
    public static void registerEntities() {
        // 既に登録されているので、ここでは直接呼び出しのみ
    }
}
