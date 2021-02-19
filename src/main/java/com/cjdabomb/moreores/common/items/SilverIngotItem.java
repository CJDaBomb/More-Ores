package com.cjdabomb.moreores.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SilverIngotItem extends Item {

	public SilverIngotItem(Properties properties) {
		super(properties);
		
	}
	
	@Override
	public boolean isPiglinCurrency(ItemStack stack) {
		return true;
	}
}

	
