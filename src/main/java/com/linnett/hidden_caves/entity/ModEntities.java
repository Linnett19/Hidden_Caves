package com.linnett.hidden_caves.entity;

import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.entity.custom.CyanoslimeEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Hidden_caves.MOD_ID);

    public static final RegistryObject<EntityType<CyanoslimeEntity>> CYANOSLIME =
            ENTITY_TYPES.register("cyanoslime", () -> EntityType.Builder.of(CyanoslimeEntity::new, MobCategory.CREATURE)
                    .sized(1.3f, 1.3f).build("cyanoslime"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
