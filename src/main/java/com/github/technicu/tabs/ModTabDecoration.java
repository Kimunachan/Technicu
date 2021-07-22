package com.github.technicu.tabs;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTabDecoration extends ItemGroup
{
    public ModTabDecoration(String label)
    {
        super(Technicu.MOD_ID + "." + label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return ModBlocks.STEEL_FENCE.get().asItem().getDefaultInstance();
    }
}
