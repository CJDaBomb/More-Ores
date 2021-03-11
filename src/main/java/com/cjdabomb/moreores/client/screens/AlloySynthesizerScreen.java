package com.cjdabomb.moreores.client.screens;

import java.util.Objects;
import java.util.Set;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.containers.AlloySynthesizerContainer;
import com.cjdabomb.moreores.common.te.AlloySynthesizerTileEntity;
import com.cjdabomb.moreores.core.init.RecipeSerializerInit;
import com.cjdabomb.moreores.core.recipe.AlloySmeltingRecipe;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AlloySynthesizerScreen extends ContainerScreen<AlloySynthesizerContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(MoreOres.MOD_ID, "textures/gui/alloy_synthesizer.png");

    private int tick = 0;
    private int rand = 0;	

    public AlloySynthesizerScreen(AlloySynthesizerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 183;

    }


    //*Init the Screen and Buttons
    @Override
    protected void init() {
        super.init();
        System.out.println("Init Screen");
    }
    
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
    	super.renderBackground(matrixStack, 0);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY) {
    	super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
        this.font.drawString(matrixStack, this.title.getUnformattedComponentText(), 8.0f, 6.0f, 4210752);
    }

    @SuppressWarnings({ "deprecation", "resource" })
	protected void renderRecipes() {
        AlloySynthesizerTileEntity tile = (AlloySynthesizerTileEntity) this.container.tile;
        if (Objects.requireNonNull(tile.getWorld()).isRemote) {
            //*Gets the Recipes
            Set<IRecipe<?>> recipes = AlloySynthesizerTileEntity.findRecipeByType(RecipeSerializerInit.ALLOY_TYPE, tile.getWorld());
            int yOffset = 0;
            int renders = 0;
            RenderSystem.pushMatrix();
            RenderSystem.enableRescaleNormal();
            for (IRecipe<?> r : recipes) {
                if (r instanceof AlloySmeltingRecipe && renders < 5) {
                    AlloySmeltingRecipe rr = (AlloySmeltingRecipe) r;
                    ItemStack s1 = rr.getInput1Stack();
                    ItemStack s2 = rr.getInput2Stack();
                    ItemStack s3 = rr.getRecipeOutput();
                    //!Renders Item inside GUI
                    this.itemRenderer.zLevel = 0f;
                    
                    renders++;
                    this.itemRenderer.zLevel = 100.0F;
                    //*Render the ItemStack1 into the GUI
                    this.itemRenderer.renderItemAndEffectIntoGUI(s1, this.guiLeft - 110 + 6, this.guiTop + yOffset + 6);
                    this.itemRenderer.renderItemOverlays(this.font, s1, this.guiLeft - 110 + 6, this.guiTop + yOffset + 6);
                    //*Render the ItemStack2 into the GUI
                    this.itemRenderer.renderItemAndEffectIntoGUI(s2, this.guiLeft - 110 + 26, this.guiTop + yOffset + 6);
                    this.itemRenderer.renderItemOverlays(this.font, s2, this.guiLeft - 110 + 26, this.guiTop + yOffset + 6);
                    //*Render the ItemStack3 into the GUI
                    this.itemRenderer.renderItemAndEffectIntoGUI(s3, this.guiLeft - 110 + 77, this.guiTop + yOffset + 6);
                    this.itemRenderer.renderItemOverlays(this.font, s3, this.guiLeft - 110 + 77, this.guiTop + yOffset + 6);
                    //*Increase the Y_Offset
                    yOffset += 29;
                    this.itemRenderer.zLevel = 0f;
                }
            }
            RenderSystem.popMatrix();
            RenderSystem.enableDepthTest();
        }
    }

	@SuppressWarnings("deprecation")
	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		 RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
	        //*Checking for Null
	        if (this.minecraft == null) {
	            return;
	        }
	        //*Bind the Texture that is rendered in the Background
	        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
	        //*Halves the Screen Width(So it is centered Vertically)
	        int X = (this.width - this.xSize) / 2;
	        //*Halves the Screen Height(So it is centered Horizontally)
	        int Y = (this.height - this.ySize) / 2;
	        int j = this.guiTop;
	        int l = this.guiLeft;
	        this.blit(matrixStack, l, j, 0, 0, this.xSize, this.ySize);
	        AlloySynthesizerTileEntity tile = (AlloySynthesizerTileEntity) this.container.tile;

	        if (tile.isSmelting) {
	            if (tick == 20) {
	                tick = 0;
	                rand = (int) (2 * ((Math.floor(Math.random() * 3)) + 1)); // 2-6
	            }
	            tick++;
	            //*Middle Smelting Bar (Adding Randomness for a "sparkling" effect
	            this.blit(matrixStack, x + 51, y + 23, 176, 13 + rand, 66, 2);
	            //*Progress Canister
	            //*Checking if the Recipe is actually still going
	            if (tile.recipeTime > 0) {
	                //*Checking if the Recipe is already finished
	                if (tile.smelttime > tile.recipeTime) {
	                    //*Render the Container as Full
	                    this.blit(matrixStack, X + 101, Y + 57 - 16, 176, 36 - 16, 11, 16);
	                } else {
	                    //*Get the amount of Pixels needed to be rendered onto the Screen
	                    float dec = ((float) tile.smelttime / (float) tile.recipeTime);
	                    int pixels = (int) Math.floor(dec * 17);
	                    if (pixels == 17) {
	                        pixels = 16;
	                    }
	                    //*Rendering the Pixels on the Screen
	                    this.blit(matrixStack, X + 101, Y + 57 - pixels, 176, 36 - pixels, 11, pixels);
	                }

	            }
	        }
	        //*Flame 1 rendering if it is burning and also if the Burntimes are greater than 0
	        if (tile.isBurning && tile.burntime1 > 0 && tile.totalburntime1 > 0) {
	            float dec = ((float) tile.burntime1 / (float) tile.totalburntime1);
	            int flame1 = (int) Math.floor(dec * 15);
	            this.blit(matrixStack, X + 35, Y + 50 - flame1, 176, 12 - flame1, 14, flame1);
	        }
	        //*Flame 2 rendering if it is burning and also if the Burntimes are greater than 0
	        if (tile.isBurning && tile.burntime2 > 0 && tile.totalburntime2 > 0) {
	            float dec = ((float) tile.burntime2 / (float) tile.totalburntime2);
	            int flame2 = (int) Math.floor(dec * 15);
	            this.blit(matrixStack, X + 120, Y + 50 - flame2, 176, 12 - flame2, 14, flame2);
	        }
		
	}

    

}

