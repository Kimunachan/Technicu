package com.github.technicu.setup;


import com.github.technicu.Technicu;
import com.github.technicu.blocks.alloySmelter.AlloySmelterTileEntity;
import com.github.technicu.blocks.charger.ChargerTileEntity;
import com.github.technicu.blocks.energyPort.EnergyPortTileEntity;
import com.github.technicu.blocks.fluidTank.FluidTankTileEntity;
import com.github.technicu.blocks.furnaceGenerator.FurnaceGeneratorTileEntity;
import com.github.technicu.blocks.machineController.MachineControllerTileEntity;
import com.github.technicu.blocks.metalPress.MetalPressTileEntity;
import com.github.technicu.blocks.waterMill.WaterMillTileEntity;
import com.github.technicu.capabilities.CapabilityProviderEnergy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Technicu.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCapabilities {

    @SubscribeEvent
    public static void attachCapabilitiesET(AttachCapabilitiesEvent<TileEntity> event) {
        if (event.getObject() instanceof AlloySmelterTileEntity) {
            AlloySmelterTileEntity entity = (AlloySmelterTileEntity) event.getObject();
            event.addCapability(new ResourceLocation(Technicu.MOD_ID, "energy"), new CapabilityProviderEnergy());

        } else if (event.getObject() instanceof ChargerTileEntity) {
            ChargerTileEntity entity = (ChargerTileEntity) event.getObject();
            event.addCapability(new ResourceLocation(Technicu.MOD_ID, "energy"), new CapabilityProviderEnergy());

        } else if (event.getObject() instanceof FurnaceGeneratorTileEntity) {
            FurnaceGeneratorTileEntity entity = (FurnaceGeneratorTileEntity) event.getObject();
            event.addCapability(new ResourceLocation(Technicu.MOD_ID, "energy"), new CapabilityProviderEnergy());

        } else if (event.getObject() instanceof MachineControllerTileEntity) {
            MachineControllerTileEntity entity = (MachineControllerTileEntity) event.getObject();
            event.addCapability(new ResourceLocation(Technicu.MOD_ID, "energy"), new CapabilityProviderEnergy());

        } else if (event.getObject() instanceof MetalPressTileEntity) {
            MetalPressTileEntity entity = (MetalPressTileEntity) event.getObject();
            event.addCapability(new ResourceLocation(Technicu.MOD_ID, "energy"), new CapabilityProviderEnergy());

        } else if (event.getObject() instanceof FluidTankTileEntity) {
            FluidTankTileEntity entity = (FluidTankTileEntity) event.getObject();
            //event.addCapability(new ResourceLocation(Technicu.MOD_ID,"fluid"),new ModFluidProvider());

        } else if (event.getObject() instanceof WaterMillTileEntity) {
            WaterMillTileEntity entity = (WaterMillTileEntity) event.getObject();
            event.addCapability(new ResourceLocation(Technicu.MOD_ID, "energy"), new CapabilityProviderEnergy());

        }
    }
}
