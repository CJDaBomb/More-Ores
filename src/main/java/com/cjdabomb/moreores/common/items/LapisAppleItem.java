package com.cjdabomb.moreores.common.items;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemHandlerHelper;

public class LapisAppleItem extends Item{
	
	public LapisAppleItem(Item.Properties builder) {
		super(builder);
	}
	
	public ItemStack onItemUseFinish(ItemStack stack, PlayerEntity PlayerEntity) {
		
		if(random.nextFloat() < 0.5f) {	
			
			ItemStack stack1 = new ItemStack(Items.ENCHANTED_BOOK);
	        stack1.addEnchantment(Enchantments.MENDING, 1);
			ItemHandlerHelper.giveItemToPlayer(PlayerEntity, stack1);
			
			
		}
		return stack;
	}


}
