package com.github.technicu.blocks.fluidTank;


import com.github.technicu.blocks.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class FluidTankBlock extends Block
{
    //<editor-fold>
    private static final DirectionProperty FACING = ModBlockStateProperties.FACING_HORIZONTAL;

    public FluidTankBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FluidTankTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace)
    {
        int MAX_CAPACITY = FluidTankTileEntity.MAX_CAPACITY;

        ItemStack item = player.getItemInHand(hand);

        if (world.isClientSide())
        {
            FluidTankTileEntity te = (FluidTankTileEntity) world.getBlockEntity(pos);


            if(te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).isPresent()){
                te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(iFluidHandler -> {
                    if(iFluidHandler.isFluidValid(0,new FluidStack(ForgeRegistries.FLUIDS.getValue(FluidTags.WATER.getName()),0))) {
                        if(MAX_CAPACITY - iFluidHandler.getFluidInTank(0).getAmount() >= 0){
                            iFluidHandler.fill(new FluidStack(ForgeRegistries.FLUIDS.getValue(FluidTags.WATER.getName()),1000), IFluidHandler.FluidAction.SIMULATE);
                        }
                    }

                });
                return ActionResultType.SUCCESS;
            }

        }

        return ActionResultType.CONSUME;
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
