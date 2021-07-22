package com.github.technicu.blocks.machineController;

import com.github.technicu.Technicu;
import com.github.technicu.blocks.ModBlockStateProperties;
import com.github.technicu.capabilities.CapabilityProviderEnergy;
import com.github.technicu.recipes.ModSmeltingRecipe;
import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModRecipes;
import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nullable;

public class MachineControllerTileEntity extends LockableLootTileEntity implements ITickableTileEntity
{
    public static final int WORK_TIME = 400;
    private static boolean formed;

    public static int slots = 4;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    private int progress = 0;
    public MachineControllerTileEntity() {
        super(ModTileEntityTypes.MACHINE_CONTROLLER.get());
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
        checkFormed();

        if(getInputSignal(level,getBlockPos(),getBlockState()) > 0){
            level.setBlock(getBlockPos(),getBlockState().setValue(ModBlockStateProperties.POWERED,Boolean.valueOf(true)),2);
        } else {
            level.setBlock(getBlockPos(),getBlockState().setValue(ModBlockStateProperties.POWERED,Boolean.valueOf(false)),2);
        }
    }

    protected int getInputSignal(World world, BlockPos pos, BlockState state) {
        Direction direction = state.getValue(ModBlockStateProperties.FACING_HORIZONTAL);
        BlockPos blockpos = pos.relative(direction);
        int i = world.getSignal(blockpos, direction);
        if (i >= 15) {
            return i;
        } else {
            BlockState blockstate = world.getBlockState(blockpos);
            return Math.max(i, blockstate.is(Blocks.REDSTONE_WIRE) ? blockstate.getValue(RedstoneWireBlock.POWER) : 0);
        }
    }

