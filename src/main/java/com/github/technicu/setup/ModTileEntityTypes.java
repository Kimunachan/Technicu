package com.github.technicu.setup;

import com.github.technicu.Technicu;
import com.github.technicu.blocks.machineController.MachineControllerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModTileEntityTypes
{
    //-------------------------
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Technicu.MOD_ID);
    //-------------------------

    public static final RegistryObject<TileEntityType<MachineControllerTileEntity>> MACHINE_CONTROLLER = register("machine_controller", MachineControllerTileEntity::new, ModBlocks.MACHINE_CONTROLLER);

    static void register()
    {}

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register
            (String name, Supplier<T> factory, RegistryObject<? extends Block> block)
    {
        return Registration.TILE_ENTITIES.register(name, () ->
        {
            return TileEntityType.Builder.of(factory, block.get()).build(null);
        });
    }
}
