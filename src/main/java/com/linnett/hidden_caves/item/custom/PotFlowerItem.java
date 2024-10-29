package com.linnett.hidden_caves.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class PotFlowerItem extends ItemNameBlockItem {
    public static final String WATER_TAG = "WaterFilled";
    public static final String HONEY_TAG = "HoneyFilled";
    public PotFlowerItem(Block block, Properties properties) {
        super(block, properties);
    }

    public boolean isFilledWithWater(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean(WATER_TAG);
    }

    public void setFilledWithWater(ItemStack stack, boolean filled) {
        stack.getOrCreateTag().putBoolean(WATER_TAG, filled);
    }
    public boolean isFilledWithHoney(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean(HONEY_TAG);
    }

    public void setFilledWithHoney(ItemStack stack, boolean filled) {
        stack.getOrCreateTag().putBoolean(HONEY_TAG, filled);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        BlockHitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hitResult.getBlockPos();

            if (level.getFluidState(pos).is(FluidTags.WATER)
                    && !isFilledWithWater(stack)
                    && !isFilledWithHoney(stack)) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);

                ItemStack filledPotFlower = new ItemStack(this);
                setFilledWithWater(filledPotFlower, true);

                stack.shrink(1);
                if (!player.getInventory().add(filledPotFlower)) {
                    player.drop(filledPotFlower, false);
                }

                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }

            if (level.getBlockState(pos).is(Blocks.BEEHIVE)
                    && level.getBlockState(pos).getValue(BeehiveBlock.HONEY_LEVEL) == 5
                    && !isFilledWithWater(stack)
                    && !isFilledWithHoney(stack)) {
                level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);

                ItemStack filledPotFlower = new ItemStack(this);
                setFilledWithHoney(filledPotFlower, true);

                stack.shrink(1);
                if (!player.getInventory().add(filledPotFlower)) {
                    player.drop(filledPotFlower, false); // Если инвентарь полон, выбрасываем предмет
                }

                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }
        }

        return InteractionResultHolder.pass(stack);
    }
}