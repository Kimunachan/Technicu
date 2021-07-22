package com.github.technicu.data;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider
{
    public ModBlockTagsProvider(DataGenerator gen, ExistingFileHelper existingFileHelper)
    {
        super(gen, Technicu.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags()
    {
        tag(ModTags.Blocks.ALUMINUM_BLOCK).add(ModBlocks.ALUMINUM_BLOCK.get());
        tag(ModTags.Blocks.ALUMINUM_ORE).add(ModBlocks.ALUMINUM_ORE.get());
        tag(ModTags.Blocks.BRONZE_BLOCK).add(ModBlocks.BRONZE_BLOCK.get());
        tag(ModTags.Blocks.COPPER_BLOCK).add(ModBlocks.COPPER_BLOCK.get());
        tag(ModTags.Blocks.COPPER_ORE).add(ModBlocks.COPPER_ORE.get());
        tag(ModTags.Blocks.ELECTRUM_BLOCK).add(ModBlocks.ELECTRUM_BLOCK.get());
        tag(ModTags.Blocks.ENERGY_PORT).add(ModBlocks.ENERGY_PORT.get());
        tag(ModTags.Blocks.FURNACE_GENERATOR).add(ModBlocks.FURNACE_GENERATOR.get());
        tag(ModTags.Blocks.MACHINE_BLOCK).add(ModBlocks.MACHINE_BLOCK.get());
        tag(ModTags.Blocks.MACHINE_CONTROLLER).add(ModBlocks.MACHINE_CONTROLLER.get());
        tag(ModTags.Blocks.METAL_PRESS).add(ModBlocks.METAL_PRESS.get());
        tag(ModTags.Blocks.NICKEL_BLOCK).add(ModBlocks.NICKEL_BLOCK.get());
        tag(ModTags.Blocks.NICKEL_ORE).add(ModBlocks.NICKEL_ORE.get());
        tag(ModTags.Blocks.PLATINUM_BLOCK).add(ModBlocks.PLATINUM_BLOCK.get());
        tag(ModTags.Blocks.PLATINUM_ORE).add(ModBlocks.PLATINUM_ORE.get());
        tag(ModTags.Blocks.STEEL_BLOCK).add(ModBlocks.STEEL_BLOCK.get());
        tag(ModTags.Blocks.TIN_BLOCK).add(ModBlocks.TIN_BLOCK.get());
        tag(ModTags.Blocks.TIN_ORE).add(ModBlocks.TIN_ORE.get());
    }
}
