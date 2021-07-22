package com.github.technicu.blocks.steelFence;

import com.github.technicu.setup.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;

public class SteelFenceBlock extends FenceBlock
{
    public SteelFenceBlock(Properties p_i48399_1_) {
        super(p_i48399_1_);
    }

    @Override
    public boolean connectsTo(BlockState blockState, boolean p_220111_2_, Direction direction) {
        Block block = blockState.getBlock();
        boolean flag = this.isSameFence(block);
        boolean flag1 = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(blockState, direction);
        return !isExceptionForConnection(block) && p_220111_2_ || flag || flag1;

    }

    private boolean isSameFence(Block block) {
        return block.is(ModBlocks.STEEL_FENCE.get());
    }
}
