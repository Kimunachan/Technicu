package com.github.technicu.setup;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModRecipes
{
    public static class Types
    {
        //public static final IRecipeType<PressingRecipe> PRESSING = IRecipeType.register(tECHNICU.MOD_ID + "pressing");
    }

    public static  class Serializers
    {
        //public static RegistryObject<IRecipeSerializer<PressingRecipe>> PRESSING = register("pressing", PressingRecipe.Serializer::new);

        public static <T extends IRecipe<?>>RegistryObject<IRecipeSerializer<T>> register
                (String name, Supplier<IRecipeSerializer<T>> serializer)
        {
            return Registration.RECIPE_SERIALIZERS.register(name, serializer);
        }
    }

    static void register()
    {}
}
