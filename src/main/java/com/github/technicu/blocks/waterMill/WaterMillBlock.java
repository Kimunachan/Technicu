package com.github.technicu.blocks.waterMill;

import com.github.technicu.blocks.ModBlockStateProperties;
import com.github.technicu.blocks.alloySmelter.AlloySmelterTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class WaterMillBlock extends Block
{
    //<editor-fold>
    private static final DirectionProperty FACING = ModBlockStateProperties.FACING_HORIZONTAL;

    public WaterMillBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WaterMillTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace)
    {
        if (!world.isClientSide())
        {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof WaterMillTileEntity)
            {
                NetworkHooks.openGui((ServerPlayerEntity) player, (WaterMillTileEntity) tileEntity, pos);
                System.out.println(tileEntity.getCapability(CapabilityEnergy.ENERGY).orElseThrow(() -> new NullPointerException("The mod Capability<IEnergyStorage> is null")));
                return ActionResultType.SUCCESS;
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

            if (tileEntity instanceof AlloySmelterTileEntity)
            {
                InventoryHelper.dropContents(world, pos, (AlloySmelterTileEntity) tileEntity);

                world.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, world, pos, newState, isMoving);
        }
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
        builder.add(FACING);
    }
    //</editor-fold>
}
