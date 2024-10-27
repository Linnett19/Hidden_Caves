package com.linnett.hidden_caves;

import com.linnett.hidden_caves.block.ModBlocks;
import com.linnett.hidden_caves.entity.ModEntities;
import com.linnett.hidden_caves.item.HiddenCavesModTabs;
import com.linnett.hidden_caves.item.ModItems;
import com.linnett.hidden_caves.particles.HCparticleRegistry;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Hidden_caves.MOD_ID)
public class Hidden_caves {

    public static final String MOD_ID = "hidden_caves";
    private static final Logger LOGGER = LogUtils.getLogger();


    public Hidden_caves() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        HiddenCavesModTabs.register(modEventBus);


        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        HCparticleRegistry.register(modEventBus);
        modEventBus.addListener(this::commonSetup);

        ModEntities.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                ComposterBlock.COMPOSTABLES.put(ModItems.STREAM_LILY.get(), 0.4f);
                ComposterBlock.COMPOSTABLES.put(ModItems.STREAM_LILY_PAD.get(), 0.4f);
            });
        }
    }
}
