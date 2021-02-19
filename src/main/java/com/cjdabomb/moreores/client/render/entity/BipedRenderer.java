package com.cjdabomb.moreores.client.render.entity;

import com.cjdabomb.moreores.client.render.entity.layers.VoidElytraLayer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BipedRenderer<T extends MobEntity, M extends BipedModel<T>> extends MobRenderer<T, M> {
	private static final ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");

	public BipedRenderer(EntityRendererManager renderManagerIn, M entityModelIn, float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}
	public BipedRenderer(EntityRendererManager p_i232471_1_, M p_i232471_2_, float p_i232471_3_, float p_i232471_4_, float p_i232471_5_, float p_i232471_6_) {
	      super(p_i232471_1_, p_i232471_2_, p_i232471_3_);
	      this.addLayer(new VoidElytraLayer<>(this));
	   }
	
	public ResourceLocation getEntityTexture(T entity) {
	      return DEFAULT_RES_LOC;
	   }

}
