package com.github.technicu.setup;

import com.github.technicu.Technicu;
import com.github.technicu.blocks.alloySmelter.AlloySmelterTileEntity;
import com.github.technicu.blocks.blockBreaker.BlockBreakerTileEntity;
import com.github.technicu.blocks.blockPlacer.BlockPlacerTileEntity;
import com.github.technicu.blocks.charger.ChargerTileEntity;
import com.github.technicu.blocks.cobblestoneGenerator.CobblestoneGeneratorTileEntity;
import com.github.technicu.blocks.energyPort.EnergyPortTileEntity;
import com.github.technicu.blocks.furnaceGenerator.FurnaceGeneratorTileEntity;
import com.github.technicu.blocks.machineController.MachineControllerTileEntity;
import com.github.technicu.blocks.metalPress.MetalPressTileEntity;
import com.github.technicu.blocks.trashCan.TrashCanTileEntity;
import com.github.technicu.blocks.waterMill.WaterMillTileEntity;
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
    public static final RegistryObject<TileEntityType<CobblestoneGeneratorTileEntity>> COBBLESTONE_GENERATOR = register("cobblestone_generator", CobblestoneGeneratorTileEntity::new, ModBlocks.COBBLESTONE_GENERATOR);
    public static final RegistryObject<TileEntityType<MetalPressTileEntity>> METAL_PRESS = register("metal_press", MetalPressTileEntity::new, ModBlocks.METAL_PRESS);
    public static final RegistryObject<TileEntityType<FurnaceGeneratorTileEntity>> FURNACE_GENERATOR = register("furnace_generator", FurnaceGeneratorTileEntity::new, ModBlocks.FURNACE_GENERATOR);
    public static final RegistryObject<TileEntityType<ChargerTileEntity>> CHARGER = register("charger", ChargerTileEntity::new, ModBlocks.FURNACE_GENERATOR);
    public static final RegistryObject<TileEntityType<TrashCanTileEntity>> TRASH_CAN = register("trash_can", TrashCanTileEntity::new, ModBlocks.TRASH_CAN);
    public static final RegistryObject<TileEntityType<BlockPlacerTileEntity>> BLOCK_PLACER = register("block_placer", BlockPlacerTileEntity::new, ModBlocks.BLOCK_PLACER);
    public static final RegistryObject<TileEntityType<BlockBreakerTileEntity>> BLOCK_BREAKER = register("block_breaker", BlockBreakerTileEntity::new, ModBlocks.BLOCK_BREAKER);
    public static final RegistryObject<TileEntityType<WaterMillTileEntity>> WATER_MILL = register("water_mill", WaterMillTileEntity::new, ModBlocks.WATER_MILL);
    public static final RegistryObject<TileEntityType<FluidTankTileEntity>> FLUID_TANK = register("fluid_tank", FluidTankTileEntity::new, ModBlocks.FLUID_TANK);

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
