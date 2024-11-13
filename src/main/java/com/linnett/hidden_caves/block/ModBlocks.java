package com.linnett.hidden_caves.block;

import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.block.custom.BioLuminescentBlock;
import com.linnett.hidden_caves.block.custom.RiverGrassBlock;
import com.linnett.hidden_caves.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Hidden_caves.MOD_ID);


    public static final RegistryObject<Block> RIVER_PEBBLES = registerBlock("river_pebbles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> RIVER_PEBBLES_GOLDEN_ORE = registerBlock("river_pebbles_golden_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)));

    public static final RegistryObject<Block> POLISHED_RIVER_PEBBLES = registerBlock("polished_river_pebbles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CHISELED_RIVER_PEBBLES_BRICKS = registerBlock("chiseled_river_pebbles_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> RIVER_PEBBLES_BRICKS = registerBlock("river_pebbles_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));


    public static final RegistryObject<Block> MOSSY_RIVER_PEBBLES = registerBlock("mossy_river_pebbles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)));

    public static final RegistryObject<Block> RIVER_MOSS = registerBlock("river_moss",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK)));


    public static final RegistryObject<Block> RIVER_ROOTS = registerBlock("river_roots",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT)));

    public static final RegistryObject<Block> RIVER_GRASS = registerBlock("river_grass",
            () -> new RiverGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS)));

    public static final RegistryObject<Block> RIVER_ROOTS_BLOCK = registerBlock("river_roots_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ROOTED_DIRT)));

    public static final RegistryObject<Block> STREAM_LILY = BLOCKS.register("stream_lily",
            () -> new WaterlilyBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)) {
                @Override
                public PlantType getPlantType(BlockGetter level, BlockPos pos) {
                    return PlantType.WATER;
                }
            });

    public static final RegistryObject<Block> STREAM_LILY_PAD = BLOCKS.register("stream_lily_pad",
            () -> new WaterlilyBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)) {
                @Override
                public PlantType getPlantType(BlockGetter level, BlockPos pos) {
                    return PlantType.WATER;
                }
            });


    public static final RegistryObject<Block> BIOLUMINESCENCE_BLOCK = registerBlock("bioluminescence_block",
            () -> new BioLuminescentBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK)
                    .strength(1.5f)
                    .lightLevel((state) -> 7)) {

            });

    public static final RegistryObject<Block> MARBLE = registerBlock("marble",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> MARBLE_BRICKS = registerBlock("marble_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> POLISHED_MARBLE = registerBlock("polished_marble",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> CHISELED_MARBLE_BRICKS = registerBlock("chiseled_marble_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> MARBLE_LAPIS_ORE = registerBlock("marble_lapis_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.LAPIS_ORE)));


    public static final RegistryObject<Block> NACRE = registerBlock("nacre",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> NACRE_BRICKS = registerBlock("nacre_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> POLISHED_NACRE = registerBlock("polished_nacre",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> CHISELED_NACRE_BRICKS = registerBlock("chiseled_nacre_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));







    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}