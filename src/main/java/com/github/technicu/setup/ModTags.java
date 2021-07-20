package com.github.technicu.setup;

import com.github.technicu.Technicu;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ModTags
{
    public static final class Blocks
    {
        //public static final ITag.INamedTag<Block> ORES_COPPER = forge("ores/copper");

        //public static final ITag<Block> STORAGE_BLOCKS_METAL_PRESS = forge("storage_blocks/metal_press");

        private static ITag.INamedTag<Block> forge(String path)
        {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Block> mod(String path)
        {
            return BlockTags.bind(new ResourceLocation(Technicu.MOD_ID, path).toString());
        }
    }

    public static final class Items
    {
        //public static final ITag.INamedTag<Item> ORES_COPPER = forge("ores/copper");

        //public static final ITag.INamedTag<Item> INGOTS_COPPER = forge("ingots/copper");

        private static ITag.INamedTag<Item> forge(String path)
        {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path)
        {
            return ItemTags.bind(new ResourceLocation(Technicu.MOD_ID, path).toString());
        }
    }
}
