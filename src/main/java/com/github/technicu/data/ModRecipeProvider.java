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

        ShapelessRecipeBuilder.shapeless(ModItems.ALUMINUM_INGOT.get(), 9).requires(ModBlocks.ALUMINUM_BLOCK.get()).unlockedBy("has_item", has(ModItems.ALUMINUM_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.BRONZE_INGOT.get(), 9).requires(ModBlocks.BRONZE_BLOCK.get()).unlockedBy("has_item", has(ModItems.BRONZE_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.COPPER_INGOT.get(), 9).requires(ModBlocks.COPPER_BLOCK.get()).unlockedBy("has_item", has(ModItems.COPPER_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.ELECTRUM_INGOT.get(), 9).requires(ModBlocks.ELECTRUM_BLOCK.get()).unlockedBy("has_item", has(ModItems.ELECTRUM_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.NICKEL_INGOT.get(), 9).requires(ModBlocks.NICKEL_BLOCK.get()).unlockedBy("has_item", has(ModItems.NICKEL_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.PLATINUM_INGOT.get(), 9).requires(ModBlocks.PLATINUM_BLOCK.get()).unlockedBy("has_item", has(ModItems.PLATINUM_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.STEEL_INGOT.get(), 9).requires(ModBlocks.STEEL_BLOCK.get()).unlockedBy("has_item", has(ModItems.STEEL_INGOT.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(ModItems.TIN_INGOT.get(), 9).requires(ModBlocks.TIN_BLOCK.get()).unlockedBy("has_item", has(ModItems.TIN_INGOT.get())).save(consumer);

        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.ALUMINUM_ORE.get()), ModItems.ALUMINUM_INGOT.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.ALUMINUM_ORE.get())).save(consumer, modid("aluminium_ore_blasting"));
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.COPPER_ORE.get()), ModItems.COPPER_INGOT.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.COPPER_ORE.get())).save(consumer, modid("copper_ore_blasting"));
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.NICKEL_ORE.get()), ModItems.NICKEL_INGOT.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.NICKEL_ORE.get())).save(consumer, modid("nickel_ore_blasting"));
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.PLATINUM_ORE.get()), ModItems.PLATINUM_INGOT.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.PLATINUM_ORE.get())).save(consumer, modid("platinum_ore_blasting"));
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.TIN_ORE.get()), ModItems.TIN_INGOT.get(), 0.7F, 100).unlockedBy("has_item", has(ModBlocks.TIN_ORE.get())).save(consumer, modid("tin_ore_blasting"));
        
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.ALUMINUM_ORE.get()), ModItems.ALUMINUM_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.ALUMINUM_ORE.get())).save(consumer, modid("aluminium_ore_smelting"));
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.COPPER_ORE.get()), ModItems.COPPER_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.COPPER_ORE.get())).save(consumer, modid("copper_ore_smelting"));
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.NICKEL_ORE.get()), ModItems.NICKEL_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.NICKEL_ORE.get())).save(consumer, modid("nickel_ore_smelting"));
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.PLATINUM_ORE.get()), ModItems.PLATINUM_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.PLATINUM_ORE.get())).save(consumer, modid("platinum_ore_smelting"));
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.TIN_ORE.get()), ModItems.TIN_INGOT.get(), 0.7F, 200).unlockedBy("has_item", has(ModBlocks.TIN_ORE.get())).save(consumer, modid("tin_ore_smelting"));
        
        ShapedRecipeBuilder.shaped(ModBlocks.ALUMINUM_BLOCK.get()).define('#', ModItems.ALUMINUM_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.ALUMINUM_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.BRONZE_BLOCK.get()).define('#', ModItems.BRONZE_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.BRONZE_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.COPPER_BLOCK.get()).define('#', ModItems.COPPER_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.COPPER_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.ELECTRUM_BLOCK.get()).define('#', ModItems.ELECTRUM_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.ELECTRUM_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.NICKEL_BLOCK.get()).define('#', ModItems.NICKEL_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.NICKEL_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.PLATINUM_BLOCK.get()).define('#', ModItems.PLATINUM_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.PLATINUM_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.STEEL_BLOCK.get()).define('#', ModItems.STEEL_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.STEEL_INGOT.get())).save(consumer);
        ShapedRecipeBuilder.shaped(ModBlocks.TIN_BLOCK.get()).define('#', ModItems.TIN_INGOT.get()).pattern("###").pattern("###").pattern("###").unlockedBy("has_item", has(ModItems.TIN_INGOT.get())).save(consumer);

        //SmithingRecipeBuilder.smithing(Ingredient.of(Items.DIAMOND_AXE), Ingredient.of(ModItems.RUBY.get()), ModItems.RUBY_AXE.get()).unlocks("has_item", has(ModItems.RUBY.get())).save(consumer, "smithing_ruby_axe");
    }

    private static ResourceLocation modid(String path)
    {
        return new ResourceLocation(Technicu.MOD_ID, path);
    }
}








