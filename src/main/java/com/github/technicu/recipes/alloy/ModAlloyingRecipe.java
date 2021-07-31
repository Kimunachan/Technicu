package com.github.technicu.recipes.alloy;

import com.github.technicu.recipes.charging.IChargingRecipe;
import com.github.technicu.setup.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ModAlloyingRecipe implements IChargingRecipe
{
    private final ResourceLocation id;
    private Ingredient input;
    private final ItemStack output;

    public ModAlloyingRecipe(ResourceLocation id, Ingredient input, ItemStack output)
    {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World world)
    {
        if (this.input.test(inv.getItem(0)))
        {
            return true;
        }

        return false;
    }

    @Override
    public ItemStack assemble(RecipeWrapper inv)
    {
        return this.output;
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_)
    {
        return false;
    }

    @Override
    public Ingredient getInput()
    {
        return this.input;
    }

    @Override
    public ItemStack getResultItem()
    {
        return this.output;
    }

    @Override
    public ResourceLocation getId()
    {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return ModRecipes.ALLOYING_SERIALIZER.get();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(null, this.input);
    }
}
