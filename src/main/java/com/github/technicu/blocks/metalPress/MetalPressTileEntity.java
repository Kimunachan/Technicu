package com.github.technicu.blocks.metalPress;

import com.github.technicu.Technicu;
import com.github.technicu.recipes.ModPressingRecipe;
import com.github.technicu.setup.ModRecipes;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class MetalPressTileEntity extends LockableLootTileEntity
{
    private static boolean formed;

    private int progress = 0;

    public MetalPressTileEntity() {
        super(ModTileEntityTypes.METAL_PRESS.get());
    }

    //<editor-fold desc="IIntArray">
    private final IIntArray fields = new IIntArray()
    {
        @Override
        public int get(int index)
        {
            switch (index)
            {
                case 0:
                    return progress;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value)
        {
            switch (index)
            {
                case 0:
                    progress = value;
                    break;
            }
        }

        @Override
        public int getCount()
        {
            return 1;
        }
    };
    //</editor-fold>

    void encodeExtraData(PacketBuffer data)
    {
        data.writeByte(fields.getCount());
    }

    @Nullable
    public ModPressingRecipe getRecipe()
    {
        if (level == null || getItem(0).isEmpty())
        {
            return null;
        }

        return level.getRecipeManager().getRecipeFor(ModRecipes.Types.PRESSING, this, level).orElse(null);
    }

    //<editor-fold desc="InventoryStuff">
    public static int slots = 2;
    public static final int WORK_TIME = 20 * 2;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots,ItemStack.EMPTY);

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
        return new TranslationTextComponent("container."+ Technicu.MOD_ID + ".metal_press");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new MetalPressContainer(id,playerInventory,this);
    }

    @Override
    public int getContainerSize() {
        return slots;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt) {
        super.load(state, nbt);
        this.items = NonNullList.withSize(getContainerSize(),ItemStack.EMPTY);
        if(!this.tryLoadLootTable(nbt)){
            ItemStackHelper.loadAllItems(nbt,this.items);
        }

    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);
        if(!this.trySaveLootTable(nbt)){
            ItemStackHelper.saveAllItems(nbt,this.items);
        }

        return nbt;
    }
    //</editor-fold>
}
