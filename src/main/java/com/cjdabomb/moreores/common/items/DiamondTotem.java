package com.cjdabomb.moreores.common.items;

import com.cjdabomb.moreores.core.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class DiamondTotem extends Item{

	public DiamondTotem(Properties properties) {
		super(properties);
	}
  @Override
  public @NotNull ActionResult<ItemStack> use(@NotNull World worldIn, @NotNull PlayerEntity playerIn, @NotNull Hand handIn) {
      
	  ItemStack apple = new ItemStack(ItemInit.DIAMOND_APPLE.get());
	  ItemHandlerHelper.giveItemToPlayer(playerIn, apple);
	  playerIn.getCooldowns().addCooldown(this, 12000);
	
	  return ActionResult.success(playerIn.getItemInHand(handIn));
  }
}
