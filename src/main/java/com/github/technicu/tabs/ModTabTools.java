package com.github.technicu.tabs;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModTabTools extends ItemGroup
{
    public ModTabTools(String label)
    {
        super(Technicu.MOD_ID + "." + label);
    }

    @Override
    public ItemStack makeIcon()
    {
        return ModBlocks.MACHINE_BLOCK.get().asItem().getDefaultInstance();
    }
}
