package com.cjdabomb.moreores.common.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class VoidElytraItem extends ElytraItem {

	public VoidElytraItem(Properties elytra) {
		super(elytra);
		 
	}
	

	@Override
	public Rarity getRarity(ItemStack stack) {
		return Rarity.EPIC;
	}
	
	public static boolean isUsable(ItemStack stack) {
	      return stack.getDamage() < stack.getMaxDamage() - 1;
	   }
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
	      return repair.getItem() == Items.PHANTOM_MEMBRANE;
	   }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      EquipmentSlotType equipmentslottype = EquipmentSlotType.CHEST;
	      ItemStack itemstack1 = playerIn.getItemStackFromSlot(equipmentslottype);
	      if (itemstack1.isEmpty()) {
	         playerIn.setItemStackToSlot(equipmentslottype, itemstack.copy());
	         itemstack.setCount(0);
	         return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
	      } else {
	         return ActionResult.resultFail(itemstack);
	      }
	   }
	
	 @Override
	   public boolean canElytraFly(ItemStack stack, net.minecraft.entity.LivingEntity entity) {
	      return ElytraItem.isUsable(stack);
	   }

	 @Override
	   public boolean elytraFlightTick(ItemStack stack, net.minecraft.entity.LivingEntity entity, int flightTicks) {
	      if (!entity.world.isRemote && (flightTicks + 1) % 20 == 0) {
	         stack.damageItem(1, entity, e -> e.sendBreakAnimation(net.minecraft.inventory.EquipmentSlotType.CHEST));
	      }
	      return true;
	   }
}
