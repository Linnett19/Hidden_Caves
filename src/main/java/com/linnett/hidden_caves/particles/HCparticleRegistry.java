package com.linnett.hidden_caves.particles;

import com.google.common.base.Function;
import com.linnett.hidden_caves.Hidden_caves;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class HCparticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Hidden_caves.MOD_ID);
    public static final RegistryObject<SimpleParticleType> BIO_PARTICLES = PARTICLE_TYPES.register("bio_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);
    }
}