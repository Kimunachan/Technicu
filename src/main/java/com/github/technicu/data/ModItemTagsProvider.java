package com.github.technicu.data;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
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
    protected void addTags()
    {
        copy(ModTags.Blocks.FENCE, ModTags.Items.FENCE);

        //<editor-fold desc="Ingots">
        tag(ModTags.Items.INGOTS).add(ModItems.ALUMINUM_INGOT.get());
        tag(ModTags.Items.INGOTS).add(ModItems.BRONZE_INGOT.get());
        tag(ModTags.Items.INGOTS).add(ModItems.COPPER_INGOT.get());
        tag(ModTags.Items.INGOTS).add(ModItems.ELECTRUM_INGOT.get());
        tag(ModTags.Items.INGOTS).add(ModItems.NICKEL_INGOT.get());
        tag(ModTags.Items.INGOTS).add(ModItems.PLATINUM_INGOT.get());
        tag(ModTags.Items.INGOTS).add(ModItems.STEEL_INGOT.get());
        tag(ModTags.Items.INGOTS).add(ModItems.TIN_INGOT.get());
        //</editor-fold>

        //<editor-fold desc="Rods">
        tag(ModTags.Items.RODS).add(ModItems.ALUMINUM_ROD.get());
        tag(ModTags.Items.RODS).add(ModItems.BRONZE_ROD.get());
        tag(ModTags.Items.RODS).add(ModItems.COPPER_ROD.get());
        tag(ModTags.Items.RODS).add(ModItems.ELECTRUM_ROD.get());
        tag(ModTags.Items.RODS).add(ModItems.NICKEL_ROD.get());
        tag(ModTags.Items.RODS).add(ModItems.PLATINUM_ROD.get());
        tag(ModTags.Items.RODS).add(ModItems.STEEL_ROD.get());
        tag(ModTags.Items.RODS).add(ModItems.TIN_ROD.get());
        //</editor-fold>

        //<editor-fold desc="Plates">
        tag(ModTags.Items.PLATES).add(ModItems.ALUMINUM_PLATE.get());
        tag(ModTags.Items.PLATES).add(ModItems.BRONZE_PLATE.get());
        tag(ModTags.Items.PLATES).add(ModItems.COPPER_PLATE.get());
        tag(ModTags.Items.PLATES).add(ModItems.ELECTRUM_PLATE.get());
        tag(ModTags.Items.PLATES).add(ModItems.NICKEL_PLATE.get());
        tag(ModTags.Items.PLATES).add(ModItems.PLATINUM_PLATE.get());
        tag(ModTags.Items.PLATES).add(ModItems.STEEL_PLATE.get());
        tag(ModTags.Items.PLATES).add(ModItems.TIN_PLATE.get());
        //</editor-fold>
    }
}
