package com.cjdabomb.moreores.common.items;


import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class RedstoneAppleItem extends Item{
	
	public RedstoneAppleItem(Item.Properties builder) {
		super(builder);
	}
	
@Override
public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
	if(random.nextFloat() < 0.5f) {	
		ItemStack xpStack = new ItemStack(Items.EXPERIENCE_BOTTLE);
		Random random = new Random();
		int drops = random.nextInt(18);
		xpStack.setCount(drops+2);	
		ItemHandlerHelper.giveItemToPlayer((PlayerEntity) entityLiving, xpStack);
					
	}
	return super.onItemUseFinish(stack, worldIn, entityLiving);
	} 
}
