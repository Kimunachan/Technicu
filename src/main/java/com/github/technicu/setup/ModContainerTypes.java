package com.github.technicu.setup;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.network.IContainerFactory;

public class ModContainerTypes
{

    public static final RegistryObject<ContainerType<MachineControllerBasicContainer>> MACHINE_CONTROLLER_BASIC = register("machine_container_basic", MachineControllerBasicContainer::new);

    private static <T extends Container> RegistryObject<ContainerType<T>> register(String name, IContainerFactory<T> factory)
    {
        return Registration.CONTAINERS.register(name, () -> IForgeContainerType.create(factory));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerScreens(FMLClientSetupEvent e)
    {
        //ScreenManager.register(METAL_PRESS.get(), MetalPressScreen::new);
    }

    static void register()
    {}
}
