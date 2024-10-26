package com.linnett.hidden_caves.block.custom;

import com.linnett.hidden_caves.particles.HCparticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BioLuminescentBlock extends Block {
    public BioLuminescentBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void animateTick(BlockState p_220827_, Level p_220828_, BlockPos p_220829_, RandomSource p_220830_) {
        super.animateTick(p_220827_, p_220828_, p_220829_, p_220830_);
        Vec3 randpos = p_220829_.getCenter().add(new Vec3(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5));
        p_220828_.addParticle(HCparticleRegistry.BIO_PARTICLES.get(), randpos.x, randpos.y, randpos.z, 0, 0.1, 0);
    }

    @Override
    public void fallOn(Level world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.causeFallDamage(fallDistance, 0.0F, world.damageSources().fall());
    }

    @Override
    public void updateEntityAfterFallOn(BlockGetter level, Entity entity) {
        if (entity.isSuppressingBounce()) {
            super.updateEntityAfterFallOn(level, entity);
        } else {
            Vec3 velocity = entity.getDeltaMovement();
            entity.setDeltaMovement(velocity.x, -velocity.y * 0.9, velocity.z);


            if (level instanceof Level && Math.abs(velocity.y) > 0.1) {
                Level world = (Level) level;
                BlockPos pos = entity.blockPosition();
                for (int i = 0; i < 7; i++) {
                    double offsetX = (Math.random() - 0.5) * 0.5;
                    double offsetY = Math.random() * 0.5;
                    double offsetZ = (Math.random() - 0.5) * 0.5;
                    world.addParticle(HCparticleRegistry.BIO_PARTICLES.get(),
                            pos.getX() + 0.5 + offsetX,
                            pos.getY() + offsetY,
                            pos.getZ() + 0.5 + offsetZ,
                            0, 0.1, 0);
                }
            }
        }
    }
}


