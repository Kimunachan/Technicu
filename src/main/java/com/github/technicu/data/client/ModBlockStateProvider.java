package com.github.technicu.data.client;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
import net.minecraft.data.BlockModelFields;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
        super(gen, Technicu.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(ModBlocks.ALLOY_SMELTER.get(), modLoc("block/alloy_smelter_side"), modLoc("block/alloy_smelter_front"), modLoc("block/alloy_smelter_top"));
        simpleBlock(ModBlocks.ALUMINUM_BLOCK.get());
        simpleBlock(ModBlocks.ALUMINUM_ORE.get());
        simpleBlock(ModBlocks.BRONZE_BLOCK.get());
        simpleBlock(ModBlocks.COPPER_BLOCK.get());
        simpleBlock(ModBlocks.COPPER_ORE.get());
        simpleBlock(ModBlocks.ELECTRUM_BLOCK.get());
        horizontalBlock(ModBlocks.ENERGY_PORT.get(), modLoc("block/energy_port_side"), modLoc("block/energy_port_front"), modLoc("block/energy_port_top"));
        simpleBlock(ModBlocks.MACHINE_BLOCK.get());
        horizontalBlock(ModBlocks.MACHINE_CONTROLLER.get(), modLoc("block/machine_controller_side"), modLoc("block/machine_controller_front"), modLoc("block/machine_controller_top"));
        simpleBlock(ModBlocks.NICKEL_BLOCK.get());
        simpleBlock(ModBlocks.NICKEL_ORE.get());
        simpleBlock(ModBlocks.PLATINUM_BLOCK.get());
        simpleBlock(ModBlocks.PLATINUM_ORE.get());
        simpleBlock(ModBlocks.STEEL_BLOCK.get());
        simpleBlock(ModBlocks.TIN_BLOCK.get());
        simpleBlock(ModBlocks.TIN_ORE.get());
    }
}
