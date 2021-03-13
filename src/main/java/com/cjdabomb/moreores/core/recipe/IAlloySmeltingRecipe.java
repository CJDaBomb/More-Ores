package com.cjdabomb.moreores.core.recipe;

import javax.annotation.Nonnull;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.core.init.RecipeSerializerInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public interface IAlloySmeltingRecipe extends IRecipe<RecipeWrapper> {
    ResourceLocation RECIPE_TYPE_ID = new ResourceLocation(MoreOres.MOD_ID, "alloysmelting");

    @Nonnull
    @Override
    default IRecipeType<?> getType() {
        return RecipeSerializerInit.ALLOY_TYPE;
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    Ingredient getInput1();

    Ingredient getInput2();

    ItemStack getInput1Stack();

    ItemStack getInput2Stack();

    Integer getSmeltTime();


}
