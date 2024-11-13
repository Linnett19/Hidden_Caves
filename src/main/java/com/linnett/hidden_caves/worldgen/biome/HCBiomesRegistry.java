package com.linnett.hidden_caves.worldgen.biome;


import com.linnett.hidden_caves.Hidden_caves;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class HCBiomesRegistry {
    private static final Music CAVE_MUSIC = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES);
    public static final ResourceKey<Biome> SUBTERRANEAN_STREAMS = ResourceKey.create(Registries.BIOME, new ResourceLocation(Hidden_caves.MOD_ID,"subterranean_streams"));
    public static void bootstrap(BootstapContext<Biome> context)
    {
        context.register(SUBTERRANEAN_STREAMS, dessert_lair(context));
    }

    public static float setAmbientLight(Holder<Biome> value) {
        if (value.is(SUBTERRANEAN_STREAMS)) {
            return 100F;
        }
        return 0.0F;
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {

        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static Biome dessert_lair(BootstapContext<Biome> context){
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);

        return new Biome.BiomeBuilder().hasPrecipitation(false).downfall(0.8f).temperature(1f).generationSettings(biomeBuilder.build()).mobSpawnSettings(spawnBuilder.build()).specialEffects((new BiomeSpecialEffects.Builder().skyColor(0xecaab95).fogColor(0xcaab95).waterFogColor(0xcaab95).waterColor(0xa1d3ed).grassColorOverride(0xb5eda1).backgroundMusic(CAVE_MUSIC).build())).build();
    }
}
