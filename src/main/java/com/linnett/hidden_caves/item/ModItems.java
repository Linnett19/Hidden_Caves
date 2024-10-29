package com.linnett.hidden_caves.item;

import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.block.ModBlocks;
import com.linnett.hidden_caves.item.custom.PotFlowerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Hidden_caves.MOD_ID);

    public static final RegistryObject<Item> CAVE_BOOK = ITEMS.register("cave_book",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WIP_ITEM = ITEMS.register("wip_item",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WATER_LILY_POTTERY_SHERD = ITEMS.register("water_lily_pottery_sherd",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POT_FLOWER = ITEMS.register("pot_flower",
            () -> new PotFlowerItem(ModBlocks.POT_FLOWER.get(),new Item.Properties()));
    public static final RegistryObject<Item> BIOLUMINESCENT_CREAM = ITEMS.register("bioluminescent_cream",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> VINE_ROPE = ITEMS.register("vine_rope",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> JUG_FLOWER = ITEMS.register("jug_flower",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STREAM_LILY = ITEMS.register("stream_lily",
            () -> new PlaceOnWaterBlockItem(ModBlocks.STREAM_LILY.get() ,(new Item.Properties())));

    public static final RegistryObject<Item> STREAM_LILY_PAD = ITEMS.register("stream_lily_pad",
            () -> new PlaceOnWaterBlockItem(ModBlocks.STREAM_LILY_PAD.get() ,(new Item.Properties())));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
