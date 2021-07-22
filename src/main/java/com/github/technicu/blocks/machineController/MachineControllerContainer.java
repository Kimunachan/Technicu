package com.github.technicu.blocks.machineController;

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

import java.util.Objects;

public class MachineControllerContainer extends Container
{
    //<editor-fold>
    public final MachineControllerTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    private IIntArray fields;

    public int getProgressArrowScale() {
        int progress = fields.get(0);

        if (progress > 0) {
            return progress * 24 / MachineControllerTileEntity.WORK_TIME;
        }

        return 0;
    }
    public MachineControllerContainer(final int windowId, final PlayerInventory playerInventory, final MachineControllerTileEntity tileEntity) {
        super(ModContainerTypes.MACHINE_CONTROLLER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        //slot,x,y
        if (MachineControllerTileEntity.formed())
        {
            this.addSlot(new Slot((IInventory) tileEntity,0,46,22));
            this.addSlot(new Slot((IInventory) tileEntity,1,33,49));
            this.addSlot(new Slot((IInventory) tileEntity,2,58,49));
            this.addSlot(new Slot((IInventory) tileEntity,3,116,35));
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

    public MachineControllerContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static MachineControllerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(data, "PacketBuffer cannot be null");

        final TileEntity tileEntity = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileEntity instanceof MachineControllerTileEntity) {
            return (MachineControllerTileEntity) tileEntity;
        }
        throw new IllegalStateException("Tileentity is not correct!");
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(canInteractWithCallable,player, ModBlocks.MACHINE_CONTROLLER.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();

            if (MachineControllerTileEntity.formed())
            {
                if (index < 4 && !this.moveItemStackTo(stack1, MachineControllerTileEntity.slots, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }

            if(!this.moveItemStackTo(stack1,0,MachineControllerTileEntity.slots,false)){
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
    //</editor-fold>
}
