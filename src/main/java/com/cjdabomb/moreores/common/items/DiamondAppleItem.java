package com.cjdabomb.moreores.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class DiamondAppleItem extends Item {


	public DiamondAppleItem(Properties properties) {
		super(properties);
		
	}
	
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		ItemStack stack1 = new ItemStack(Items.DIAMOND);
		ItemHandlerHelper.giveItemToPlayer((PlayerEntity) entityLiving, stack1);
		return stack;
	} 
}
