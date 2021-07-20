package com.github.technicu.blocks.machineController;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class MachineControllerBlock extends Block
{
    public MachineControllerBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new MachineControllerTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace)
    {
        if (!world.isClientSide())
        {
            if (MachineControllerTileEntity.formed())
            {
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
    public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (!state.is(newState.getBlock()))
        {
            TileEntity tileEntity = world.getBlockEntity(pos);

            if (tileEntity instanceof MachineControllerTileEntity)
            {
                InventoryHelper.dropContents(world, pos, (MachineControllerTileEntity) tileEntity);

                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
    }
}
