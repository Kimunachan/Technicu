package com.github.technicu.blocks.energyPort;


import com.github.technicu.blocks.machineController.MachineControllerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class EnergyPortBlock extends Block
{
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public EnergyPortBlock(Properties properties)
    {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTrace)
    {
        if (!world.isClientSide())
        {
            if (MachineControllerTileEntity.alloyBasicFormed())
            {

            }

            if (MachineControllerTileEntity.alloyAdvancedFormed())
            {

            }
        }

        return ActionResultType.CONSUME;
    }
}
