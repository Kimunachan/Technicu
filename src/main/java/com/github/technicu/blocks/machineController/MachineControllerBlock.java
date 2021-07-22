package com.github.technicu.blocks.machineController;

import com.github.technicu.blocks.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.spongepowered.asm.mixin.Overwrite;

import javax.annotation.Nullable;

public class MachineControllerBlock extends Block
{
    //<editor-fold>
    private static final DirectionProperty FACING = ModBlockStateProperties.FACING_HORIZONTAL;
    private static final BooleanProperty POWERED = ModBlockStateProperties.POWERED;

    public MachineControllerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                stateDefinition.any()
                        .setValue(FACING,Direction.NORTH)
                        .setValue(POWERED, Boolean.valueOf(false))
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new MachineControllerTileEntity();
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING,POWERED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace) {
        if (!world.isClientSide()) {
            if (MachineControllerTileEntity.formed()) {
                TileEntity tileEntity = world.getBlockEntity(pos);
                if (tileEntity instanceof MachineControllerTileEntity) {
                    NetworkHooks.openGui((ServerPlayerEntity) player, (MachineControllerTileEntity) tileEntity, pos);
                    return ActionResultType.SUCCESS;
                }
            }
        }

        return ActionResultType.CONSUME;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            TileEntity tileEntity = world.getBlockEntity(pos);

            if (tileEntity instanceof MachineControllerTileEntity) {
                InventoryHelper.dropContents(world, pos, (MachineControllerTileEntity) tileEntity);

                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos p_220069_5_, boolean bool) {
        if (!world.isClientSide) {
            if(getInputSignal(world,pos,state) > 0){
                world.setBlock(pos,state.setValue(ModBlockStateProperties.POWERED,Boolean.valueOf(true)),2);
            } else {
                world.setBlock(pos,state.setValue(ModBlockStateProperties.POWERED,Boolean.valueOf(false)),2);
            }
            world.getBlockTicks().scheduleTick(pos,state.getBlock(),2, TickPriority.NORMAL);
        }
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState p_220082_4_, boolean bool) {
        if (!world.isClientSide) {
            if(getInputSignal(world,pos,state) > 0){
                world.setBlock(pos,state.setValue(ModBlockStateProperties.POWERED,Boolean.valueOf(true)),2);
            } else {
                world.setBlock(pos,state.setValue(ModBlockStateProperties.POWERED,Boolean.valueOf(false)),2);
            }
            world.getBlockTicks().scheduleTick(pos,state.getBlock(),2, TickPriority.NORMAL);
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

    @Override
    public boolean canConnectRedstone(BlockState state, IBlockReader world, BlockPos pos, @Nullable Direction side) {
        return true;
    }

    //</editor-fold>
}
