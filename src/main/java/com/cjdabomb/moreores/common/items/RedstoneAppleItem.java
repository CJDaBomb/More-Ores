package com.cjdabomb.moreores.common.items;


import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.ItemHandlerHelper;

public class RedstoneAppleItem extends Item{
	
	public RedstoneAppleItem(Item.Properties builder) {
		super(builder);
	}
	
public ItemStack onItemUseFinish(ItemStack stack, PlayerEntity PlayerEntity) {
		
		if(random.nextFloat() < 0.5f) {	
			ItemStack stack2 = new ItemStack(Items.EXPERIENCE_BOTTLE);
			Random rand1 = new Random();
			int drops = rand1.nextInt(18);
			ItemHandlerHelper.giveItemToPlayer(PlayerEntity, stack2);
			stack2.setCount(2+drops);				
		}
		return stack;
	}

}
