package com.github.technicu.blocks.cobblestoneGenerator;

import com.github.technicu.Technicu;
import com.github.technicu.capabilities.ModEnergyHandler;
import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import java.util.concurrent.atomic.AtomicInteger;

public class CobblestoneGeneratorTileEntity extends LockableTileEntity implements ITickableTileEntity
{
    //<editor-fold>
    public static final int MAX_ENERGY = 25000;

    public static final int slots = 15;
    public static int tick = 0;

    TranslationTextComponent TITLE = new TranslationTextComponent("container." + Technicu.MOD_ID + ".energy_port");

    public CobblestoneGeneratorTileEntity() {
        super(ModTileEntityTypes.COBBLESTONE_GENERATOR.get());
    }

    //<editor-fold desc="LockablePart">
    @Override
    protected ITextComponent getDefaultName() {
        return TITLE;
    }

    @Override
    protected Container createMenu(int windowId, PlayerInventory playerInventory) {
        return new CobblestoneGeneratorContainer(windowId,playerInventory,this);
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
        tick++;

        AtomicInteger current = new AtomicInteger();

        getCapability(CapabilityEnergy.ENERGY).ifPresent(iEnergyStorage -> {
            current.set(iEnergyStorage.getEnergyStored());
        });

//        this.energyStorageLazyOptional.ifPresent(iEnergyStorage ->
//        {
//            if(iEnergyStorage.canReceive())
//            {
//                iEnergyStorage.receiveEnergy(500,false);
//            }
//        });

        if (current.get() >= 100)
        {
            if (tick == (5 * 20))
            {
                for (int slot = 0; slot < slots; slot++)
                {
                    if (getItem(slot).getCount() == 64)
                    {
                        setItem(slot + 1, getItem(slot).copy());
                    }
                    else
                    {
                        setItem(slot, Blocks.COBBLESTONE.asItem().getDefaultInstance());
                    }
                }

                tick = 0;
            }
        }
    }
    //</editor-fold>
}
