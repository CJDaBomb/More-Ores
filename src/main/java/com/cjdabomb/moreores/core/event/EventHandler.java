package com.cjdabomb.moreores.core.event;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.core.init.ItemInit;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
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
        if(event.getEntity().level.isClientSide()) return;
        if (!(event.getEntityLiving() instanceof ServerPlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity)event.getEntityLiving();

        if(event.getAmount() >= player.getHealth()) {
            ItemStack itemstack = null;

            for (Hand hand : Hand.values()) {
                ItemStack itemstack1 = player.getItemInHand(hand);
                if (itemstack1.getItem() == ItemInit.EMERALD_TOTEM.get() || itemstack1.getItem() == ItemInit.DIAMOND_TOTEM.get() ) {
                    itemstack = itemstack1.copy();
                    itemstack1.shrink(1);
                    break;
                }
            }

            if (itemstack != null) {
                player.setHealth(1.0F);
                player.removeAllEffects();
                if(itemstack.getItem() == ItemInit.EMERALD_TOTEM.get()){
                player.addEffect(new EffectInstance(Effects.REGENERATION, 9000, 1));
                player.addEffect(new EffectInstance(Effects.ABSORPTION, 10000, 1));
                player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 8000, 0));
                player.addEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 1000000, 4));
                ItemStack apple = new ItemStack(Items.GOLDEN_APPLE);
                player.addItem(apple);
                }
                else if(itemstack.getItem() == ItemInit.DIAMOND_TOTEM.get()){
                	 player.addEffect(new EffectInstance(Effects.REGENERATION, 9000, 1));
                     player.addEffect(new EffectInstance(Effects.ABSORPTION, 10000, 1));
                     player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 8000, 0));
                     player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 9000, 2));
                     player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 10000, 2));
                     player.addEffect(new EffectInstance(Effects.JUMP, 8000, 1));
                     player.addEffect(new EffectInstance(Effects.INVISIBILITY, 8000, 0));
                     ItemStack apple2 = new ItemStack(ItemInit.DIAMOND_APPLE.get());
                     player.addItem(apple2);
                }
                player.level.broadcastEntityEvent(player, (byte) 35);
            }
            if(itemstack != null) event.setCanceled(true);
        }
    }

	@SubscribeEvent
	public static void onPlayerRightCLickBLockWithItem(final PlayerInteractEvent.RightClickBlock event) {
		
		
		if(event.getItemStack().getItem() == Items.TOTEM_OF_UNDYING) {
				if(event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.DIAMOND_BLOCK) {
					event.getWorld().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
					event.getItemStack().shrink(1);
						if(Math.random() < .5f) {
							ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), ItemInit.DIAMOND_TOTEM.get().getDefaultInstance());
						}
				}
				else if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.EMERALD_BLOCK) {
						event.getWorld().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
						event.getItemStack().shrink(1);
							if(Math.random() < .5f) {
								ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), ItemInit.EMERALD_TOTEM.get().getDefaultInstance());
							}
					}
			}
		
	}
	
	
	
}
