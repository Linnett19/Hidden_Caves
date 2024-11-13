package com.linnett.hidden_caves.misc;

import com.linnett.hidden_caves.event.ClientEvents;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
    public static float lastBiomeAmbientLightAmountPrev = 0;
    public static float lastBiomeAmbientLightAmount = 0;
    @Override
    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

}
