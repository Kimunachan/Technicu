package com.github.technicu.recipes;

import com.github.technicu.setup.ModRecipes;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.SingleItemRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ModPressingRecipe extends SingleItemRecipe
{
    public ModPressingRecipe(ResourceLocation recipeId, Ingredient ingredient, ItemStack result)
    {
        super(ModRecipes.Types.PRESSING, ModRecipes.Serializers.PRESSING.get(), recipeId, "", ingredient, result);
    }

    @Override
    public boolean matches(IInventory inv, World world)
    {
        return ingredient.test(inv.getItem(0));
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ModPressingRecipe>
    {
        @Override
        public ModPressingRecipe fromJson(ResourceLocation recipeId, JsonObject json)
        {
            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));

            ResourceLocation itemId = new ResourceLocation(JSONUtils.getAsString(json, "result"));

            int count = JSONUtils.getAsInt(json, "count");

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count);

            return new ModPressingRecipe(recipeId, ingredient, result);
        }

        @Nullable
        @Override
        public ModPressingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer)
        {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);

            ItemStack result = buffer.readItem();

            return new ModPressingRecipe(recipeId, ingredient, result);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, ModPressingRecipe recipe)
        {
            recipe.ingredient.toNetwork(buffer);

            buffer.writeItem(recipe.result);
        }
    }
}
