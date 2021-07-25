package com.github.technicu.blocks.alloySmelter;

import com.github.technicu.Technicu;
import com.github.technicu.capabilities.ModEnergyHandler;
import com.github.technicu.recipes.ModSmeltingRecipe;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class AlloySmelterTileEntity extends LockableLootTileEntity
{
    //<editor-fold>
    public static final int WORK_TIME = 400;

    public static int slots = 3;

    int progress = 0;
    public static final int MAX_ENERGY = 25000;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    //new EnergyStorage(capacity,maxInput,maxOutput,startingEnergy)
    public AlloySmelterTileEntity() {
        super(ModTileEntityTypes.ALLOY_SMELTER.get());
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
    public ModSmeltingRecipe getRecipe() {
        if (level == null || getItem(0).isEmpty()) {
            return null;
        }

        return level.getRecipeManager().getRecipeFor(ModRecipes.Types.SMELTING, this, level).orElse(null);
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
        return new TranslationTextComponent("container." + Technicu.MOD_ID + ".alloy_smelter");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new AlloySmelterContainer(id, playerInventory, this);
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
    //</editor-fold>
}
