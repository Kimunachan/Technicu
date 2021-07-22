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
        public static final ITag.INamedTag<Block> ALUMINUM_BLOCK = forge("blocks/aluminum_block");
        public static final ITag.INamedTag<Block> ALUMINUM_ORE = forge("blocks/aluminum_ore");
        public static final ITag.INamedTag<Block> BRONZE_BLOCK = forge("blocks/bronze_block");
        public static final ITag.INamedTag<Block> COPPER_BLOCK = forge("blocks/copper_block");
        public static final ITag.INamedTag<Block> COPPER_ORE = forge("blocks/copper_ore");
        public static final ITag.INamedTag<Block> ELECTRUM_BLOCK = forge("blocks/electrum_block");
        public static final ITag.INamedTag<Block> ENERGY_CABLE = forge("blocks/energy_cable");
        public static final ITag.INamedTag<Block> ENERGY_PORT = forge("blocks/energy_port");
        public static final ITag.INamedTag<Block> FURNACE_GENERATOR = forge("blocks/furnace_generator");
        public static final ITag.INamedTag<Block> MACHINE_BLOCK = forge("blocks/machine_block");
        public static final ITag.INamedTag<Block> MACHINE_CONTROLLER = forge("blocks/machine_controller");
        public static final ITag.INamedTag<Block> METAL_PRESS = forge("blocks/metal_press");
        public static final ITag.INamedTag<Block> NICKEL_BLOCK = forge("blocks/nickel_block");
        public static final ITag.INamedTag<Block> NICKEL_ORE = forge("blocks/nickel_ore");
        public static final ITag.INamedTag<Block> PLATINUM_BLOCK = forge("blocks/platinum_block");
        public static final ITag.INamedTag<Block> PLATINUM_ORE = forge("blocks/platinum_ore");
        public static final ITag.INamedTag<Block> STEEL_BLOCK = forge("blocks/steel_block");
        public static final ITag.INamedTag<Block> STEEL_FENCE = forge("blocks/steel_fence");
        public static final ITag.INamedTag<Block> TIN_BLOCK = forge("blocks/tin_block");
        public static final ITag.INamedTag<Block> TIN_ORE = forge("blocks/tin_ore");

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
        public static final ITag.INamedTag<Item> ALUMINUM_BLOCK = forge("blocks/aluminum_block");
        public static final ITag.INamedTag<Item> ALUMINUM_ORE = forge("blocks/aluminum_ore");
        public static final ITag.INamedTag<Item> BRONZE_BLOCK = forge("blocks/bronze_block");
        public static final ITag.INamedTag<Item> COPPER_BLOCK = forge("blocks/copper_block");
        public static final ITag.INamedTag<Item> COPPER_ORE = forge("blocks/copper_ore");
        public static final ITag.INamedTag<Item> ELECTRUM_BLOCK = forge("blocks/electrum_block");
        public static final ITag.INamedTag<Item> ENERGY_PORT = forge("blocks/energy_port");
        public static final ITag.INamedTag<Item> FURNACE_GENERATOR = forge("blocks/furnace_generator");
        public static final ITag.INamedTag<Item> MACHINE_BLOCK = forge("blocks/machine_block");
        public static final ITag.INamedTag<Item> MACHINE_CONTROLLER = forge("blocks/machine_controller");
        public static final ITag.INamedTag<Item> NICKEL_BLOCK = forge("blocks/nickel_block");
        public static final ITag.INamedTag<Item> NICKEL_ORE = forge("blocks/nickel_ore");
        public static final ITag.INamedTag<Item> PLATINUM_BLOCK = forge("blocks/platinum_block");
        public static final ITag.INamedTag<Item> PLATINUM_ORE = forge("blocks/platinum_ore");
        public static final ITag.INamedTag<Item> STEEL_BLOCK = forge("blocks/steel_block");
        public static final ITag.INamedTag<Item> STEEL_FENCE = forge("blocks/steel_fence");
        public static final ITag.INamedTag<Item> TIN_BLOCK = forge("blocks/tin_block");
        public static final ITag.INamedTag<Item> TIN_ORE = forge("blocks/tin_ore");

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
