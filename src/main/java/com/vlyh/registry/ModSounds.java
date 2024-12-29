package com.vlyh.procrafter.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {
    public static final SoundEvent MAGIC_CAST = new SoundEvent(new Identifier("procrafter", "magic_cast"));

    public static void registerSounds() {
        Registry.register(Registry.SOUND_EVENT, new Identifier("procrafter", "magic_cast"), MAGIC_CAST);
    }
}
