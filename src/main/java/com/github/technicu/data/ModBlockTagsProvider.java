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
        //<editor-fold desc="Fence">
        tag(ModTags.Blocks.FENCE).add(ModBlocks.STEEL_FENCE.get());
        tag(ModTags.Blocks.FENCE).add(ModBlocks.COPPER_FENCE.get());
        //</editor-fold>

        //<editor-fold desc="Fence Gate">
        tag(ModTags.Blocks.FENCE_GATE).add(ModBlocks.STEEL_FENCE_GATE.get());
        tag(ModTags.Blocks.FENCE_GATE).add(ModBlocks.COPPER_FENCE_GATE.get());
        //</editor-fold>
    }
}
