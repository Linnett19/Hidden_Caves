package com.linnett.hidden_caves.event;

import com.linnett.hidden_caves.biome.BiomeSampler;
import com.linnett.hidden_caves.biome.CaveBiomeVisulals;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class ClientEvents {
    private static float lastSampledFogNearness = 0.0F;
    private static float lastSampledWaterFogFarness = 0.0F;
    private static Vec3 lastSampledFogColor = Vec3.ZERO;
    private static Vec3 lastSampledWaterFogColor = Vec3.ZERO;

    public static float getLastSampledFogNearness() {
        return lastSampledFogNearness;
    }

    public static float getLastSampledWaterFogFarness() {
        return lastSampledWaterFogFarness;
    }

    public static Vec3 getLastSampledFogColor() {
        return lastSampledFogColor;
    }

    public static Vec3 getLastSampledWaterFogColor() {
        return lastSampledWaterFogColor;
    }


    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void fogRender(ViewportEvent.RenderFog event) {
        if (event.isCanceled()) {
            //another mod has cancelled fog rendering.
            return;
        }
        //some mods incorrectly set the RenderSystem fog start and end directly, so this will have to do as a band-aid...
        float defaultFarPlaneDistance = RenderSystem.getShaderFogEnd();
        float defaultNearPlaneDistance = RenderSystem.getShaderFogStart();
        if (event.getCamera().getFluidInCamera() == FogType.WATER) {
            float farness = lastSampledWaterFogFarness;
            if (farness != 1.0F) {
                event.setCanceled(true);
                event.setFarPlaneDistance(defaultFarPlaneDistance * farness);
            }
        } else if (event.getMode() == FogRenderer.FogMode.FOG_TERRAIN) {
            float nearness = lastSampledFogNearness;
            boolean flag = Math.abs(nearness) - 1.0F < 0.01F;
            if (flag) {
                event.setCanceled(true);
                event.setNearPlaneDistance(defaultNearPlaneDistance * nearness);
            }
        }
    }

    private static float calculateBiomeAmbientLight(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return CaveBiomeVisulals.getBiomeAmbientLight(player.level().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesFloat(player.level(), player.position(), CaveBiomeVisulals::getBiomeAmbientLight);
        }
    }

    private static float calculateBiomeFogNearness(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        float nearness;
        if (i == 0) {
            nearness = CaveBiomeVisulals.getBiomeFogNearness(player.level().getBiome(player.blockPosition()));
        } else {
            nearness = BiomeSampler.sampleBiomesFloat(player.level(), player.position(), CaveBiomeVisulals::getBiomeFogNearness);
        }
        return nearness;
    }

    private static float calculateBiomeWaterFogFarness(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        float farness;
        if (i == 0) {
            farness = CaveBiomeVisulals.getBiomeWaterFogFarness(player.level().getBiome(player.blockPosition()));
        } else {
            farness = BiomeSampler.sampleBiomesFloat(player.level(), player.position(), CaveBiomeVisulals::getBiomeWaterFogFarness);
        }
        return farness;
    }

    private static Vec3 calculateBiomeLightColor(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return CaveBiomeVisulals.getBiomeLightColorOverride(player.level().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesVec3(player.level(), player.position(), CaveBiomeVisulals::getBiomeLightColorOverride);
        }
    }

    private static Vec3 calculateBiomeFogColor(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        Vec3 vec3;
        if (i == 0) {
            vec3 = ((ClientLevel) player.level()).effects().getBrightnessDependentFogColor(Vec3.fromRGB24(player.level().getBiomeManager().getNoiseBiomeAtPosition(player.blockPosition()).value().getFogColor()), 1.0F);
        } else {
            vec3 = ((ClientLevel) player.level()).effects().getBrightnessDependentFogColor(BiomeSampler.sampleBiomesVec3(player.level(), player.position(), biomeHolder -> Vec3.fromRGB24(biomeHolder.value().getFogColor())), 1.0F);
        }
        return vec3;
    }

    private Vec3 calculateBiomeWaterFogColor(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        Vec3 vec3;
        if (i == 0) {
            vec3 = ((ClientLevel) player.level()).effects().getBrightnessDependentFogColor(Vec3.fromRGB24(player.level().getBiomeManager().getNoiseBiomeAtPosition(player.blockPosition()).value().getWaterFogColor()), 1.0F);
        } else {
            vec3 = ((ClientLevel) player.level()).effects().getBrightnessDependentFogColor(BiomeSampler.sampleBiomesVec3(player.level(), player.position(), biomeHolder -> Vec3.fromRGB24(biomeHolder.value().getWaterFogColor())), 1.0F);
        }
        return vec3;
    }
}