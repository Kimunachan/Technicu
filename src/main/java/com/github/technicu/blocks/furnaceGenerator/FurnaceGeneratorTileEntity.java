package com.github.technicu.blocks.furnaceGenerator;

import com.github.technicu.Technicu;
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
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class FurnaceGeneratorTileEntity extends LockableLootTileEntity
{
    //<editor-fold>
    public static final int WORK_TIME = 400;

    public static int slots = 1;

    int progress = 0;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    //new EnergyStorage(capacity,maxInput,maxOutput,startingEnergy)
    public FurnaceGeneratorTileEntity() {
        super(ModTileEntityTypes.FURNACE_GENERATOR.get());
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
        return new TranslationTextComponent("container." + Technicu.MOD_ID + ".furnace_generator");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new FurnaceGeneratorContainer(id, playerInventory, this);
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
