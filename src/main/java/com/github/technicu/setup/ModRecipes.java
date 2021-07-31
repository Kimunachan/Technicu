package com.github.technicu.setup;

import com.github.technicu.recipes.advancedAlloy.IAdvancedAlloyingRecipe;
import com.github.technicu.recipes.advancedAlloy.ModAdvancedAlloyingRecipe;
import com.github.technicu.recipes.advancedAlloy.ModAdvancedAlloyingRecipeSerializer;
import com.github.technicu.recipes.alloy.IAlloyingRecipe;
import com.github.technicu.recipes.alloy.ModAlloyingRecipe;
import com.github.technicu.recipes.alloy.ModAlloyingRecipeSerializer;
import com.github.technicu.recipes.charging.IChargingRecipe;
import com.github.technicu.recipes.charging.ModChargingRecipe;
import com.github.technicu.recipes.charging.ModChargingRecipeSerializer;
import com.github.technicu.recipes.pressing.IPressingRecipe;
import com.github.technicu.recipes.pressing.ModPressingRecipe;
import com.github.technicu.recipes.pressing.ModPressingRecipeSerializer;
import com.github.technicu.recipes.smelting.ISmeltingRecipe;
import com.github.technicu.recipes.smelting.ModSmeltingRecipe;
import com.github.technicu.recipes.smelting.ModSmeltingRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

public class ModRecipes
{
    public static final IRecipeSerializer<ModChargingRecipe> CHARGING_RECIPE_SERIALIZER = new ModChargingRecipeSerializer();
    public static final IRecipeSerializer<ModPressingRecipe> PRESSING_RECIPE_SERIALIZER = new ModPressingRecipeSerializer();
    public static final IRecipeSerializer<ModSmeltingRecipe> SMELTING_RECIPE_SERIALIZER = new ModSmeltingRecipeSerializer();
    public static final IRecipeSerializer<ModAlloyingRecipe> ALLOYING_RECIPE_SERIALIZER = new ModAlloyingRecipeSerializer();
    public static final IRecipeSerializer<ModAdvancedAlloyingRecipe> ADVANCED_ALLOYING_RECIPE_SERIALIZER = new ModAdvancedAlloyingRecipeSerializer();

    public static final IRecipeType<ModChargingRecipe> CHARGING_TYPE = registerType(IChargingRecipe.RECIPE_TYPE_ID);
    public static final IRecipeType<ModPressingRecipe> PRESSING_TYPE = registerType(IPressingRecipe.RECIPE_TYPE_ID);
    public static final IRecipeType<ModSmeltingRecipe> SMELTING_TYPE = registerType(ISmeltingRecipe.RECIPE_TYPE_ID);
    public static final IRecipeType<ModAlloyingRecipe> ALLOYING_TYPE = registerType(IAlloyingRecipe.RECIPE_TYPE_ID);
    public static final IRecipeType<ModAdvancedAlloyingRecipe> ADVANCED_ALLOYING_TYPE = registerType(IAdvancedAlloyingRecipe.RECIPE_TYPE_ID);

    public static final RegistryObject<IRecipeSerializer<?>> CHARGING_SERIALIZER = Registration.RECIPE_SERIALIZERS.register("charging", () -> CHARGING_RECIPE_SERIALIZER);
    public static final RegistryObject<IRecipeSerializer<?>> PRESSING_SERIALIZER = Registration.RECIPE_SERIALIZERS.register("pressing", () -> PRESSING_RECIPE_SERIALIZER);
    public static final RegistryObject<IRecipeSerializer<?>> SMELTING_SERIALIZER = Registration.RECIPE_SERIALIZERS.register("smelting", () -> SMELTING_RECIPE_SERIALIZER);
    public static final RegistryObject<IRecipeSerializer<?>> ALLOYING_SERIALIZER = Registration.RECIPE_SERIALIZERS.register("alloying", () -> ALLOYING_RECIPE_SERIALIZER);
    public static final RegistryObject<IRecipeSerializer<?>> ADVANCED_ALLOYING_SERIALIZER = Registration.RECIPE_SERIALIZERS.register("advanced_", () -> ADVANCED_ALLOYING_RECIPE_SERIALIZER);

    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T>
    {
        @Override
        public String toString()
        {
            return Registry.RECIPE_TYPE.getKey(this).toString();
        }
    }

    private static <T extends IRecipeType> T registerType(ResourceLocation recipeTypeId)
    {
        return (T) Registry.register(Registry.RECIPE_TYPE, recipeTypeId, new RecipeType<>());
    }

    static void register()
    {

    }
}
