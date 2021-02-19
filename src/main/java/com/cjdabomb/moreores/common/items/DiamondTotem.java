package com.cjdabomb.moreores.common.items;

import com.cjdabomb.moreores.core.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class DiamondTotem extends Item{

	public DiamondTotem(Properties properties) {
		super(properties);
	}
  @Override
public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
      
	  ItemStack apple = new ItemStack(ItemInit.DIAMOND_APPLE.get());
	  ItemHandlerHelper.giveItemToPlayer(playerIn, apple);
	  playerIn.getCooldownTracker().setCooldown(this, 12000);
	
	  return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
  }
}
