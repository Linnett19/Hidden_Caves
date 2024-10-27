package com.linnett.hidden_caves.entity.custom;

import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.entity.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Hidden_caves.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModAttributesRegister {

    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent pEvent) {
        pEvent.put(ModEntities.CYANOSLIME.get(), CyanoslimeEntity.createAttributes().build());

    }




}
