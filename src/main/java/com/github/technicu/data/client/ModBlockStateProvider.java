package com.github.technicu.data.client;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider
{
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper)
    {
        super(gen, Technicu.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        simpleBlock(ModBlocks.ALUMINUM_BLOCK.get());
        simpleBlock(ModBlocks.ALUMINUM_ORE.get());
        simpleBlock(ModBlocks.BRONZE_BLOCK.get());
        simpleBlock(ModBlocks.COPPER_BLOCK.get());
        simpleBlock(ModBlocks.COPPER_ORE.get());
        simpleBlock(ModBlocks.ELECTRUM_BLOCK.get());
        simpleBlock(ModBlocks.MACHINE_BLOCK.get());
        simpleBlock(ModBlocks.MACHINE_CONTROLLER.get());
        simpleBlock(ModBlocks.MACHINE_ENERGY_PORT.get());
        simpleBlock(ModBlocks.NICKEL_BLOCK.get());
        simpleBlock(ModBlocks.NICKEL_ORE.get());
        simpleBlock(ModBlocks.PLATINUM_BLOCK.get());
        simpleBlock(ModBlocks.PLATINUM_ORE.get());
        simpleBlock(ModBlocks.STEEL_BLOCK.get());
        simpleBlock(ModBlocks.TIN_BLOCK.get());
        simpleBlock(ModBlocks.TIN_ORE.get());
    }
}
