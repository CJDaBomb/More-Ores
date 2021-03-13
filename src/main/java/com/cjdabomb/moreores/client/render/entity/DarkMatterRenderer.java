package com.cjdabomb.moreores.client.render.entity;

import com.cjdabomb.moreores.common.entities.DarkMatterEntity;
import com.cjdabomb.moreores.core.init.BlockInit;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TNTMinecartRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DarkMatterRenderer extends EntityRenderer<DarkMatterEntity> {
   public DarkMatterRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn);
      this.shadowRadius = 0.5F;
   }
   public static final ResourceLocation darkMatter = new ResourceLocation("moreores:textures/entities/dark_matter");

   @Override
   public void render(DarkMatterEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
      matrixStackIn.pushPose();
      matrixStackIn.translate(0.0D, 0.5D, 0.0D);
      if ((float)entityIn.getLife() - partialTicks + 1.0F < 10.0F) {
         float f = 1.0F - ((float)entityIn.getLife() - partialTicks + 1.0F) / 10.0F;
         f = MathHelper.clamp(f, 0.0F, 1.0F);
         f = f * f;
         f = f * f;
         float f1 = 1.0F + f * 0.3F;
         matrixStackIn.scale(f1, f1, f1);
      }

      matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
      matrixStackIn.translate(-0.5D, -0.5D, 0.5D);
      matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
      TNTMinecartRenderer.renderWhiteSolidBlock(BlockInit.DARK_MATTER.get().defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, entityIn.getLife() / 5 % 2 == 0);
      matrixStackIn.popPose();
      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
   }

   

/**
    * Returns the location of an entity's texture.
    */

   	@Override
  	public @NotNull ResourceLocation getTextureLocation(@NotNull DarkMatterEntity entity) {
	 return darkMatter;
  	}




  
}
