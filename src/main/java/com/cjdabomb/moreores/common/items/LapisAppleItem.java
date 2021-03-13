package com.cjdabomb.moreores.common.items;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class LapisAppleItem extends Item{
	
	public LapisAppleItem(Item.Properties builder) {
		super(builder);
	}
	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull LivingEntity entityLiving) {
			if(random.nextFloat() < 0.5f) {	
			
			ItemStack stack1 = new ItemStack(Items.ENCHANTED_BOOK);
	        stack1.enchant(Enchantments.MENDING, 1);
			ItemHandlerHelper.giveItemToPlayer((PlayerEntity) entityLiving, stack1);
			
			
		}
		return super.finishUsingItem(stack, worldIn, entityLiving);
	} 


}
