package com.github.technicu.blocks.waterMill;

import com.github.technicu.Technicu;
import com.github.technicu.blocks.waterMill.WaterMillContainer;
import com.github.technicu.capabilities.ModEnergyHandler;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class WaterMillTileEntity extends LockableLootTileEntity implements ITickableTileEntity
{
    public static int slots = 2;

    public static final int MAX_ENERGY = 25000;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public WaterMillTileEntity() {
        super(ModTileEntityTypes.WATER_MILL.get());
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
        return new TranslationTextComponent("container." + Technicu.MOD_ID + ".water_mill");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new WaterMillContainer(id, playerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    LazyOptional<IEnergyStorage> energyStorageLazyOptional = LazyOptional.of(() -> new ModEnergyHandler(MAX_ENERGY,500,0));

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
        AtomicInteger current = new AtomicInteger();

        getCapability(CapabilityEnergy.ENERGY).ifPresent(iEnergyStorage -> {
            current.set(iEnergyStorage.getEnergyStored());
        });

        if (getItem(0).getItem() == Items.WATER_BUCKET && current.get() != MAX_ENERGY)
        {
            Timer t = new Timer();

            t.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    if (getItem(1) == ItemStack.EMPTY)
                    {
                        setItem(1, Items.BUCKET.getDefaultInstance());

                        if (getItem(0) != ItemStack.EMPTY)
                        {
                            doWork();
                        }

                        setItem(0, ItemStack.EMPTY);
                    }
                    else
                    {
                        if (getItem(1).getCount()  != 16)
                        {
                            getItem(1).grow(getItem(0).getCount());

                            if (getItem(0) != ItemStack.EMPTY)
                            {
                                doWork();
                            }

                            setItem(0, ItemStack.EMPTY);
                        }
                    }
                }
            }, 200);
        }
    }

    private void doWork()
    {
        energyStorageLazyOptional.ifPresent(iEnergyStorage ->
        {
            if(iEnergyStorage.canReceive())
            {
                iEnergyStorage.receiveEnergy(500,false);

                System.out.println(iEnergyStorage.getEnergyStored());
            }
        });
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
        //getTileEntity().save(nbt);
        if (!this.trySaveLootTable(nbt)) {
            ItemStackHelper.saveAllItems(nbt, this.items);
        }

        return nbt;
    }
}
