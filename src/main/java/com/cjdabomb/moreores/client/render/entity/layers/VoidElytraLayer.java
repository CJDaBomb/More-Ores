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


public class VoidElytraLayer<T extends LivingEntity, M extends BipedModel<T>> extends LayerRenderer<T, M> {
	


	public VoidElytraLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
		// TODO Auto-generated constructor stub
	}


	private static final ResourceLocation TEXTURE_VOID_ELYTRA = new ResourceLocation("moreores:textures/entities/void_elytra.png");
	private final ElytraModel<T> modelVoidElytra = new ElytraModel<>();
	
	
	
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
	      ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EquipmentSlotType.CHEST);
	      if (shouldRender(itemstack, entitylivingbaseIn)) {
	         ResourceLocation resourcelocation;
	         if (entitylivingbaseIn instanceof AbstractClientPlayerEntity) {
	            AbstractClientPlayerEntity abstractclientplayerentity = (AbstractClientPlayerEntity)entitylivingbaseIn;
	            if (abstractclientplayerentity.isPlayerInfoSet() && abstractclientplayerentity.getLocationElytra() != null) {
	               resourcelocation = abstractclientplayerentity.getLocationElytra();
	            } else if (abstractclientplayerentity.hasPlayerInfo() && abstractclientplayerentity.getLocationCape() != null && abstractclientplayerentity.isWearing(PlayerModelPart.CAPE)) {
	               resourcelocation = abstractclientplayerentity.getLocationCape();
	            } else {
	               resourcelocation = getElytraTexture(itemstack, entitylivingbaseIn);
	            }
	         } else {
	            resourcelocation = getElytraTexture(itemstack, entitylivingbaseIn);
	         }

	         matrixStackIn.push();
	         matrixStackIn.translate(0.0D, 0.0D, 0.125D);
	         this.getEntityModel().copyModelAttributesTo(this.modelVoidElytra);
	         this.modelVoidElytra.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	         IVertexBuilder ivertexbuilder = ItemRenderer.getArmorVertexBuilder(bufferIn, RenderType.getArmorCutoutNoCull(resourcelocation), false, itemstack.hasEffect());
	         this.modelVoidElytra.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	         matrixStackIn.pop();
	      }
	   }


	   public boolean shouldRender(ItemStack stack, T entity) {
	      return stack.getItem() == ItemInit.VOID_ELYTRA.get();
	   }


	   public ResourceLocation getElytraTexture(ItemStack stack, T entity) {
	      return TEXTURE_VOID_ELYTRA;
	   }
}
