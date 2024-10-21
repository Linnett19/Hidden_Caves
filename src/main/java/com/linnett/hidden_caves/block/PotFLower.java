package com.linnett.hidden_caves.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PotFLower extends Block {
    public PotFLower(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        VoxelShape base = Block.box(3, 0, 3, 13, 12, 13);
        VoxelShape topSmall = Block.box(4, 12, 4, 12, 14, 12);
        VoxelShape topBig = Block.box(3, 14, 3, 13, 16, 13);
        return Shapes.or(base, topSmall, topBig);
    }
}
