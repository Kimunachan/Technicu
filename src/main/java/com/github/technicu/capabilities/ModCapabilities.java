package com.github.technicu.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class ModCapabilities {

    @CapabilityInject(IFluidHandler.class)
    public static final Capability<IFluidHandler> FLUID_CAPABILITY = null;


    public static void registerCapabilities() {
    }
}
