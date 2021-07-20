package com.github.technicu.data;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider
{
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, Technicu.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        copy(ModTags.Blocks.ALUMINUM_BLOCK, ModTags.Items.ALUMINUM_BLOCK);
        copy(ModTags.Blocks.ALUMINUM_ORE, ModTags.Items.ALUMINUM_ORE);
        copy(ModTags.Blocks.BRONZE_BLOCK, ModTags.Items.BRONZE_BLOCK);
        copy(ModTags.Blocks.COPPER_BLOCK, ModTags.Items.COPPER_BLOCK);
        copy(ModTags.Blocks.COPPER_ORE, ModTags.Items.COPPER_ORE);
        copy(ModTags.Blocks.ELECTRUM_BLOCK, ModTags.Items.ELECTRUM_BLOCK);
        copy(ModTags.Blocks.MACHINE_BLOCK, ModTags.Items.MACHINE_BLOCK);
        copy(ModTags.Blocks.MACHINE_CONTROLLER, ModTags.Items.MACHINE_CONTROLLER);
        copy(ModTags.Blocks.MACHINE_ENERGY_PORT, ModTags.Items.MACHINE_ENERGY_PORT);
        copy(ModTags.Blocks.NICKEL_BLOCK, ModTags.Items.NICKEL_BLOCK);
        copy(ModTags.Blocks.NICKEL_ORE, ModTags.Items.NICKEL_ORE);
        copy(ModTags.Blocks.PLATINUM_BLOCK, ModTags.Items.PLATINUM_BLOCK);
        copy(ModTags.Blocks.PLATINUM_ORE, ModTags.Items.PLATINUM_ORE);
        copy(ModTags.Blocks.STEEL_BLOCK, ModTags.Items.STEEL_BLOCK);
        copy(ModTags.Blocks.TIN_BLOCK, ModTags.Items.TIN_BLOCK);
        copy(ModTags.Blocks.TIN_ORE, ModTags.Items.TIN_ORE);

        //tag(ModTags.Items.EXAMPLE_ITEM).add(ModItems.EXAMPLE_ITEM.get());
    }
}
