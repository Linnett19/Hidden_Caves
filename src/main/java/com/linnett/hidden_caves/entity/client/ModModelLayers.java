package com.linnett.hidden_caves.entity.client;

import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.entity.ModEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hidden_caves.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModModelLayers {
    public static final ModelLayerLocation CYANOSLIME_LAYER = new ModelLayerLocation(
            new ResourceLocation(Hidden_caves.MOD_ID, "cyanoslime"), "main");

    @SubscribeEvent
    public static void onRegisterLayer(EntityRenderersEvent.RegisterLayerDefinitions pEvent) {
        pEvent.registerLayerDefinition(CYANOSLIME_LAYER, CyanoslimeModel::createBodyLayer);
    }


}


