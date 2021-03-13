package com.cjdabomb.moreores.core.init;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

public class PotionRecipeRegistry  {

	public static boolean addRecipe(Ingredient input, Ingredient ingredient, ItemStack output) {
		
		return BrewingRecipeRegistry.addRecipe(Ingredient.of(PotionUtils
				.setPotion(new ItemStack(Items.POTION), Potions.WATER)),Ingredient.of(new ItemStack(Items.DIAMOND)),
				PotionUtils.setPotion(new ItemStack(Items.POTION),Potions.LONG_REGENERATION));

	}
		

}
