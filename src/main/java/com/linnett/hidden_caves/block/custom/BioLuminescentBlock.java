package com.linnett.hidden_caves.block.custom;

import com.linnett.hidden_caves.particles.HCparticleRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BioLuminescentBlock extends Block {
    public BioLuminescentBlock(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void animateTick(BlockState p_220827_, Level p_220828_, BlockPos p_220829_, RandomSource p_220830_) {
        super.animateTick(p_220827_, p_220828_, p_220829_, p_220830_);
        Vec3 randpos = p_220829_.getCenter().add(new Vec3(Math.random()-0.5,Math.random()-0.5,Math.random()-0.5));
        p_220828_.addParticle(HCparticleRegistry.BIO_PARTICLES.get(),randpos.x,randpos.y,randpos.z,0,0.1,0);
    }
}
