package com.github.technicu.blocks.energyPort;

import com.github.technicu.setup.ModBlocks;
import com.github.technicu.setup.ModContainerTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.Objects;

public class EnergyPortContainer extends Container
{
    //<editor-fold>
    public final EnergyPortTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    private IIntArray fields;

    public EnergyPortContainer(final int windowId, final PlayerInventory playerInventory, final EnergyPortTileEntity tileEntity) {
        super(ModContainerTypes.ENERGY_PORT.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos());
    }

    public EnergyPortContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    private static EnergyPortTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "PlayerInventory cannot be null");
        Objects.requireNonNull(data, "PacketBuffer cannot be null");

        final TileEntity tileEntity = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileEntity instanceof EnergyPortTileEntity) {
            return (EnergyPortTileEntity) tileEntity;
        }
        throw new IllegalStateException("Tileentity is not correct!");
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return stillValid(canInteractWithCallable,player, ModBlocks.ENERGY_PORT.get());
    }

    public LazyOptional<IEnergyStorage> getCapabilityFromTE(){
        return this.tileEntity.getCapability(CapabilityEnergy.ENERGY);
    }

    //</editor-fold>
}
