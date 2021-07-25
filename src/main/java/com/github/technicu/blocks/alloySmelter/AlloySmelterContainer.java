package com.github.technicu.blocks.alloySmelter;

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

public class AlloySmelterContainer extends Container {

    //<editor-fold>
    public final AlloySmelterTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    private IIntArray fields;

    public int getProgressArrowScale() {
        int progress = fields.get(0);

        if (progress > 0) {
            return progress * 24 / AlloySmelterTileEntity.WORK_TIME;
        }

        return 0;
    }
    public AlloySmelterContainer(final int windowId, final PlayerInventory playerInventory, final AlloySmelterTileEntity tileEntity) {
        super(ModContainerTypes.ALLOY_SMELTER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());

        //slot,x,y

        this.addSlot(new Slot((IInventory) tileEntity,0,46,22));
        this.addSlot(new Slot((IInventory) tileEntity,1,46,49));
        this.addSlot(new Slot((IInventory) tileEntity,2,116,35));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory,col,8+col*18,142));
        }

    }

    public AlloySmelterContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static AlloySmelterTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(data, "PacketBuffer cannot be null");

        final TileEntity tileEntity = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileEntity instanceof AlloySmelterTileEntity) {
            return (AlloySmelterTileEntity) tileEntity;
        }
        throw new IllegalStateException("Tileentity is not correct!");
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(canInteractWithCallable,player, ModBlocks.ALLOY_SMELTER.get());
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerEntity, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if(slot != null && slot.hasItem()){
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();

            if (index < 3 && !this.moveItemStackTo(stack1, AlloySmelterTileEntity.slots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }

            if(!this.moveItemStackTo(stack1,0,AlloySmelterTileEntity.slots,false)){
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
