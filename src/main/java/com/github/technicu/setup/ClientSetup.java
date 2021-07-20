package com.github.technicu.setup;

import com.github.technicu.Technicu;
import com.github.technicu.blocks.machineController.MachineControllerBasicScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Technicu.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event){
        ScreenManager.register(ModContainerTypes.MACHINE_CONTROLLER_BASIC.get(), MachineControllerBasicScreen::new);
    }
}
