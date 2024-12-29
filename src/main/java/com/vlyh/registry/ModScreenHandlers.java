package com.vlyh.procrafter.registry;

import com.vlyh.procrafter.screen.MagicMachineScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModScreenHandlers {
    public static final ScreenHandlerType<MagicMachineScreenHandler> MAGIC_MACHINE_SCREEN_HANDLER = new ScreenHandlerType<>(MagicMachineScreenHandler::new);
    
    public static void registerScreenHandlers() {
        Registry.register(Registry.SCREEN_HANDLER, new Identifier("procrafter", "magic_machine_screen_handler"), MAGIC_MACHINE_SCREEN_HANDLER);
    }
}
