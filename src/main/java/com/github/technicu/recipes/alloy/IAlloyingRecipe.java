package com.github.technicu.recipes.alloy;

import com.github.technicu.Technicu;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IAlloyingRecipe extends IRecipe<RecipeWrapper>
{
    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(Technicu.MOD_ID, "alloying");

    @Override
    default IRecipeType<?> getType()
    {
        return Registry.RECIPE_TYPE.get(RECIPE_TYPE_ID);
    }

    @Override
    default boolean canCraftInDimensions(int width, int height)
    {
        return false;
    }

    Ingredient getInput();
}
