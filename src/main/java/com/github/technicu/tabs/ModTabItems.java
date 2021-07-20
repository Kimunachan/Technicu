package com.github.technicu.tabs;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTabItems extends ItemGroup
{
    public ModTabItems(String label)
    {
        super(Technicu.MOD_ID + "." + label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return ModItems.COPPER_INGOT.get().asItem().getDefaultInstance();
    }
}
