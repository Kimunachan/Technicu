package com.github.technicu.blocks.fluidTank;

import com.github.technicu.setup.ModTileEntityTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class FluidTankTileEntity extends TileEntity {
    public static final int MAX_CAPACITY = 10000;

    public FluidTankTileEntity() {
        super(ModTileEntityTypes.FLUID_TANK.get());
    }

    LazyOptional<IFluidHandler> fluidHandlerLazyOptional = LazyOptional.of(() -> new FluidTank(MAX_CAPACITY));

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return fluidHandlerLazyOptional.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        fluidHandlerLazyOptional.invalidate();
    }

}
