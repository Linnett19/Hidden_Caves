package com.linnett.hidden_caves.block.custom;

import com.linnett.hidden_caves.item.custom.PotFlowerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PotFLowerBlock extends Block {
    public static final BooleanProperty WATER_POT = BooleanProperty.create("water_pot");
    public static final BooleanProperty HONEY_POT = BooleanProperty.create("honey_pot");

    public PotFLowerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(WATER_POT, false)
                .setValue(HONEY_POT, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATER_POT, HONEY_POT);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        VoxelShape base = Block.box(3, 0, 3, 13, 12, 13);
        VoxelShape topSmall = Block.box(4, 12, 4, 12, 14, 12);
        VoxelShape topBig = Block.box(3, 14, 3, 13, 16, 13);
        return Shapes.or(base, topSmall, topBig);
    }


    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity p_49850_, ItemStack stack) {
        super.setPlacedBy(level, pos, state, p_49850_, stack);

        if (stack.getItem() instanceof PotFlowerItem potFlowerItem) {
            if (potFlowerItem.isFilledWithWater(stack)) {
                level.setBlock(pos, state.setValue(WATER_POT, true).setValue(HONEY_POT, false), 3);
            } else if (potFlowerItem.isFilledWithHoney(stack)) {
                level.setBlock(pos, state.setValue(HONEY_POT, true).setValue(WATER_POT, false), 3);
            } else {
                level.setBlock(pos, state.setValue(WATER_POT, false).setValue(HONEY_POT, false), 3);
            }
        }
    }
}