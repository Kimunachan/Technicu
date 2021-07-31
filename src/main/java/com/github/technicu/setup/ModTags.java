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
        public static final ITag.INamedTag<Block> FENCE = mod("blocks/fence");
        public static final ITag.INamedTag<Block> FENCE_GATE = mod("blocks/fence_gate");

        //<editor-fold desc="Magie">
        private static ITag.INamedTag<Block> forge(String path)
        {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Block> mod(String path)
        {
            return BlockTags.bind(new ResourceLocation(Technicu.MOD_ID, path).toString());
        }
        //</editor-fold>
    }

    public static final class Items
    {
        //Blocks
        public static final ITag.INamedTag<Item> FENCE = mod("blocks/fence");
        public static final ITag.INamedTag<Item> FENCE_GATE = mod("blocks/fence_gate");

        //Items
        public static final ITag.INamedTag<Item> INGOTS = forge("items/ingots");
        public static final ITag.INamedTag<Item> RODS = forge("items/rods");
        public static final ITag.INamedTag<Item> PLATES = forge("items/plates");

        //<editor-fold desc="Magie">
        private static ITag.INamedTag<Item> forge(String path)
        {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static ITag.INamedTag<Item> mod(String path)
        {
            return ItemTags.bind(new ResourceLocation(Technicu.MOD_ID, path).toString());
        }
        //</editor-fold>
    }
}
