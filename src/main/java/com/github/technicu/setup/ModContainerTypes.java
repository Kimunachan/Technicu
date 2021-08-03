package com.github.technicu.setup;

import com.github.technicu.blocks.alloySmelter.AlloySmelterContainer;
import com.github.technicu.blocks.blockBreaker.BlockBreakerContainer;
import com.github.technicu.blocks.blockPlacer.BlockPlacerContainer;
import com.github.technicu.blocks.charger.ChargerContainer;
import com.github.technicu.blocks.cobblestoneGenerator.CobblestoneGeneratorContainer;
import com.github.technicu.blocks.energyPort.EnergyPortContainer;
import com.github.technicu.blocks.energyPort.EnergyPortScreen;
import com.github.technicu.blocks.furnaceGenerator.FurnaceGeneratorContainer;
import com.github.technicu.blocks.machineController.MachineControllerContainer;
import com.github.technicu.blocks.metalPress.MetalPressContainer;
import com.github.technicu.blocks.trashCan.TrashCanContainer;
import com.github.technicu.blocks.waterMill.WaterMillContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.network.IContainerFactory;

public class ModContainerTypes
{

    public static final RegistryObject<ContainerType<MachineControllerContainer>> MACHINE_CONTROLLER = register("machine_container_basic", MachineControllerContainer::new);
    public static final RegistryObject<ContainerType<AlloySmelterContainer>> ALLOY_SMELTER = register("alloy_smelter", AlloySmelterContainer::new);
    public static final RegistryObject<ContainerType<CobblestoneGeneratorContainer>> COBBLESTONE_GENERATOR = register("cobblestone_generator", CobblestoneGeneratorContainer::new);
    public static final RegistryObject<ContainerType<EnergyPortContainer>> ENERGY_PORT = register("energy_port", EnergyPortContainer::new);
    public static final RegistryObject<ContainerType<MetalPressContainer>> METAL_PRESS = register("metal_press", MetalPressContainer::new);
    public static final RegistryObject<ContainerType<FurnaceGeneratorContainer>> FURNACE_GENERATOR = register("furnace_generator", FurnaceGeneratorContainer::new);
    public static final RegistryObject<ContainerType<ChargerContainer>> CHARGER = register("charger", ChargerContainer::new);
    public static final RegistryObject<ContainerType<TrashCanContainer>> TRASH_CAN = register("trash_can", TrashCanContainer::new);
    public static final RegistryObject<ContainerType<BlockPlacerContainer>> BLOCK_PLACER = register("block_placer", BlockPlacerContainer::new);
    public static final RegistryObject<ContainerType<BlockBreakerContainer>> BLOCK_BREAKER = register("block_breaker", BlockBreakerContainer::new);
    public static final RegistryObject<ContainerType<WaterMillContainer>> WATER_MILL = register("water_mill", WaterMillContainer::new);

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
