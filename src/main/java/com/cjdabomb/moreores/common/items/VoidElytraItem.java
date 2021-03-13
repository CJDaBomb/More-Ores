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
import org.jetbrains.annotations.NotNull;

public class VoidElytraItem extends ElytraItem {

	public VoidElytraItem(Properties elytra) {
		super(elytra);
		 
	}
	

	@Override
	public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
		return Rarity.EPIC;
	}


	public static boolean isUsable(ItemStack stack) {
	      return stack.getDamageValue() < stack.getMaxDamage() - 1;
	   }
	
	@Override
	public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repair) {
	      return repair.getItem() == Items.PHANTOM_MEMBRANE;
	   }
	
	@Override
	public @NotNull ActionResult<ItemStack> use(@NotNull World worldIn, PlayerEntity playerIn, @NotNull Hand handIn) {
	      ItemStack itemstack = playerIn.getItemInHand(handIn);
	      EquipmentSlotType equipmentslottype = EquipmentSlotType.CHEST;
	      ItemStack itemstack1 = playerIn.getItemBySlot(equipmentslottype);
	      if (itemstack1.isEmpty()) {
	         playerIn.setItemSlot(equipmentslottype, itemstack.copy());
	         itemstack.setCount(0);
	         return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
	      } else {
	         return ActionResult.fail(itemstack);
	      }
	   }
	
	 @Override
	   public boolean canElytraFly(@NotNull ItemStack stack, net.minecraft.entity.@NotNull LivingEntity entity) {
	      return ElytraItem.isFlyEnabled(stack);
	   }

	 @Override
	   public boolean elytraFlightTick(@NotNull ItemStack stack, net.minecraft.entity.LivingEntity entity, int flightTicks) {
	      if (!entity.level.isClientSide && (flightTicks + 1) % 20 == 0) {
	         stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(net.minecraft.inventory.EquipmentSlotType.CHEST));
	      }
	      return true;
	   }
}
