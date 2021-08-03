package com.github.technicu.blocks.blockPlacer;

import com.github.technicu.Technicu;
import com.github.technicu.capabilities.ModEnergyHandler;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockPlacerTileEntity extends LockableLootTileEntity implements ITickableTileEntity
{
    //<editor-fold>
    public static final int MAX_ENERGY = 25000;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public static final int slots = 6;

    TranslationTextComponent TITLE = new TranslationTextComponent("container." + Technicu.MOD_ID + ".block_placer");

    public BlockPlacerTileEntity() {
        super(ModTileEntityTypes.BLOCK_PLACER.get());
    }

    //<editor-fold desc="LockablePart">
    @Override
    protected ITextComponent getDefaultName() {
        return TITLE;
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new BlockPlacerContainer(windowId,playerInventory,this);
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public ItemStack getItem(int p_70301_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_70304_1_) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItem(int p_70299_1_, ItemStack p_70299_2_) {

    }

    @Override
    public boolean stillValid(PlayerEntity p_70300_1_) {
        return true;
    }

    @Override
    public void clearContent() { }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> itemsIn) {
        items = itemsIn;
    }
    //</editor-fold>

    LazyOptional<IEnergyStorage> energyStorageLazyOptional = LazyOptional.of(()-> new ModEnergyHandler(MAX_ENERGY,500,0,0));

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

    @Override
    public void tick()
    {

    }
    //</editor-fold>
}
