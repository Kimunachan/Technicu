package com.github.technicu.blocks.blockPlacer;

import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Objects;

public class BlockPlacerContainer extends Container
{
    //<editor-fold>
    public final BlockPlacerTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public BlockPlacerContainer(final int windowId, final PlayerInventory playerInventory, final BlockPlacerTileEntity tileEntity) {
        super(ModContainerTypes.BLOCK_PLACER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        for(int row = 0; row < 2; row++)
        {
            for(int col = 0; col < 4; col++)
            {
                this.addSlot(new FurnaceResultSlot(playerInventory.player, tileEntity, col + row * 2 + 4, 71 + col * 18, 26 + row * 18));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory,col,8+col*18,142));
        }
    }

    public BlockPlacerContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static BlockPlacerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(data, "PacketBuffer cannot be null");

        final TileEntity tileEntity = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileEntity instanceof BlockPlacerTileEntity) {
            return (BlockPlacerTileEntity) tileEntity;
        }
        throw new IllegalStateException("Tileentity is not correct!");
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(canInteractWithCallable,player, ModBlocks.BLOCK_PLACER.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();

            if (index < BlockPlacerTileEntity.slots && !this.moveItemStackTo(stack1, BlockPlacerTileEntity.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }

            if(!this.moveItemStackTo(stack1,0, BlockPlacerTileEntity.slots,false)){
                return ItemStack.EMPTY;
            }

            if(stack1.isEmpty()){
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return stack;
    }

    public LazyOptional<IEnergyStorage> getCapabilityFromTE(){
        return this.tileEntity.getCapability(CapabilityEnergy.ENERGY);
    }

    //</editor-fold>
}
