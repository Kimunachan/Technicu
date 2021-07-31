package com.github.technicu.recipes.smelting;

import com.github.technicu.recipes.pressing.ModPressingRecipe;
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

public class ModSmeltingRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ModSmeltingRecipe>
{
    @Override
    public ModSmeltingRecipe fromJson(ResourceLocation recipeId, JsonObject json)
    {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
        Ingredient input = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "input"));

        return new ModSmeltingRecipe(recipeId, input, output);
    }

    @Nullable
    @Override
    public ModSmeltingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer data)
    {
        ItemStack output = data.readItem();
        Ingredient input = Ingredient.fromNetwork(data);

        return new ModSmeltingRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(PacketBuffer data, ModSmeltingRecipe recipe)
    {
        Ingredient input = recipe.getIngredients().get(0);
        input.toNetwork(data);

        data.writeItemStack(recipe.getResultItem(), false);
    }
}
