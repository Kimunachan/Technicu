package com.github.technicu.blocks.charger;

import com.github.technicu.Technicu;
import com.github.technicu.capabilities.ModEnergyHandler;
import com.github.technicu.recipes.charging.ModChargingRecipe;
import com.github.technicu.setup.ModRecipes;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ChargerTileEntity extends LockableLootTileEntity
{
    //<editor-fold>
    public static final int WORK_TIME = 1200;

    public static int slots = 1;

    int progress = 0;
    public static final int MAX_ENERGY = 25000;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    //new EnergyStorage(capacity,maxInput,maxOutput,startingEnergy)
    public ChargerTileEntity() {
        super(ModTileEntityTypes.CHARGER.get());
    }

    LazyOptional<IEnergyStorage> energyStorageLazyOptional = LazyOptional.of(()-> new ModEnergyHandler(MAX_ENERGY,0,0,10000));

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityEnergy.ENERGY){
            return energyStorageLazyOptional.cast();
        }

        return super.getCapability(cap, side);
    }


    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        energyStorageLazyOptional.invalidate();
    }

    @Nullable
    public ModChargingRecipe getRecipe(ItemStack item) {
        if (level == null || getItem(0).isEmpty()) {
            return null;
        }

        Set<IRecipe<?>> recipes = findRecipesByType(ModRecipes.CHARGING_TYPE, this.level);

        for (IRecipe<?> iRecipe : recipes)
        {
            ModChargingRecipe recipe = (ModChargingRecipe) iRecipe;

            if (recipe.matches(new RecipeWrapper((IItemHandlerModifiable) this.inventory), this.level))
            {
                return recipe;
            }
        }

        return null;
    }

    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<ModChargingRecipe> typeIn, World world)
    {
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.EMPTY_SET;
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipesByType(IRecipeType<ModChargingRecipe> typeIn)
    {
        ClientWorld world = Minecraft.getInstance().level;

        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType() == typeIn).collect(Collectors.toSet()) : Collections.EMPTY_SET;
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<ModChargingRecipe> typeIn, World world)
    {
        Set<ItemStack> inputs = new HashSet<>();
        Set<IRecipe<?>> recipes = findRecipesByType(typeIn, world);

        for (IRecipe<?> recipe : recipes)
        {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();

            ingredients.forEach(ingredient ->
            {
                for (ItemStack stack : ingredient.getItems())
                {
                    inputs.add(stack);
                }
            });
        }

        return inputs;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + Technicu.MOD_ID + ".charger");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new ChargerContainer(id, playerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.items);
        }

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        if (!this.trySaveLootTable(nbt)) {
            ItemStackHelper.saveAllItems(nbt, this.items);
        }

        return nbt;
    }

    @Override
    public void tick() {

    }
    //</editor-fold>
}
