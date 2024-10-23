package com.linnett.hidden_caves.block.custom;

import com.linnett.hidden_caves.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PotFLower extends Block {
    public PotFLower(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(WATER_POT, false)
                .setValue(HONEY_POT, false)
        );
    }
    public static final BooleanProperty WATER_POT = BooleanProperty.create("water_pot");
    public static final BooleanProperty HONEY_POT = BooleanProperty.create("honey_pot");
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATER_POT, HONEY_POT);
    }



    @Override
    public InteractionResult use(BlockState state,Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {

        // Проверяем, является ли блок водой
        if (world.getFluidState(pos).getType() == Fluids.WATER) {
            // Проверяем, что у нас уже не установлен флаг WATER_POT
            if (!state.getValue(WATER_POT)) {
                // Изменяем состояние блока
                world.setBlock(pos, state.setValue(WATER_POT, true), 3);
                // Зачёрпываем воду (можно также добавить эффект или звук)
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.PASS;
            }
        }

        return InteractionResult.PASS; // Если блок не является водой, пропускаем действие
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        VoxelShape base = Block.box(3, 0, 3, 13, 12, 13);
        VoxelShape topSmall = Block.box(4, 12, 4, 12, 14, 12);
        VoxelShape topBig = Block.box(3, 14, 3, 13, 16, 13);
        return Shapes.or(base, topSmall, topBig);
    }
}