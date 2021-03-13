package com.cjdabomb.moreores.client.render.entity.layers;

import com.cjdabomb.moreores.core.init.ItemInit;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.ElytraModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class VoidElytraLayer<T extends LivingEntity, M extends BipedModel<T>> extends LayerRenderer<T, M> {
	


	public VoidElytraLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
		// TODO Auto-generated constructor stub
	}


	private static final ResourceLocation TEXTURE_VOID_ELYTRA = new ResourceLocation("moreores:textures/entities/void_elytra.png");
	private final ElytraModel<T> modelVoidElytra = new ElytraModel<>();
	
	
	
	
	@Override
	public void render(@NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
	      ItemStack itemstack = entitylivingbaseIn.getItemBySlot(EquipmentSlotType.CHEST);
	      if (shouldRender(itemstack, entitylivingbaseIn)) {
	         ResourceLocation resourcelocation;
	         if (entitylivingbaseIn instanceof AbstractClientPlayerEntity) {
	            AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity)entitylivingbaseIn;
	            if (abstractclientplayerentity.isElytraLoaded() && abstractclientplayerentity.getElytraTextureLocation() != null) {
	               resourcelocation = abstractclientplayerentity.getElytraTextureLocation();
	            } else if (abstractclientplayerentity.isCapeLoaded() && abstractclientplayerentity.getCloakTextureLocation() != null && abstractclientplayerentity.isModelPartShown(PlayerModelPart.CAPE)) {
	               resourcelocation = abstractclientplayerentity.getCloakTextureLocation();
	            } else {
	               resourcelocation = getElytraTexture(itemstack, entitylivingbaseIn);
	            }
	         } else {
	            resourcelocation = getElytraTexture(itemstack, entitylivingbaseIn);
	         }

	         matrixStackIn.pushPose();
	         matrixStackIn.translate(0.0D, 0.0D, 0.125D);
	         this.getParentModel().copyPropertiesTo(this.modelVoidElytra);
	         this.modelVoidElytra.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	         IVertexBuilder ivertexbuilder = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(resourcelocation), false, itemstack.hasFoil());
	         this.modelVoidElytra.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	         matrixStackIn.popPose();
	      }
	   }


	   public boolean shouldRender(ItemStack stack, T entity) {
	      return stack.getItem() == ItemInit.VOID_ELYTRA.get();
	   }


	   public ResourceLocation getElytraTexture(ItemStack stack, T entity) {
	      return TEXTURE_VOID_ELYTRA;
	   }
}
