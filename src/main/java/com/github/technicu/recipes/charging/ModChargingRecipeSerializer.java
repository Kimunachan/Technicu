package com.github.technicu.recipes.charging;

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

public class ModChargingRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ModChargingRecipe>
{
    @Override
    public ModChargingRecipe fromJson(ResourceLocation recipeId, JsonObject json)
    {
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "output"), true);
        Ingredient input = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "input"));

        return new ModChargingRecipe(recipeId, input, output);
    }

    @Nullable
    @Override
    public ModChargingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer data)
    {
        ItemStack output = data.readItem();
        Ingredient input = Ingredient.fromNetwork(data);

        return new ModChargingRecipe(recipeId, input, output);
    }

    @Override
    public void toNetwork(PacketBuffer data, ModChargingRecipe recipe)
    {
        Ingredient input = recipe.getIngredients().get(0);
        input.toNetwork(data);

        data.writeItemStack(recipe.getResultItem(), false);
    }
}
