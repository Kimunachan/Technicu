package com.github.technicu.blocks.energyPort;

import com.github.technicu.Technicu;
import com.github.technicu.capabilities.ModEnergyHandler;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class EnergyPortTileEntity extends LockableTileEntity implements ITickableTileEntity
{
    //<editor-fold>
    public static final int MAX_ENERGY = 25000;
    TranslationTextComponent TITLE = new TranslationTextComponent("container." + Technicu.MOD_ID + ".energy_port");

    public EnergyPortTileEntity() {
        super(ModTileEntityTypes.ENERGY_PORT.get());
    }

    //<editor-fold desc="LockablePart">
    @Override
    protected ITextComponent getDefaultName() {
        return TITLE;
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new EnergyPortContainer(windowId,playerInventory,this);
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
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
    //</editor-fold>

    LazyOptional<IEnergyStorage> energyStorageLazyOptional = LazyOptional.of(()-> new ModEnergyHandler(MAX_ENERGY));

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
    public void tick() {
    }
    //</editor-fold>
}
