package com.linnett.hidden_caves.event;


import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.particles.HCparticleRegistry;
import com.linnett.hidden_caves.particles.custom.BioLuminescentParticles;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hidden_caves.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBus {
    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event){
        event.registerSpriteSet(HCparticleRegistry.BIO_PARTICLES.get(), BioLuminescentParticles.Provider::new);

    }
}
