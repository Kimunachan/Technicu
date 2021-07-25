package com.github.technicu.blocks.metalPress;

import com.github.technicu.blocks.metalPress.MetalPressTileEntity;
import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Objects;

public class MetalPressContainer extends Container {


    public final MetalPressTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    private IIntArray fields;

    public int getProgressArrowScale() {
        int progress = fields.get(0);

        if (progress > 0) {
            return progress * 24 / MetalPressTileEntity.WORK_TIME;
        }

        return 0;
    }
    public MetalPressContainer(final int windowId, final PlayerInventory playerInventory, final MetalPressTileEntity tileEntity) {
        super(ModContainerTypes.METAL_PRESS.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        //slot,x,y
        this.addSlot(new Slot((IInventory) tileEntity,0,56,35));
        this.addSlot(new Slot((IInventory) tileEntity,1,116,35));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory,col,8+col*18,142));
        }

    }

    public MetalPressContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static MetalPressTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data)
    {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(data, "PacketBuffer cannot be null");

        final TileEntity tileEntity = playerInventory.player.level.getBlockEntity(data.readBlockPos());

        if (tileEntity instanceof MetalPressTileEntity)
        {
            return (MetalPressTileEntity) tileEntity;
        }

        throw new IllegalStateException("Tileentity is not correct!");
    }

    @Override
    public boolean stillValid(PlayerEntity player)
    {
        return stillValid(canInteractWithCallable,player, ModBlocks.METAL_PRESS.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int index)
    {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if(slot != null && slot.hasItem())
        {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();

            if(index < 1 &&!this.moveItemStackTo(stack1,MetalPressTileEntity.slots,this.slots.size(),true))
            {
                return ItemStack.EMPTY;
            }
            if(!this.moveItemStackTo(stack1,0, MetalPressTileEntity.slots,false))
            {
                return ItemStack.EMPTY;
            }

            if(stack1.isEmpty())
            {
                slot.set(ItemStack.EMPTY);
            }
            else
            {
                slot.setChanged();
            }
        }

        return stack;
    }
    public LazyOptional<IEnergyStorage> getCapabilityFromTE(){
        return this.tileEntity.getCapability(CapabilityEnergy.ENERGY);
    }
}
