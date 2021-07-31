package com.github.technicu.recipes.advancedAlloy;

import com.github.technicu.recipes.alloy.ModAlloyingRecipe;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ModAdvancedAlloyingRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ModAdvancedAlloyingRecipe>
{
    @Override
    public ModAdvancedAlloyingRecipe fromJson(ResourceLocation recipeId, JsonObject json)
    {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
        Ingredient input = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "input"));

        return new ModAdvancedAlloyingRecipe(recipeId, input, output);
    }

    @Nullable
    @Override
    public ModAdvancedAlloyingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer data)
    {
        ItemStack output = data.readItem();
        Ingredient input = Ingredient.fromNetwork(data);

        return new ModAdvancedAlloyingRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(PacketBuffer data, ModAdvancedAlloyingRecipe recipe)
    {
        Ingredient input = recipe.getIngredients().get(0);
        input.toNetwork(data);

        data.writeItemStack(recipe.getResultItem(), false);
    }
}
