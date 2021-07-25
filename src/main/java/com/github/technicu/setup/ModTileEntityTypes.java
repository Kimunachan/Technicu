package com.github.technicu.setup;

import com.github.technicu.Technicu;
import com.github.technicu.blocks.alloySmelter.AlloySmelterTileEntity;
import com.github.technicu.blocks.charger.ChargerTileEntity;
import com.github.technicu.blocks.energyPort.EnergyPortTileEntity;
import com.github.technicu.blocks.furnaceGenerator.FurnaceGeneratorTileEntity;
import com.github.technicu.blocks.machineController.MachineControllerTileEntity;
import com.github.technicu.blocks.metalPress.MetalPressTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModTileEntityTypes {
    //-------------------------
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Technicu.MOD_ID);
    //-------------------------

    public static final RegistryObject<TileEntityType<MachineControllerTileEntity>> MACHINE_CONTROLLER = register("machine_controller", MachineControllerTileEntity::new, ModBlocks.MACHINE_CONTROLLER);
    public static final RegistryObject<TileEntityType<EnergyPortTileEntity>> ENERGY_PORT = register("energy_port", EnergyPortTileEntity::new, ModBlocks.ENERGY_PORT);
    public static final RegistryObject<TileEntityType<AlloySmelterTileEntity>> ALLOY_SMELTER = register("alloy_smelter", AlloySmelterTileEntity::new, ModBlocks.ALLOY_SMELTER);
    public static final RegistryObject<TileEntityType<MetalPressTileEntity>> METAL_PRESS = register("metal_press", MetalPressTileEntity::new, ModBlocks.METAL_PRESS);
    public static final RegistryObject<TileEntityType<FurnaceGeneratorTileEntity>> FURNACE_GENERATOR = register("furnace_generator", FurnaceGeneratorTileEntity::new, ModBlocks.FURNACE_GENERATOR);
    public static final RegistryObject<TileEntityType<ChargerTileEntity>> CHARGER = register("charger", ChargerTileEntity::new, ModBlocks.FURNACE_GENERATOR);

    static void register() {
    }

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register
            (String name, Supplier<T> factory, RegistryObject<? extends Block> block) {
        return Registration.TILE_ENTITIES.register(name, () ->
        {
            return TileEntityType.Builder.of(factory, block.get()).build(null);
        });
    }
}
