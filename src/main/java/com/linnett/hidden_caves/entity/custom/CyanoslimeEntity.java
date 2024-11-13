package com.linnett.hidden_caves.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public class CyanoslimeEntity extends PathfinderMob {
    public CyanoslimeEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 25.0F)
                .add(Attributes.FOLLOW_RANGE, 40.0F)
                .add(Attributes.ATTACK_DAMAGE, 3.0F)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.1F)
                .add(Attributes.ARMOR, 2F);
    }
}