    private void checkFormed(){

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

        Block opt2l1b01 = level.getBlockState(getBlockPos().below().east().east().east().north()).getBlock();
        Block opt2l1b02 = level.getBlockState(getBlockPos().below().east().east().east()).getBlock();
        Block opt2l1b03 = level.getBlockState(getBlockPos().below().east().east().east().south()).getBlock();
        Block opt2l1b04 = level.getBlockState(getBlockPos().below().east().east().north()).getBlock();
        Block opt2l1b05 = level.getBlockState(getBlockPos().below().east().east()).getBlock();
        Block opt2l1b06 = level.getBlockState(getBlockPos().below().east().east().south()).getBlock();
        Block opt2l1b07 = level.getBlockState(getBlockPos().below().east().north()).getBlock();
        Block opt2l1b08 = level.getBlockState(getBlockPos().below().east()).getBlock();
        Block opt2l1b09 = level.getBlockState(getBlockPos().below().east().south()).getBlock();
        Block opt2l1b10 = level.getBlockState(getBlockPos().below().north()).getBlock();
        Block opt2l1b11 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt2l1b12 = level.getBlockState(getBlockPos().below().south()).getBlock();

        Block opt2l2b01 = level.getBlockState(getBlockPos().east().east().east().north()).getBlock();
        Block opt2l2b02 = level.getBlockState(getBlockPos().east().east().east()).getBlock();
        Block opt2l2b03 = level.getBlockState(getBlockPos().east().east().east().south()).getBlock();
        Block opt2l2b04 = level.getBlockState(getBlockPos().east().east().north()).getBlock();
        Block opt2l2b05 = level.getBlockState(getBlockPos().east().east()).getBlock();
        Block opt2l2b06 = level.getBlockState(getBlockPos().east().east().south()).getBlock();
        Block opt2l2b07 = level.getBlockState(getBlockPos().east().north()).getBlock();
        Block opt2l2b08 = level.getBlockState(getBlockPos().east()).getBlock();
        Block opt2l2b09 = level.getBlockState(getBlockPos().east().south()).getBlock();
        Block opt2l2b10 = level.getBlockState(getBlockPos().north()).getBlock();
        Block opt2l2b11 = level.getBlockState(getBlockPos().south()).getBlock();

        Block opt2l3b01 = level.getBlockState(getBlockPos().above().east().east().east().north()).getBlock();
        Block opt2l3b02 = level.getBlockState(getBlockPos().above().east().east().east()).getBlock();
        Block opt2l3b03 = level.getBlockState(getBlockPos().above().east().east().east().south()).getBlock();
        Block opt2l3b04 = level.getBlockState(getBlockPos().above().east().east().north()).getBlock();
        Block opt2l3b05 = level.getBlockState(getBlockPos().above().east().east()).getBlock();
        Block opt2l3b06 = level.getBlockState(getBlockPos().above().east().east().south()).getBlock();
        Block opt2l3b07 = level.getBlockState(getBlockPos().above().east().north()).getBlock();
        Block opt2l3b08 = level.getBlockState(getBlockPos().above().east()).getBlock();
        Block opt2l3b09 = level.getBlockState(getBlockPos().above().east().south()).getBlock();
        Block opt2l3b10 = level.getBlockState(getBlockPos().above().north()).getBlock();
        Block opt2l3b11 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt2l3b12 = level.getBlockState(getBlockPos().above().south()).getBlock();

        Block opt3l1b01 = level.getBlockState(getBlockPos().below().south().south().south().east()).getBlock();
        Block opt3l1b02 = level.getBlockState(getBlockPos().below().south().south().south()).getBlock();
        Block opt3l1b03 = level.getBlockState(getBlockPos().below().south().south().south().west()).getBlock();
        Block opt3l1b04 = level.getBlockState(getBlockPos().below().south().south().east()).getBlock();
        Block opt3l1b05 = level.getBlockState(getBlockPos().below().south().south()).getBlock();
        Block opt3l1b06 = level.getBlockState(getBlockPos().below().south().south().west()).getBlock();
        Block opt3l1b07 = level.getBlockState(getBlockPos().below().south().east()).getBlock();
        Block opt3l1b08 = level.getBlockState(getBlockPos().below().south()).getBlock();
        Block opt3l1b09 = level.getBlockState(getBlockPos().below().south().west()).getBlock();
        Block opt3l1b10 = level.getBlockState(getBlockPos().below().east()).getBlock();
        Block opt3l1b11 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt3l1b12 = level.getBlockState(getBlockPos().below().west()).getBlock();

        Block opt3l2b01 = level.getBlockState(getBlockPos().south().south().south().east()).getBlock();
        Block opt3l2b02 = level.getBlockState(getBlockPos().south().south().south()).getBlock();
        Block opt3l2b03 = level.getBlockState(getBlockPos().south().south().south().west()).getBlock();
        Block opt3l2b04 = level.getBlockState(getBlockPos().south().south().east()).getBlock();
        Block opt3l2b05 = level.getBlockState(getBlockPos().south().south()).getBlock();
        Block opt3l2b06 = level.getBlockState(getBlockPos().south().south().west()).getBlock();
        Block opt3l2b07 = level.getBlockState(getBlockPos().south().east()).getBlock();
        Block opt3l2b08 = level.getBlockState(getBlockPos().south()).getBlock();
        Block opt3l2b09 = level.getBlockState(getBlockPos().south().west()).getBlock();
        Block opt3l2b10 = level.getBlockState(getBlockPos().east()).getBlock();
        Block opt3l2b11 = level.getBlockState(getBlockPos().west()).getBlock();

        Block opt3l3b01 = level.getBlockState(getBlockPos().above().south().south().south().east()).getBlock();
        Block opt3l3b02 = level.getBlockState(getBlockPos().above().south().south().south()).getBlock();
        Block opt3l3b03 = level.getBlockState(getBlockPos().above().south().south().south().west()).getBlock();
        Block opt3l3b04 = level.getBlockState(getBlockPos().above().south().south().east()).getBlock();
        Block opt3l3b05 = level.getBlockState(getBlockPos().above().south().south()).getBlock();
        Block opt3l3b06 = level.getBlockState(getBlockPos().above().south().south().west()).getBlock();
        Block opt3l3b07 = level.getBlockState(getBlockPos().above().south().east()).getBlock();
        Block opt3l3b08 = level.getBlockState(getBlockPos().above().south()).getBlock();
        Block opt3l3b09 = level.getBlockState(getBlockPos().above().south().west()).getBlock();
        Block opt3l3b10 = level.getBlockState(getBlockPos().above().east()).getBlock();
        Block opt3l3b11 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt3l3b12 = level.getBlockState(getBlockPos().above().west()).getBlock();

        Block opt4l1b01 = level.getBlockState(getBlockPos().below().west().west().west().south()).getBlock();
        Block opt4l1b02 = level.getBlockState(getBlockPos().below().west().west().west()).getBlock();
        Block opt4l1b03 = level.getBlockState(getBlockPos().below().west().west().west().north()).getBlock();
        Block opt4l1b04 = level.getBlockState(getBlockPos().below().west().west().south()).getBlock();
        Block opt4l1b05 = level.getBlockState(getBlockPos().below().west().west()).getBlock();
        Block opt4l1b06 = level.getBlockState(getBlockPos().below().west().west().north()).getBlock();
        Block opt4l1b07 = level.getBlockState(getBlockPos().below().west().south()).getBlock();
        Block opt4l1b08 = level.getBlockState(getBlockPos().below().west()).getBlock();
        Block opt4l1b09 = level.getBlockState(getBlockPos().below().west().north()).getBlock();
        Block opt4l1b10 = level.getBlockState(getBlockPos().below().south()).getBlock();
        Block opt4l1b11 = level.getBlockState(getBlockPos().below()).getBlock();
        Block opt4l1b12 = level.getBlockState(getBlockPos().below().north()).getBlock();

        Block opt4l2b01 = level.getBlockState(getBlockPos().west().west().west().south()).getBlock();
        Block opt4l2b02 = level.getBlockState(getBlockPos().west().west().west()).getBlock();
        Block opt4l2b03 = level.getBlockState(getBlockPos().west().west().west().north()).getBlock();
        Block opt4l2b04 = level.getBlockState(getBlockPos().west().west().south()).getBlock();
        Block opt4l2b05 = level.getBlockState(getBlockPos().west().west()).getBlock();
        Block opt4l2b06 = level.getBlockState(getBlockPos().west().west().north()).getBlock();
        Block opt4l2b07 = level.getBlockState(getBlockPos().west().south()).getBlock();
        Block opt4l2b08 = level.getBlockState(getBlockPos().west()).getBlock();
        Block opt4l2b09 = level.getBlockState(getBlockPos().west().north()).getBlock();
        Block opt4l2b10 = level.getBlockState(getBlockPos().south()).getBlock();
        Block opt4l2b11 = level.getBlockState(getBlockPos().north()).getBlock();

        Block opt4l3b01 = level.getBlockState(getBlockPos().above().west().west().west().south()).getBlock();
        Block opt4l3b02 = level.getBlockState(getBlockPos().above().west().west().west()).getBlock();
        Block opt4l3b03 = level.getBlockState(getBlockPos().above().west().west().west().north()).getBlock();
        Block opt4l3b04 = level.getBlockState(getBlockPos().above().west().west().south()).getBlock();
        Block opt4l3b05 = level.getBlockState(getBlockPos().above().west().west()).getBlock();
        Block opt4l3b06 = level.getBlockState(getBlockPos().above().west().west().north()).getBlock();
        Block opt4l3b07 = level.getBlockState(getBlockPos().above().west().south()).getBlock();
        Block opt4l3b08 = level.getBlockState(getBlockPos().above().west()).getBlock();
        Block opt4l3b09 = level.getBlockState(getBlockPos().above().west().north()).getBlock();
        Block opt4l3b10 = level.getBlockState(getBlockPos().above().south()).getBlock();
        Block opt4l3b11 = level.getBlockState(getBlockPos().above()).getBlock();
        Block opt4l3b12 = level.getBlockState(getBlockPos().above().north()).getBlock();

        Block GLASS = Blocks.GLASS;
        Block STEEL = ModBlocks.STEEL_BLOCK.get();
        Block FENCE = ModBlocks.STEEL_FENCE.get();
        Block MACHINE = ModBlocks.MACHINE_BLOCK.get();

        boolean opt1 = opt1l1b01.is(MACHINE) && opt1l1b01.is(MACHINE) && opt1l1b02.is(MACHINE) && opt1l1b03.is(MACHINE) && opt1l1b04.is(MACHINE) && opt1l1b05.is(MACHINE) && opt1l1b06.is(MACHINE) && opt1l1b07.is(MACHINE) && opt1l1b08.is(MACHINE) && opt1l1b09.is(MACHINE) && opt1l1b10.is(MACHINE) && opt1l1b11.is(MACHINE) && opt1l1b12.is(MACHINE) && opt1l2b01.is(MACHINE) && opt1l2b02.is(MACHINE) && opt1l2b03.is(MACHINE) && opt1l2b04.is(FENCE) && opt1l2b05.is(STEEL) && opt1l2b06.is(MACHINE) && opt1l2b07.is(FENCE) && opt1l2b08.is(STEEL) && opt1l2b09.is(MACHINE) && opt1l2b10.is(MACHINE) && opt1l2b11.is(ModBlocks.ENERGY_PORT.get()) && opt1l3b01.is(GLASS) && opt1l3b02.is(GLASS) && opt1l3b03.is(GLASS) && opt1l3b04.is(MACHINE) && opt1l3b05.is(GLASS) && opt1l3b06.is(GLASS) && opt1l3b07.is(MACHINE) && opt1l3b08.is(ModBlocks.COPPER_BLOCK.get()) && opt1l3b09.is(GLASS) && opt1l3b10.is(MACHINE) && opt1l3b11.is(MACHINE) && opt1l3b12.is(MACHINE);
        boolean opt2 = opt2l1b01.is(MACHINE) && opt2l1b01.is(MACHINE) && opt2l1b02.is(MACHINE) && opt2l1b03.is(MACHINE) && opt2l1b04.is(MACHINE) && opt2l1b05.is(MACHINE) && opt2l1b06.is(MACHINE) && opt2l1b07.is(MACHINE) && opt2l1b08.is(MACHINE) && opt2l1b09.is(MACHINE) && opt2l1b10.is(MACHINE) && opt2l1b11.is(MACHINE) && opt2l1b12.is(MACHINE) && opt2l2b01.is(MACHINE) && opt2l2b02.is(MACHINE) && opt2l2b03.is(MACHINE) && opt2l2b04.is(FENCE) && opt2l2b05.is(STEEL) && opt2l2b06.is(MACHINE) && opt2l2b07.is(FENCE) && opt2l2b08.is(STEEL) && opt2l2b09.is(MACHINE) && opt2l2b10.is(MACHINE) && opt2l2b11.is(ModBlocks.ENERGY_PORT.get()) && opt2l3b01.is(GLASS) && opt2l3b02.is(GLASS) && opt2l3b03.is(GLASS) && opt2l3b04.is(MACHINE) && opt2l3b05.is(GLASS) && opt2l3b06.is(GLASS) && opt2l3b07.is(MACHINE) && opt2l3b08.is(ModBlocks.COPPER_BLOCK.get()) && opt2l3b09.is(GLASS) && opt2l3b10.is(MACHINE) && opt2l3b11.is(MACHINE) && opt2l3b12.is(MACHINE);
        boolean opt3 = opt3l1b01.is(MACHINE) && opt3l1b01.is(MACHINE) && opt3l1b02.is(MACHINE) && opt3l1b03.is(MACHINE) && opt3l1b04.is(MACHINE) && opt3l1b05.is(MACHINE) && opt3l1b06.is(MACHINE) && opt3l1b07.is(MACHINE) && opt3l1b08.is(MACHINE) && opt3l1b09.is(MACHINE) && opt3l1b10.is(MACHINE) && opt3l1b11.is(MACHINE) && opt3l1b12.is(MACHINE) && opt3l2b01.is(MACHINE) && opt3l2b02.is(MACHINE) && opt3l2b03.is(MACHINE) && opt3l2b04.is(FENCE) && opt3l2b05.is(STEEL) && opt3l2b06.is(MACHINE) && opt3l2b07.is(FENCE) && opt3l2b08.is(STEEL) && opt3l2b09.is(MACHINE) && opt3l2b10.is(MACHINE) && opt3l2b11.is(ModBlocks.ENERGY_PORT.get()) && opt3l3b01.is(GLASS) && opt3l3b02.is(GLASS) && opt3l3b03.is(GLASS) && opt3l3b04.is(MACHINE) && opt3l3b05.is(GLASS) && opt3l3b06.is(GLASS) && opt3l3b07.is(MACHINE) && opt3l3b08.is(ModBlocks.COPPER_BLOCK.get()) && opt3l3b09.is(GLASS) && opt3l3b10.is(MACHINE) && opt3l3b11.is(MACHINE) && opt3l3b12.is(MACHINE);
        boolean opt4 = opt4l1b01.is(MACHINE) && opt4l1b01.is(MACHINE) && opt4l1b02.is(MACHINE) && opt4l1b03.is(MACHINE) && opt4l1b04.is(MACHINE) && opt4l1b05.is(MACHINE) && opt4l1b06.is(MACHINE) && opt4l1b07.is(MACHINE) && opt4l1b08.is(MACHINE) && opt4l1b09.is(MACHINE) && opt4l1b10.is(MACHINE) && opt4l1b11.is(MACHINE) && opt4l1b12.is(MACHINE) && opt4l2b01.is(MACHINE) && opt4l2b02.is(MACHINE) && opt4l2b03.is(MACHINE) && opt4l2b04.is(FENCE) && opt4l2b05.is(STEEL) && opt4l2b06.is(MACHINE) && opt4l2b07.is(FENCE) && opt4l2b08.is(STEEL) && opt4l2b09.is(MACHINE) && opt4l2b10.is(MACHINE) && opt4l2b11.is(ModBlocks.ENERGY_PORT.get()) && opt4l3b01.is(GLASS) && opt4l3b02.is(GLASS) && opt4l3b03.is(GLASS) && opt4l3b04.is(MACHINE) && opt4l3b05.is(GLASS) && opt4l3b06.is(GLASS) && opt4l3b07.is(MACHINE) && opt4l3b08.is(ModBlocks.COPPER_BLOCK.get()) && opt4l3b09.is(GLASS) && opt4l3b10.is(MACHINE) && opt4l3b11.is(MACHINE) && opt4l3b12.is(MACHINE);

        if (opt1 || opt2 || opt3 || opt4)
        {
            formed = true;
        }
        else
        {
            formed = false;
        }
    }

    public static boolean formed()
    {
        return formed;
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
        return new TranslationTextComponent("container." + Technicu.MOD_ID + ".machine_controller");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new MachineControllerContainer(id, playerInventory, this);
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
