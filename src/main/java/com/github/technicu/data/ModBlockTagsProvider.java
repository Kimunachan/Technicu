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
        //tag(ModTags.Blocks.EXAMPLE_BLOCK).add(ModBlocks.EXAMPLE_BLOCK.get());
    }
}
