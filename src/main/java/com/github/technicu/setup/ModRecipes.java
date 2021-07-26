package com.github.technicu.setup;

import com.github.technicu.Technicu;
import com.github.technicu.recipes.ModChargingRecipe;
import com.github.technicu.recipes.ModPressingRecipe;
import com.github.technicu.recipes.ModSmeltingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModRecipes
{
    public static class Types
    {
        public static final IRecipeType<ModSmeltingRecipe> SMELTING = IRecipeType.register(Technicu.MOD_ID + "smelting");
        public static final IRecipeType<ModPressingRecipe> PRESSING = IRecipeType.register(Technicu.MOD_ID + "pressing");
        public static final IRecipeType<ModChargingRecipe> CHARGING = IRecipeType.register(Technicu.MOD_ID + "charging");
    }

    public static  class Serializers
    {
        public static RegistryObject<IRecipeSerializer<ModSmeltingRecipe>> SMELTING = register("smelting", ModSmeltingRecipe.Serializer::new);
        public static RegistryObject<IRecipeSerializer<ModPressingRecipe>> PRESSING = register("pressing", ModPressingRecipe.Serializer::new);
        public static RegistryObject<IRecipeSerializer<ModChargingRecipe>> CHARGING = register("charging", ModChargingRecipe.Serializer::new);

        public static <T extends IRecipe<?>>RegistryObject<IRecipeSerializer<T>> register
                (String name, Supplier<IRecipeSerializer<T>> serializer)
        {
            return Registration.RECIPE_SERIALIZERS.register(name, serializer);
        }
    }

    static void register()
    {}
}
