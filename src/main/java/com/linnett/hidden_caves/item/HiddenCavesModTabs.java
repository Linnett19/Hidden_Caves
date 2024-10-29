package com.linnett.hidden_caves.item;

import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class HiddenCavesModTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Hidden_caves.MOD_ID);


    public static final RegistryObject<CreativeModeTab> GARBAGE_DUMP = CREATIVE_MODE_TABS.register("garbage_dump",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack((ModItems.WIP_ITEM.get())))
                    .title(Component.translatable("creativetab.garbage_dump"))
                    .displayItems((itemDisplayParameters, pOutput) -> {

                        pOutput.accept(ModItems.CAVE_BOOK.get());




                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> WIND_GORGES = CREATIVE_MODE_TABS.register("wind_gorges",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack((ModItems.WIP_ITEM.get())))
                    .title(Component.translatable("creativetab.wind_gorges"))
                    .displayItems((itemDisplayParameters, pOutput) -> {

                        pOutput.accept(ModItems.CAVE_BOOK.get());




                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> UNDERGROUND_SWAMPS = CREATIVE_MODE_TABS.register("underground_swamps",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack((ModItems.WIP_ITEM.get())))
                    .title(Component.translatable("creativetab.underground_swamps"))
                    .displayItems((itemDisplayParameters, pOutput) -> {

                        pOutput.accept(ModItems.CAVE_BOOK.get());




                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> UNDERGROUND_RIVERS = CREATIVE_MODE_TABS.register("subterranean_streams",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack((ModBlocks.STREAM_LILY.get())))
                    .title(Component.translatable("creativetab.subterranean_streams"))
                    .displayItems((itemDisplayParameters, pOutput) -> {

                        pOutput.accept(ModItems.CAVE_BOOK.get());





                        pOutput.accept(ModItems.WATER_LILY_POTTERY_SHERD.get());

                        pOutput.accept(ModBlocks.RIVER_PEBBLES.get());
                        pOutput.accept(ModBlocks.RIVER_PEBBLES_GOLDEN_ORE.get());
                        pOutput.accept(ModBlocks.CHISELED_RIVER_PEBBLES_BRICKS.get());
                        pOutput.accept(ModBlocks.POLISHED_RIVER_PEBBLES.get());
                        pOutput.accept(ModBlocks.RIVER_PEBBLES_BRICKS.get());
                        pOutput.accept(ModBlocks.MOSSY_RIVER_PEBBLES.get());
                        pOutput.accept(ModBlocks.RIVER_MOSS.get());
                        pOutput.accept(ModItems.VINE_ROPE.get());
                        pOutput.accept(ModItems.JUG_FLOWER.get());
                        pOutput.accept(ModItems.POT_FLOWER.get());
                        pOutput.accept(ModBlocks.RIVER_GRASS.get());
                        pOutput.accept(ModItems.STREAM_LILY.get());
                        pOutput.accept(ModItems.STREAM_LILY_PAD.get());
                        pOutput.accept(ModItems.BIOLUMINESCENT_CREAM.get());
                        pOutput.accept(ModBlocks.BIOLUMINESCENCE_BLOCK.get());
                        pOutput.accept(ModBlocks.RIVER_ROOTS_BLOCK.get());




                    })
                    .build());








    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register((eventBus));
    }

}
