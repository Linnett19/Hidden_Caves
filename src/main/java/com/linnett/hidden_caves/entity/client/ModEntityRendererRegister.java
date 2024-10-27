package com.linnett.hidden_caves.entity.client;


import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.entity.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hidden_caves.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModEntityRendererRegister {

    @SubscribeEvent
    public static void onRegisterEntityRender(EntityRenderersEvent.RegisterRenderers pEvent) {
        pEvent.registerEntityRenderer(ModEntities.CYANOSLIME.get(), CyanoslimeRenderer::new);
    }
}
