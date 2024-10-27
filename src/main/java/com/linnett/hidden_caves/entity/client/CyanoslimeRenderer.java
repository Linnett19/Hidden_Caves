package com.linnett.hidden_caves.entity.client;

import com.linnett.hidden_caves.Hidden_caves;
import com.linnett.hidden_caves.entity.custom.CyanoslimeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class CyanoslimeRenderer extends MobRenderer<CyanoslimeEntity, CyanoslimeModel> {
    public CyanoslimeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CyanoslimeModel(pContext.bakeLayer(ModModelLayers.CYANOSLIME_LAYER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(CyanoslimeEntity pEntity) {
        return new ResourceLocation(Hidden_caves.MOD_ID, "textures/entity/cyanoslime.png");

    }

    @Nullable
    @Override
    protected RenderType getRenderType(CyanoslimeEntity p_115322_, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
        return RenderType.entityTranslucent(this.getTextureLocation(p_115322_));
    }

    @Override
    public void render(CyanoslimeEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
