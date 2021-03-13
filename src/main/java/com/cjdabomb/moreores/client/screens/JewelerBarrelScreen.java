package com.cjdabomb.moreores.client.screens;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.containers.JewelerBarrelContainer;
import com.cjdabomb.moreores.common.te.JewelerBarrelTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class JewelerBarrelScreen extends ContainerScreen<JewelerBarrelContainer> {

	private static final ResourceLocation JEWELER_BARREL_GUI = new ResourceLocation(MoreOres.MOD_ID,
			"textures/gui/jeweler_barrel.png");
	private static final ResourceLocation JEWELER_BARREL_UPGRADE = new ResourceLocation(MoreOres.MOD_ID,
			"textures/gui/jeweler_barrel_upgrade.png");
	private static final ResourceLocation JEWELER_BARREL_UPGRADE_2 = new ResourceLocation(MoreOres.MOD_ID,
			"textures/gui/jeweler_barrel_upgrade2.png");
	private static final ResourceLocation JEWELER_BARREL_UPGRADE_3 = new ResourceLocation(MoreOres.MOD_ID,
			"textures/gui/jeweler_barrel_upgrade3.png");
	private static final ResourceLocation JEWELER_BARREL_UPGRADE_4 = new ResourceLocation(MoreOres.MOD_ID,
			"textures/gui/jeweler_barrel_upgrade4.png");
	private static final ResourceLocation CONFIRM_BUTTON = new ResourceLocation(MoreOres.MOD_ID,
			"textures/gui/confirm_button.png");
	private static final ResourceLocation CAN_CONFIRM_BUTTON = new ResourceLocation(MoreOres.MOD_ID,
			"textures/gui/can_confirm_button.png");

	public PlayerEntity player;

	private Button myButton;
	final int relX = (this.width - this.imageWidth) / 2;
	final int relY = (this.height - this.imageHeight) / 2;

	public JewelerBarrelScreen(JewelerBarrelContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);

		this.myButton = new Button(relX + 10, relY + 10, 160, 20, // pos and size
				new StringTextComponent(""), (button) -> buttonPressed(), (button, matrixStack, x, y) -> {
				});

		this.leftPos = 0;
		this.topPos = 0;

		if (JewelerBarrelContainer.upgrade == 0) {
			this.imageWidth = 180;
			this.imageHeight = 150;
		} else if (JewelerBarrelContainer.upgrade == 1) {
			this.imageWidth = 180;
			this.imageHeight = 166;
		} else if (JewelerBarrelContainer.upgrade == 2) {
			this.imageWidth = 180;
			this.imageHeight = 182;
		} else if (JewelerBarrelContainer.upgrade == 3) {
			this.imageWidth = 180;
			this.imageHeight = 198;
		} else if (JewelerBarrelContainer.upgrade == 4) {
			this.imageWidth = 180;
			this.imageHeight = 214;
		}
	}


	public JewelerBarrelScreen(PlayerEntity player, JewelerBarrelContainer container, PlayerInventory playerInventory) {
		super(container, playerInventory, new StringTextComponent(""));
        this.player = player;

    }


	private void buttonPressed() {
		if (JewelerBarrelTileEntity.canConfirm) {
			if (JewelerBarrelContainer.upgrade < 5)
				JewelerBarrelContainer.upgrade++;
			this.menu.getSlot(97).remove(1);
			this.menu.syncContainer();

		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(@NotNull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
		this.myButton.render(matrixStack, mouseX, mouseY, partialTicks);

		RenderSystem.color4f(1F, 1F, 1F, 1F);
		if (JewelerBarrelTileEntity.canConfirm) {
			Minecraft.getInstance().getTextureManager().bind(CAN_CONFIRM_BUTTON);
			int x = (this.width - this.imageWidth) / 2;
			int y = (this.height - this.imageHeight) / 2;
			blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		} else {
			Minecraft.getInstance().getTextureManager().bind(CONFIRM_BUTTON);
			int x = (this.width - this.imageWidth) / 2;
			int y = (this.height - this.imageHeight) / 2;
			blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		}
	}

	@Override
	protected void renderLabels(@NotNull MatrixStack matrixStack, int x, int y) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), (float) this.inventoryLabelX,
				(float) this.inventoryLabelY, 4210752);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(@NotNull MatrixStack matrixStack, float partialTicks, int mouseX,
							int mouseY) {
		if (JewelerBarrelContainer.upgrade == 0) {
			RenderSystem.color4f(1F, 1F, 1F, 1F);
			assert this.minecraft != null;
			this.minecraft.textureManager.bind(JEWELER_BARREL_GUI);
			int x = (this.width - this.imageWidth) / 2;
			int y = (this.height - this.imageHeight) / 2;
			this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		} else if (JewelerBarrelContainer.upgrade == 1) {
			RenderSystem.color4f(1F, 1F, 1F, 1F);
			assert this.minecraft != null;
			this.minecraft.textureManager.bind(JEWELER_BARREL_UPGRADE);
			int x = (this.width - this.imageWidth) / 2;
			int y = (this.height - this.imageHeight) / 2;
			this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		} else if (JewelerBarrelContainer.upgrade == 2) {
			RenderSystem.color4f(1F, 1F, 1F, 1F);
			assert this.minecraft != null;
			this.minecraft.textureManager.bind(JEWELER_BARREL_UPGRADE_2);
			int x = (this.width - this.imageWidth) / 2;
			int y = (this.height - this.imageHeight) / 2;
			this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		} else if (JewelerBarrelContainer.upgrade == 3) {
			RenderSystem.color4f(1F, 1F, 1F, 1F);
			assert this.minecraft != null;
			this.minecraft.textureManager.bind(JEWELER_BARREL_UPGRADE_3);
			int x = (this.width - this.imageWidth) / 2;
			int y = (this.height - this.imageHeight) / 2;
			this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		} else if (JewelerBarrelContainer.upgrade == 4) {
			RenderSystem.color4f(1F, 1F, 1F, 1F);
			assert this.minecraft != null;
			this.minecraft.textureManager.bind(JEWELER_BARREL_UPGRADE_4);
			int x = (this.width - this.imageWidth) / 2;
			int y = (this.height - this.imageHeight) / 2;
			this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		}
	}

	@Override
	protected void init() {
		super.init();
	}
	
	public void open(PlayerEntity player) {
		Minecraft.getInstance().setScreen(new JewelerBarrelScreen(player, menu, inventory));
	}

}
