package com.cjdabomb.moreores.core.event;

import java.util.UUID;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.core.init.ItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;

@EventBusSubscriber(modid = MoreOres.MOD_ID, bus = Bus.FORGE)
public class EventHandler {
	
	@SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event){
        if(event.getEntity().world.isRemote()) return;
        if (!(event.getEntityLiving() instanceof ServerPlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity)event.getEntityLiving();
        player.sendMessage(ITextComponent.getTextComponentOrEmpty("player taking damage"), UUID.randomUUID());

        if(event.getAmount() >= player.getHealth()) {
        	player.sendMessage(ITextComponent.getTextComponentOrEmpty("fatal damage"), UUID.randomUUID());
            ItemStack itemstack = null;

            for (Hand hand : Hand.values()) {
                ItemStack itemstack1 = player.getHeldItem(hand);
                if (itemstack1.getItem() == ItemInit.EMERALD_TOTEM.get() || itemstack1.getItem() == ItemInit.DIAMOND_TOTEM.get() ) {
                    itemstack = itemstack1.copy();
                    itemstack1.shrink(1);
                    break;
                }
            }

            if (itemstack != null) {
            	player.sendMessage(ITextComponent.getTextComponentOrEmpty("has custom totem"), UUID.randomUUID());
                player.setHealth(1.0F);
                player.clearActivePotions();
                if(itemstack.getItem() == ItemInit.EMERALD_TOTEM.get()){
                player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 9000, 1));
                player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 10000, 1));
                player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 8000, 0));
                player.addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 1000000, 4));
                ItemStack apple = new ItemStack(Items.GOLDEN_APPLE);
                player.addItemStackToInventory(apple);
                }
                else if(itemstack.getItem() == ItemInit.DIAMOND_TOTEM.get()){
                	 player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 9000, 1));
                     player.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 10000, 1));
                     player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 8000, 0));
                     player.addPotionEffect(new EffectInstance(Effects.SPEED, 9000, 2));
                     player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 10000, 2));
                     player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 8000, 1));
                     player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 8000, 0));
                     ItemStack apple2 = new ItemStack(ItemInit.DIAMOND_APPLE.get());
                     player.addItemStackToInventory(apple2);
                }
                player.world.setEntityState(player, (byte) 35);
            }
            if(itemstack != null) event.setCanceled(true);
        }
    }

	@SubscribeEvent
	public static void onPlayerRightCLickBLockWithItem(final PlayerInteractEvent.RightClickBlock event) {
		
		
		if(event.getItemStack().getItem() == Items.TOTEM_OF_UNDYING) {
				if(event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.DIAMOND_BLOCK) {
					event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), Constants.BlockFlags.BLOCK_UPDATE);
					event.getItemStack().shrink(1);
						if(Math.random() < .5f) {
							ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), ItemInit.DIAMOND_TOTEM.get().getDefaultInstance());
						}
				}
				else if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.EMERALD_BLOCK) {
						event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), Constants.BlockFlags.BLOCK_UPDATE);
						event.getItemStack().shrink(1);
							if(Math.random() < .5f) {
								ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), ItemInit.EMERALD_TOTEM.get().getDefaultInstance());
							}
					}
			}
		
	}
	
	
	
}
