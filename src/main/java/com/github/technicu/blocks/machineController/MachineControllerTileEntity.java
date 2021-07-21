package com.github.technicu.blocks.machineController;

import com.github.technicu.Technicu;
import com.github.technicu.recipes.ModSmeltingRecipe;
import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModRecipes;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;

public class MachineControllerTileEntity extends LockableLootTileEntity implements ITickableTileEntity
{
    public static final int WORK_TIME = 400;
    private static boolean alloyBasicFormed;
    private static boolean alloyAdvancedFormed;

    //<editor-fold desc="InventoryStuff">
    public static int slots = 3;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
    //</editor-fold>

    private int progress = 0;

    //<editor-fold desc="IIntArray">
    private final IIntArray fields = new IIntArray() {
        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return progress;
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0:
                    progress = value;
                    break;
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    //new EnergyStorage(capacity,maxInput,maxOutput,startingEnergy)
    public MachineControllerTileEntity() {
        super(ModTileEntityTypes.MACHINE_CONTROLLER.get());
    }
    //</editor-fold>

    void encodeExtraData(PacketBuffer data) {
        data.writeByte(fields.getCount());
    }

    @Nullable
    public ModSmeltingRecipe getRecipe() {
        if (level == null || getItem(0).isEmpty()) {
            return null;
        }

        return level.getRecipeManager().getRecipeFor(ModRecipes.Types.SMELTING, this, level).orElse(null);
    }

    @Override
    public void tick() {
        checkAlloyBasicFormed();
    }

    //<editor-fold desc="Multiblock">
    private void checkAlloyBasicFormed() {

        /*OPT1
         * Layer 1
         * MMM
         * MMM
         * MMM
         * Layer 2
         * MMM
         * MKM
         * MCE
         * Layer 3
         * MMM
         * MMM
         * MMM
         *
         *OPT2
         * Layer 1
         * MMM
         * MMM
         * MMM
         * Layer 2
         * MMM
         * CKM
         * EMM
         * Layer 3
         * MMM
         * MMM
         * MMM
         *
         *OPT3
         * Layer 1
         * MMM
         * MMM
         * MMM
         * Layer 2
         * ECM
         * MKM
         * MMM
         * Layer 3
         * MMM
         * MMM
         * MMM
         *
         * OPT4
         * Layer 1
         * MMM
         * MMM
         * MMM
         * Layer 2
         * MME
         * MKC
         * MMM
         * Layer 3
         * MMM
         * MMM
         * MMM
         *
         * m = machine block
         * k = copper block
         * e = energy port
         * c = controller
         * */

        //<editor-fold desc="OPT1">
        Block opt1l1b01 = level.getBlockState(getBlockPos().below().north().north().west()).getBlock();
        Block opt1l1b02 = level.getBlockState(getBlockPos().below().north().north()).getBlock();
        Block opt1l1b03 = level.getBlockState(getBlockPos().below().north().north().east()).getBlock();
        Block opt1l1b04 = level.getBlockState(getBlockPos().below().north().west()).getBlock();
        Block opt1l1b05 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt1l1b06 = level.getBlockState(getBlockPos().below().north().east()).getBlock();
        Block opt1l1b07 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt1l1b08 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt1l1b09 = level.getBlockState(getBlockPos().below().east()).getBlock();
        Block opt1l2b01 = level.getBlockState(getBlockPos().north().north().west()).getBlock();
        Block opt1l2b02 = level.getBlockState(getBlockPos().north().north()).getBlock();
        Block opt1l2b03 = level.getBlockState(getBlockPos().north().north().east()).getBlock();
        Block opt1l2b04 = level.getBlockState(getBlockPos().north().west()).getBlock();
        Block opt1l2b05 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt1l2b06 = level.getBlockState(getBlockPos().north().east()).getBlock();
        Block opt1l2b07 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt1l2b08 = level.getBlockState(getBlockPos().east()).getBlock();
        Block opt1l3b01 = level.getBlockState(getBlockPos().above().north().north().west()).getBlock();
        Block opt1l3b02 = level.getBlockState(getBlockPos().above().north().north()).getBlock();
        Block opt1l3b03 = level.getBlockState(getBlockPos().above().north().north().east()).getBlock();
        Block opt1l3b04 = level.getBlockState(getBlockPos().above().north().west()).getBlock();
        Block opt1l3b05 = level.getBlockState(getBlockPos().above().north()).getBlock();
        Block opt1l3b06 = level.getBlockState(getBlockPos().above().north().east()).getBlock();
        Block opt1l3b07 = level.getBlockState(getBlockPos().above().west()).getBlock();
        Block opt1l3b08 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt1l3b09 = level.getBlockState(getBlockPos().above().east()).getBlock();
        //</editor-fold>
        //<editor-fold desc="OPT2">
        Block opt2l1b01 = level.getBlockState(getBlockPos().below().east().east().north()).getBlock();
        Block opt2l1b02 = level.getBlockState(getBlockPos().below().east().east()).getBlock();
        Block opt2l1b03 = level.getBlockState(getBlockPos().below().east().east().south()).getBlock();
        Block opt2l1b04 = level.getBlockState(getBlockPos().below().east().north()).getBlock();
        Block opt2l1b05 = level.getBlockState(getBlockPos().below().east()).getBlock();
        Block opt2l1b06 = level.getBlockState(getBlockPos().below().east().south()).getBlock();
        Block opt2l1b07 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt2l1b08 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt2l1b09 = level.getBlockState(getBlockPos().below().south()).getBlock();
        Block opt2l2b01 = level.getBlockState(getBlockPos().east().east().north()).getBlock();
        Block opt2l2b02 = level.getBlockState(getBlockPos().east().east()).getBlock();
        Block opt2l2b03 = level.getBlockState(getBlockPos().east().east().south()).getBlock();
        Block opt2l2b04 = level.getBlockState(getBlockPos().east().north()).getBlock();
        Block opt2l2b05 = level.getBlockState(getBlockPos().east()).getBlock();
        Block opt2l2b06 = level.getBlockState(getBlockPos().east().south()).getBlock();
        Block opt2l2b07 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt2l2b08 = level.getBlockState(getBlockPos().south()).getBlock();
        Block opt2l3b01 = level.getBlockState(getBlockPos().above().east().east().north()).getBlock();
        Block opt2l3b02 = level.getBlockState(getBlockPos().above().east().east()).getBlock();
        Block opt2l3b03 = level.getBlockState(getBlockPos().above().east().east().south()).getBlock();
        Block opt2l3b04 = level.getBlockState(getBlockPos().above().east().north()).getBlock();
        Block opt2l3b05 = level.getBlockState(getBlockPos().above().east()).getBlock();
        Block opt2l3b06 = level.getBlockState(getBlockPos().above().east().south()).getBlock();
        Block opt2l3b07 = level.getBlockState(getBlockPos().above().north()).getBlock();
        Block opt2l3b08 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt2l3b09 = level.getBlockState(getBlockPos().above().south()).getBlock();
        //</editor-fold>
        //<editor-fold desc="OPT3">
        Block opt3l1b01 = level.getBlockState(getBlockPos().below().south().south().east()).getBlock();
        Block opt3l1b02 = level.getBlockState(getBlockPos().below().south().south()).getBlock();
        Block opt3l1b03 = level.getBlockState(getBlockPos().below().south().south().west()).getBlock();
        Block opt3l1b04 = level.getBlockState(getBlockPos().below().south().east()).getBlock();
        Block opt3l1b05 = level.getBlockState(getBlockPos().below().south()).getBlock();
        Block opt3l1b06 = level.getBlockState(getBlockPos().below().south().west()).getBlock();
        Block opt3l1b07 = level.getBlockState(getBlockPos().below().east()).getBlock();
        Block opt3l1b08 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt3l1b09 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt3l2b01 = level.getBlockState(getBlockPos().south().south().east()).getBlock();
        Block opt3l2b02 = level.getBlockState(getBlockPos().south().south()).getBlock();
        Block opt3l2b03 = level.getBlockState(getBlockPos().south().south().west()).getBlock();
        Block opt3l2b04 = level.getBlockState(getBlockPos().south().east()).getBlock();
        Block opt3l2b05 = level.getBlockState(getBlockPos().south()).getBlock();
        Block opt3l2b06 = level.getBlockState(getBlockPos().south().west()).getBlock();
        Block opt3l2b07 = level.getBlockState(getBlockPos().east()).getBlock();
        Block opt3l2b08 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt3l3b01 = level.getBlockState(getBlockPos().above().south().south().east()).getBlock();
        Block opt3l3b02 = level.getBlockState(getBlockPos().above().south().south()).getBlock();
        Block opt3l3b03 = level.getBlockState(getBlockPos().above().south().south().west()).getBlock();
        Block opt3l3b04 = level.getBlockState(getBlockPos().above().south().east()).getBlock();
        Block opt3l3b05 = level.getBlockState(getBlockPos().above().south()).getBlock();
        Block opt3l3b06 = level.getBlockState(getBlockPos().above().south().west()).getBlock();
        Block opt3l3b07 = level.getBlockState(getBlockPos().above().east()).getBlock();
        Block opt3l3b08 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt3l3b09 = level.getBlockState(getBlockPos().above().west()).getBlock();
        //</editor-fold>
        //<editor-fold desc="OPT4">
        Block opt4l1b01 = level.getBlockState(getBlockPos().below().west().west().south()).getBlock();
        Block opt4l1b02 = level.getBlockState(getBlockPos().below().west().west()).getBlock();
        Block opt4l1b03 = level.getBlockState(getBlockPos().below().west().west().north()).getBlock();
        Block opt4l1b04 = level.getBlockState(getBlockPos().below().west().south()).getBlock();
        Block opt4l1b05 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt4l1b06 = level.getBlockState(getBlockPos().below().west().north()).getBlock();
        Block opt4l1b07 = level.getBlockState(getBlockPos().below().south()).getBlock();
        Block opt4l1b08 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt4l1b09 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt4l2b01 = level.getBlockState(getBlockPos().west().west().south()).getBlock();
        Block opt4l2b02 = level.getBlockState(getBlockPos().west().west()).getBlock();
        Block opt4l2b03 = level.getBlockState(getBlockPos().west().west().north()).getBlock();
        Block opt4l2b04 = level.getBlockState(getBlockPos().west().south()).getBlock();
        Block opt4l2b05 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt4l2b06 = level.getBlockState(getBlockPos().west().north()).getBlock();
        Block opt4l2b07 = level.getBlockState(getBlockPos().south()).getBlock();
        Block opt4l2b08 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt4l3b01 = level.getBlockState(getBlockPos().above().west().west().south()).getBlock();
        Block opt4l3b02 = level.getBlockState(getBlockPos().above().west().west()).getBlock();
        Block opt4l3b03 = level.getBlockState(getBlockPos().above().west().west().north()).getBlock();
        Block opt4l3b04 = level.getBlockState(getBlockPos().above().west().south()).getBlock();
        Block opt4l3b05 = level.getBlockState(getBlockPos().above().west()).getBlock();
        Block opt4l3b06 = level.getBlockState(getBlockPos().above().west().north()).getBlock();
        Block opt4l3b07 = level.getBlockState(getBlockPos().above().south()).getBlock();
        Block opt4l3b08 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt4l3b09 = level.getBlockState(getBlockPos().above().north()).getBlock();
        //</editor-fold>

        boolean opt1 = opt1l1b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l1b09.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l2b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l2b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l2b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l2b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l2b05.is(ModBlocks.COPPER_BLOCK.get()) && opt1l2b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l2b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l2b08.is(ModBlocks.MACHINE_ENERGY_PORT.get()) && opt1l3b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt1l3b09.is(ModBlocks.MACHINE_BLOCK.get());
        boolean opt2 = opt2l1b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l1b09.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l2b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l2b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l2b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l2b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l2b05.is(ModBlocks.COPPER_BLOCK.get()) && opt2l2b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l2b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l2b08.is(ModBlocks.MACHINE_ENERGY_PORT.get()) && opt2l3b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt2l3b09.is(ModBlocks.MACHINE_BLOCK.get());
        boolean opt3 = opt3l1b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l1b09.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l2b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l2b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l2b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l2b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l2b05.is(ModBlocks.COPPER_BLOCK.get()) && opt3l2b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l2b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l2b08.is(ModBlocks.MACHINE_ENERGY_PORT.get()) && opt3l3b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt3l3b09.is(ModBlocks.MACHINE_BLOCK.get());
        boolean opt4 = opt4l1b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l1b09.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l2b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l2b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l2b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l2b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l2b05.is(ModBlocks.COPPER_BLOCK.get()) && opt4l2b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l2b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l2b08.is(ModBlocks.MACHINE_ENERGY_PORT.get()) && opt4l3b01.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b02.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b03.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b04.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b05.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b06.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b07.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b08.is(ModBlocks.MACHINE_BLOCK.get()) && opt4l3b09.is(ModBlocks.MACHINE_BLOCK.get());

        //BASIC
        if (opt1 || opt2 || opt3 || opt4) {
            alloyBasicFormed = true;
        } else {
            alloyBasicFormed = false;
        }
    }

    private void checkAlloyAdvancedFormed(){

        //<editor-fold desc="opt1">
        //<editor-fold desc="opt1l1">
        Block opt1l1b01 = level.getBlockState(getBlockPos().below().north().north().north().west()).getBlock();
        Block opt1l1b02 = level.getBlockState(getBlockPos().below().north().north().north()).getBlock();
        Block opt1l1b03 = level.getBlockState(getBlockPos().below().north().north().north().east()).getBlock();
        Block opt1l1b04 = level.getBlockState(getBlockPos().below().north().north().west()).getBlock();
        Block opt1l1b05 = level.getBlockState(getBlockPos().below().north().north()).getBlock();
        Block opt1l1b06 = level.getBlockState(getBlockPos().below().north().north().east()).getBlock();
        Block opt1l1b07 = level.getBlockState(getBlockPos().below().north().west()).getBlock();
        Block opt1l1b08 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt1l1b09 = level.getBlockState(getBlockPos().below().north().east()).getBlock();
        Block opt1l1b10 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt1l1b11 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt1l1b12 = level.getBlockState(getBlockPos().below().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt1l2">
        Block opt1l2b01 = level.getBlockState(getBlockPos().north().north().north().west()).getBlock();
        Block opt1l2b02 = level.getBlockState(getBlockPos().north().north().north()).getBlock();
        Block opt1l2b03 = level.getBlockState(getBlockPos().north().north().north().east()).getBlock();
        Block opt1l2b04 = level.getBlockState(getBlockPos().north().north().west()).getBlock();
        Block opt1l2b05 = level.getBlockState(getBlockPos().north().north()).getBlock();
        Block opt1l2b06 = level.getBlockState(getBlockPos().north().north().east()).getBlock();
        Block opt1l2b07 = level.getBlockState(getBlockPos().north().west()).getBlock();
        Block opt1l2b08 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt1l2b09 = level.getBlockState(getBlockPos().north().east()).getBlock();
        Block opt1l2b10 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt1l2b11 = level.getBlockState(getBlockPos().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt1l3">
        Block opt1l3b01 = level.getBlockState(getBlockPos().above().north().north().north().west()).getBlock();
        Block opt1l3b02 = level.getBlockState(getBlockPos().above().north().north().north()).getBlock();
        Block opt1l3b03 = level.getBlockState(getBlockPos().above().north().north().north().east()).getBlock();
        Block opt1l3b04 = level.getBlockState(getBlockPos().above().north().north().west()).getBlock();
        Block opt1l3b05 = level.getBlockState(getBlockPos().above().north().north()).getBlock();
        Block opt1l3b06 = level.getBlockState(getBlockPos().above().north().north().east()).getBlock();
        Block opt1l3b07 = level.getBlockState(getBlockPos().above().north().west()).getBlock();
        Block opt1l3b08 = level.getBlockState(getBlockPos().above().north()).getBlock();
        Block opt1l3b09 = level.getBlockState(getBlockPos().above().north().east()).getBlock();
        Block opt1l3b10 = level.getBlockState(getBlockPos().above().west()).getBlock();
        Block opt1l3b11 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt1l3b12 = level.getBlockState(getBlockPos().above().east()).getBlock();
        //</editor-fold>
        //</editor-fold>
        //<editor-fold desc="opt2">
        //<editor-fold desc="opt2l1">
        Block opt2l1b01 = level.getBlockState(getBlockPos().below().north().north().north().west()).getBlock();
        Block opt2l1b02 = level.getBlockState(getBlockPos().below().north().north().north()).getBlock();
        Block opt2l1b03 = level.getBlockState(getBlockPos().below().north().north().north().east()).getBlock();
        Block opt2l1b04 = level.getBlockState(getBlockPos().below().north().north().west()).getBlock();
        Block opt2l1b05 = level.getBlockState(getBlockPos().below().north().north()).getBlock();
        Block opt2l1b06 = level.getBlockState(getBlockPos().below().north().north().east()).getBlock();
        Block opt2l1b07 = level.getBlockState(getBlockPos().below().north().west()).getBlock();
        Block opt2l1b08 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt2l1b09 = level.getBlockState(getBlockPos().below().north().east()).getBlock();
        Block opt2l1b10 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt2l1b11 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt2l1b12 = level.getBlockState(getBlockPos().below().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt2l2">
        Block opt2l2b01 = level.getBlockState(getBlockPos().north().north().north().west()).getBlock();
        Block opt2l2b02 = level.getBlockState(getBlockPos().north().north().north()).getBlock();
        Block opt2l2b03 = level.getBlockState(getBlockPos().north().north().north().east()).getBlock();
        Block opt2l2b04 = level.getBlockState(getBlockPos().north().north().west()).getBlock();
        Block opt2l2b05 = level.getBlockState(getBlockPos().north().north()).getBlock();
        Block opt2l2b06 = level.getBlockState(getBlockPos().north().north().east()).getBlock();
        Block opt2l2b07 = level.getBlockState(getBlockPos().north().west()).getBlock();
        Block opt2l2b08 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt2l2b09 = level.getBlockState(getBlockPos().north().east()).getBlock();
        Block opt2l2b10 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt2l2b11 = level.getBlockState(getBlockPos().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt2l3">
        Block opt2l3b01 = level.getBlockState(getBlockPos().above().north().north().north().west()).getBlock();
        Block opt2l3b02 = level.getBlockState(getBlockPos().above().north().north().north()).getBlock();
        Block opt2l3b03 = level.getBlockState(getBlockPos().above().north().north().north().east()).getBlock();
        Block opt2l3b04 = level.getBlockState(getBlockPos().above().north().north().west()).getBlock();
        Block opt2l3b05 = level.getBlockState(getBlockPos().above().north().north()).getBlock();
        Block opt2l3b06 = level.getBlockState(getBlockPos().above().north().north().east()).getBlock();
        Block opt2l3b07 = level.getBlockState(getBlockPos().above().north().west()).getBlock();
        Block opt2l3b08 = level.getBlockState(getBlockPos().above().north()).getBlock();
        Block opt2l3b09 = level.getBlockState(getBlockPos().above().north().east()).getBlock();
        Block opt2l3b10 = level.getBlockState(getBlockPos().above().west()).getBlock();
        Block opt2l3b11 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt2l3b12 = level.getBlockState(getBlockPos().above().east()).getBlock();
        //</editor-fold>
        //</editor-fold>
        //<editor-fold desc="opt3">
        //<editor-fold desc="opt3l1">
        Block opt3l1b01 = level.getBlockState(getBlockPos().below().north().north().north().west()).getBlock();
        Block opt3l1b02 = level.getBlockState(getBlockPos().below().north().north().north()).getBlock();
        Block opt3l1b03 = level.getBlockState(getBlockPos().below().north().north().north().east()).getBlock();
        Block opt3l1b04 = level.getBlockState(getBlockPos().below().north().north().west()).getBlock();
        Block opt3l1b05 = level.getBlockState(getBlockPos().below().north().north()).getBlock();
        Block opt3l1b06 = level.getBlockState(getBlockPos().below().north().north().east()).getBlock();
        Block opt3l1b07 = level.getBlockState(getBlockPos().below().north().west()).getBlock();
        Block opt3l1b08 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt3l1b09 = level.getBlockState(getBlockPos().below().north().east()).getBlock();
        Block opt3l1b10 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt3l1b11 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt3l1b12 = level.getBlockState(getBlockPos().below().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt3l2">
        Block opt3l2b01 = level.getBlockState(getBlockPos().north().north().north().west()).getBlock();
        Block opt3l2b02 = level.getBlockState(getBlockPos().north().north().north()).getBlock();
        Block opt3l2b03 = level.getBlockState(getBlockPos().north().north().north().east()).getBlock();
        Block opt3l2b04 = level.getBlockState(getBlockPos().north().north().west()).getBlock();
        Block opt3l2b05 = level.getBlockState(getBlockPos().north().north()).getBlock();
        Block opt3l2b06 = level.getBlockState(getBlockPos().north().north().east()).getBlock();
        Block opt3l2b07 = level.getBlockState(getBlockPos().north().west()).getBlock();
        Block opt3l2b08 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt3l2b09 = level.getBlockState(getBlockPos().north().east()).getBlock();
        Block opt3l2b10 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt3l2b11 = level.getBlockState(getBlockPos().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt3l3">
        Block opt3l3b01 = level.getBlockState(getBlockPos().above().north().north().north().west()).getBlock();
        Block opt3l3b02 = level.getBlockState(getBlockPos().above().north().north().north()).getBlock();
        Block opt3l3b03 = level.getBlockState(getBlockPos().above().north().north().north().east()).getBlock();
        Block opt3l3b04 = level.getBlockState(getBlockPos().above().north().north().west()).getBlock();
        Block opt3l3b05 = level.getBlockState(getBlockPos().above().north().north()).getBlock();
        Block opt3l3b06 = level.getBlockState(getBlockPos().above().north().north().east()).getBlock();
        Block opt3l3b07 = level.getBlockState(getBlockPos().above().north().west()).getBlock();
        Block opt3l3b08 = level.getBlockState(getBlockPos().above().north()).getBlock();
        Block opt3l3b09 = level.getBlockState(getBlockPos().above().north().east()).getBlock();
        Block opt3l3b10 = level.getBlockState(getBlockPos().above().west()).getBlock();
        Block opt3l3b11 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt3l3b12 = level.getBlockState(getBlockPos().above().east()).getBlock();
        //</editor-fold>
        //</editor-fold>
        //<editor-fold desc="opt4">
        //<editor-fold desc="opt4l1">
        Block opt4l1b01 = level.getBlockState(getBlockPos().below().north().north().north().west()).getBlock();
        Block opt4l1b02 = level.getBlockState(getBlockPos().below().north().north().north()).getBlock();
        Block opt4l1b03 = level.getBlockState(getBlockPos().below().north().north().north().east()).getBlock();
        Block opt4l1b04 = level.getBlockState(getBlockPos().below().north().north().west()).getBlock();
        Block opt4l1b05 = level.getBlockState(getBlockPos().below().north().north()).getBlock();
        Block opt4l1b06 = level.getBlockState(getBlockPos().below().north().north().east()).getBlock();
        Block opt4l1b07 = level.getBlockState(getBlockPos().below().north().west()).getBlock();
        Block opt4l1b08 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt4l1b09 = level.getBlockState(getBlockPos().below().north().east()).getBlock();
        Block opt4l1b10 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt4l1b11 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt4l1b12 = level.getBlockState(getBlockPos().below().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt4l2">
        Block opt4l2b01 = level.getBlockState(getBlockPos().north().north().north().west()).getBlock();
        Block opt4l2b02 = level.getBlockState(getBlockPos().north().north().north()).getBlock();
        Block opt4l2b03 = level.getBlockState(getBlockPos().north().north().north().east()).getBlock();
        Block opt4l2b04 = level.getBlockState(getBlockPos().north().north().west()).getBlock();
        Block opt4l2b05 = level.getBlockState(getBlockPos().north().north()).getBlock();
        Block opt4l2b06 = level.getBlockState(getBlockPos().north().north().east()).getBlock();
        Block opt4l2b07 = level.getBlockState(getBlockPos().north().west()).getBlock();
        Block opt4l2b08 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt4l2b09 = level.getBlockState(getBlockPos().north().east()).getBlock();
        Block opt4l2b10 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt4l2b11 = level.getBlockState(getBlockPos().east()).getBlock();
        //</editor-fold>

        //<editor-fold desc="opt4l3">
        Block opt4l3b01 = level.getBlockState(getBlockPos().above().north().north().north().west()).getBlock();
        Block opt4l3b02 = level.getBlockState(getBlockPos().above().north().north().north()).getBlock();
        Block opt4l3b03 = level.getBlockState(getBlockPos().above().north().north().north().east()).getBlock();
        Block opt4l3b04 = level.getBlockState(getBlockPos().above().north().north().west()).getBlock();
        Block opt4l3b05 = level.getBlockState(getBlockPos().above().north().north()).getBlock();
        Block opt4l3b06 = level.getBlockState(getBlockPos().above().north().north().east()).getBlock();
        Block opt4l3b07 = level.getBlockState(getBlockPos().above().north().west()).getBlock();
        Block opt4l3b08 = level.getBlockState(getBlockPos().above().north()).getBlock();
        Block opt4l3b09 = level.getBlockState(getBlockPos().above().north().east()).getBlock();
        Block opt4l3b10 = level.getBlockState(getBlockPos().above().west()).getBlock();
        Block opt4l3b11 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt4l3b12 = level.getBlockState(getBlockPos().above().east()).getBlock();
        //</editor-fold>
        //</editor-fold>

        Block GLASS = Blocks.GLASS;
        Block STEEL = ModBlocks.STEEL_BLOCK.get();
        Block FENCE = ModBlocks.STEEL_FENCE.get();
        Block MACHINE = ModBlocks.MACHINE_BLOCK.get();

        boolean opt1 = opt1l1b01.is(MACHINE) &&
                opt1l1b01.is(MACHINE) &&
                opt1l1b02.is(MACHINE) &&
                opt1l1b03.is(MACHINE) &&
                opt1l1b04.is(MACHINE) &&
                opt1l1b05.is(MACHINE) &&
                opt1l1b07.is(MACHINE) &&
                opt1l1b08.is(MACHINE) &&
                opt1l1b09.is(MACHINE) &&
                opt1l1b10.is(MACHINE) &&
                opt1l1b11.is(MACHINE) &&
                opt1l1b12.is(MACHINE) &&

                opt1l2b01.is(MACHINE) &&
                opt1l2b02.is(MACHINE) &&
                opt1l2b03.is(MACHINE) &&
                opt1l2b04.is(FENCE) &&
                opt1l2b05.is(STEEL) &&
                opt1l2b06.is(MACHINE) &&
                opt1l2b07.is(FENCE) &&
                opt1l2b08.is(STEEL) &&
                opt1l2b09.is(MACHINE) &&
                opt1l2b10.is(MACHINE) &&
                opt1l2b11.is(ModBlocks.MACHINE_ENERGY_PORT.get()) &&

                opt1l3b01.is(Blocks.GLASS) &&
                opt1l3b02.is(Blocks.GLASS) &&
                opt1l3b03.is(Blocks.GLASS) &&
                opt1l3b04.is(Blocks.GLASS) &&
                opt1l3b05.is(Blocks.GLASS) &&
                opt1l3b06.is(Blocks.GLASS) &&
                opt1l3b07.is(Blocks.GLASS) &&
                opt1l3b08.is(ModBlocks.COPPER_BLOCK.get()) &&
                opt1l3b09.is(MACHINE) &&
                opt1l3b10.is(MACHINE) &&
                opt1l3b11.is(MACHINE) &&
                opt1l3b12.is(MACHINE);
    }

    public static boolean alloyBasicFormed()
    {
        return formed;
    }

    public static boolean alloyAdvancedFormed()
    {
        return alloyAdvancedFormed;
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
        return new TranslationTextComponent("container." + Technicu.MOD_ID + ".multiblock_basic");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new MachineControllerBasicContainer(id, playerInventory, this);
    }

    @Override
    public int getContainerSize() {
        return slots;
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
        if (!this.trySaveLootTable(nbt)) {
            ItemStackHelper.saveAllItems(nbt, this.items);
        }

        return nbt;
    }
    //</editor-fold>
}
