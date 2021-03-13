package com.cjdabomb.moreores.common.items;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class DiamondChorusFruitItem extends Item {

	public DiamondChorusFruitItem(Item.Properties builder) {
		super(builder);
	}
	
	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull LivingEntity entityLiving) {
	      ItemStack itemstack = super.finishUsingItem(stack, worldIn, entityLiving);
	      if (!worldIn.isClientSide) {
	         double d0 = entityLiving.getX();
	         double d1 = entityLiving.getY();
	         double d2 = entityLiving.getZ();

	         for(int i = 0; i < 16; ++i) {
	            double d3 = entityLiving.getX() + (entityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
	            double d4 = MathHelper.clamp(entityLiving.getY() + (double)(entityLiving.getRandom().nextInt(16) - 8), 0.0D, worldIn.getHeight() - 1);
	            double d5 = entityLiving.getZ() + (entityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
	            if (entityLiving.isPassenger()) {
	               entityLiving.stopRiding();
	            }

	            if (entityLiving.randomTeleport(d3, d4, d5, true)) {
	               SoundEvent soundevent = entityLiving instanceof FoxEntity ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
	               worldIn.playSound(null, d0, d1, d2, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
	               entityLiving.playSound(soundevent, 1.0F, 1.0F);
	               break;
	            }
	         }

	         if (entityLiving instanceof PlayerEntity) {
	            ((PlayerEntity)entityLiving).getCooldowns().addCooldown(this, 20);
	         }
	         
	         
	      }

	      return itemstack;
	   }
	
	public ActionResult<ItemStack> onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft, LivingEntity playerIn, Hand handIn) {
		
	if(random.nextFloat() < .1f) {	
		EndermiteEntity entity = new EndermiteEntity(null, worldIn);
        entity.setPos(playerIn.getX(), playerIn.getY(), playerIn.getX());
        worldIn.addFreshEntity(entity);
		}
	return ActionResult.success(playerIn.getItemInHand(handIn));
	
	}
}	
	
