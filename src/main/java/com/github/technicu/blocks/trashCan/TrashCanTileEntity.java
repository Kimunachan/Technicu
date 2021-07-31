package com.github.technicu.blocks.trashCan;

import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.util.text.ITextComponent;

public class TrashCanTileEntity extends LockableTileEntity implements ITickableTileEntity
{
    public static int slots = 1;

    public TrashCanTileEntity() {
        super(ModTileEntityTypes.TRASH_CAN.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return null;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory inv)
    {
        return new TrashCanContainer(id, inv, this);
    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int p_70301_1_) {
        return null;
    }

    @Override
    public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack removeItemNoUpdate(int p_70304_1_) {
        return null;
    }

    @Override
    public void setItem(int p_70299_1_, ItemStack p_70299_2_) {

    }

    @Override
    public boolean stillValid(PlayerEntity p_70300_1_) {
        return false;
    }

    @Override
    public void clearContent() {

    }

    @Override
    public void tick()
    {
        if (!getItem(0).isEmpty())
        {
            setItem(0, ItemStack.EMPTY);
        }
    }
}
