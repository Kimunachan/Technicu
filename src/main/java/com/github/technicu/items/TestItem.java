package com.github.technicu.items;

import com.github.technicu.setup.ModTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class TestItem extends Item
{
    public TestItem(Properties properties)
    {
        super(properties);
    }


    @SuppressWarnings("deprecation")
    @Override
    public boolean hasCraftingRemainingItem()
    {
        return true;
    }
}
