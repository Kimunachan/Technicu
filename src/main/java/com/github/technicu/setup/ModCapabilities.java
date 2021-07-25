package com.github.technicu.setup;


import com.github.technicu.Technicu;
import com.github.technicu.blocks.energyPort.EnergyPortTileEntity;
import com.github.technicu.capabilities.CapabilityProviderEnergy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Technicu.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModCapabilities {

    @SubscribeEvent
    public static void attachCapabilitiesET(AttachCapabilitiesEvent<TileEntity> event){
        if(event.getObject() instanceof EnergyPortTileEntity){
            EnergyPortTileEntity entity = (EnergyPortTileEntity) event.getObject();
            event.addCapability(new ResourceLocation(Technicu.MOD_ID,"energy"),new CapabilityProviderEnergy());
        }
    }
}
