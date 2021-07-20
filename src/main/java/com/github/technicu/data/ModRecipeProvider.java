package com.github.technicu.data;

import com.github.technicu.Technicu;
import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(DataGenerator dataGenerator)
    {
        super(dataGenerator);
    }

    @Override
    public void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
    {

        //ShapelessRecipeBuilder.shapeless(ModItems.COPPER_INGOT.get(), 9).requires(ModBlocks.COPPER_BLOCK.get()).unlockedBy("has_item", has(ModItems.COPPER_INGOT.get())).save(consumer);

        //CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.AMBER_GRAVEL_ORE.get()), ModItems.AMBER.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.AMBER_SAND_ORE.get())).save(consumer, modid("amber_sand_ore_blasting"));

        //ShapedRecipeBuilder.shaped(ModItems.MAGIC_WAND.get()).define('C', ModItems.CRYSTAL_EMPTY.get()).define('S', Items.STICK).pattern("  C").pattern(" S ").pattern("S  ").unlockedBy("has_item", has(ModItems.CRYSTAL_EMPTY.get())).save(consumer);

        //CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.AKASHIC_STONE.get()), ModItems.AKASHIC_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.AKASHIC_STONE.get())).save(consumer, modid("akashic_ingot_smelting"));

        //SmithingRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND_AXE), Ingredient.of(ModItems.RUBY.get()), ModItems.RUBY_AXE.get()).unlocks("has_item", has(ModItems.RUBY.get())).save(consumer, "smithing_ruby_axe");
    }

    private static ResourceLocation modid(String path)
    {
        return new ResourceLocation(Technicu.MOD_ID, path);
    }
}








