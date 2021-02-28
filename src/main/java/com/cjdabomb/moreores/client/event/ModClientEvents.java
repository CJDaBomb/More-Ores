package com.cjdabomb.moreores.client.event;

import java.util.List;
import java.util.Map;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.client.render.entity.layers.VoidElytraLayer;
import com.cjdabomb.moreores.client.screens.JewelerBarrelScreen;
import com.cjdabomb.moreores.core.init.ContainerTypeInit;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = MoreOres.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ContainerTypeInit.JEWELER_BARREL_CONTAINER.get(), JewelerBarrelScreen::new);
		
		Map<String, PlayerRenderer> skinMap = event.getMinecraftSupplier().get().getRenderManager().getSkinMap();
		addVoidElytraLayer(skinMap.get("default"));
		addVoidElytraLayer(skinMap.get("slim"));
		
		
	}

	public static void addVoidElytraLayer(PlayerRenderer entityRendererIn) {
		List<LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>> layers = ObfuscationReflectionHelper
				.getPrivateValue(LivingRenderer.class, entityRendererIn, "field_177097_h");
		if (layers != null) {
			layers.add(new VoidElytraLayer<>(entityRendererIn));
		}
	}

}
