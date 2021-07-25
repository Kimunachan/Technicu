package com.github.technicu.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HammerItem extends Item
{
    public HammerItem(Properties properties)
    {
        super(properties);

        properties.stacksTo(1);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return this.getDefaultInstance();
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
