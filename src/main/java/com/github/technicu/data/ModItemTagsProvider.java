package com.github.technicu.data;

import com.github.technicu.Technicu;
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
        //copy(ModTags.Blocks.EXAMPLE_BLOCK, ModTags.Items.EXAMPLE_BLOCK);

        //tag(ModTags.Items.EXAMPLE_ITEM).add(ModItems.EXAMPLE_ITEM.get());
    }
}
