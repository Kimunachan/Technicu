package com.github.technicu.blocks;

import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class ModBlockStateProperties {

    public static final DirectionProperty FACING_HORIZONTAL = DirectionProperty.create("facing",Direction.EAST,Direction.WEST,Direction.NORTH,Direction.SOUTH);
    public static final DirectionProperty FACING_ALLWAY = DirectionProperty.create("facing",Direction.EAST,Direction.WEST,Direction.NORTH,Direction.SOUTH,Direction.DOWN,Direction.UP);
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
}
