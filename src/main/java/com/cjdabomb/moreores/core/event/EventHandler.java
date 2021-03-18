package com.cjdabomb.moreores.core.event;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.core.init.ItemInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = MoreOres.MOD_ID, bus = Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity().level.isClientSide()) return;
        if (!(event.getEntityLiving() instanceof ServerPlayerEntity)) return;

        ServerPlayerEntity player = (ServerPlayerEntity) event.getEntityLiving();

        if (event.getAmount() >= player.getHealth()) {
            ItemStack itemstack = null;

            for (Hand hand : Hand.values()) {
                ItemStack itemstack1 = player.getItemInHand(hand);
                if (itemstack1.getItem() == ItemInit.EMERALD_TOTEM.get() || itemstack1.getItem() == ItemInit.DIAMOND_TOTEM.get()) {
                    itemstack = itemstack1.copy();
                    itemstack1.shrink(1);
                    break;
                }
            }

            if (itemstack != null) {
                player.setHealth(1.0F);
                player.removeAllEffects();
                if (itemstack.getItem() == ItemInit.EMERALD_TOTEM.get()) {
                    player.addEffect(new EffectInstance(Effects.REGENERATION, 9000, 1));
                    player.addEffect(new EffectInstance(Effects.ABSORPTION, 10000, 1));
                    player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 8000, 0));
                    player.addEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 1000000, 4));
                    ItemStack apple = new ItemStack(Items.GOLDEN_APPLE);
                    player.addItem(apple);
                } else if (itemstack.getItem() == ItemInit.DIAMOND_TOTEM.get()) {
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
            if (itemstack != null) event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerRightCLickBLockWithItem(final PlayerInteractEvent.RightClickBlock event) {


        if (event.getItemStack().getItem() == Items.TOTEM_OF_UNDYING) {
            if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.DIAMOND_BLOCK) {
                event.getWorld().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                event.getItemStack().shrink(1);
                if (Math.random() < .5f) {
                    ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), ItemInit.DIAMOND_TOTEM.get().getDefaultInstance());
                }
            } else if (event.getWorld().getBlockState(event.getPos()).getBlock() == Blocks.EMERALD_BLOCK) {
                event.getWorld().setBlock(event.getPos(), Blocks.AIR.defaultBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                event.getItemStack().shrink(1);
                if (Math.random() < .5f) {
                    ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), ItemInit.EMERALD_TOTEM.get().getDefaultInstance());
                }
            }
        }

    }

    @SubscribeEvent
    public static void AttackEntityEvent(final AttackEntityEvent attackEntityEvent) {
        Random rand = new Random();
        if (rand.nextFloat() < 0.3) {
            if (attackEntityEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.COBALT_AXE.get().getDefaultInstance()) {

                attackEntityEvent.getPlayer().giveExperiencePoints((int) ((Math.random() * (15 - 5)) + 5));
            } else if (attackEntityEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.COBALT_SWORD.get().getDefaultInstance()) {

                attackEntityEvent.getPlayer().giveExperiencePoints((int) ((Math.random() * (20 - 10)) + 10));
            }
        }

        if (attackEntityEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_AXE.get().getDefaultInstance() ||
                attackEntityEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_PICKAXE.get().getDefaultInstance() ||
                attackEntityEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_SHOVEL.get().getDefaultInstance() ||
                attackEntityEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_SWORD.get().getDefaultInstance()) {
            List<Effect> effects = ForgeRegistries.POTIONS.getValues().parallelStream()
                    .filter(e -> !e.isInstantenous()).collect(Collectors.toList());
            Effect effect = effects.get((int) (Math.random() * effects.size()));
            attackEntityEvent.getPlayer().addEffect(new EffectInstance(effect, 100, 1));
        }
    }

    @SubscribeEvent
    public static void onBlockBreakEvent(final BlockEvent.BreakEvent breakEvent) {
        Random rand = new Random();
        if (rand.nextFloat() < 0.3) {
            if (breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.COBALT_AXE.get().getDefaultInstance() ||
                    breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.COBALT_PICKAXE.get().getDefaultInstance() ||
                    breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.COBALT_SHOVEL.get().getDefaultInstance() ||
                    breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.COBALT_SWORD.get().getDefaultInstance()) {
                breakEvent.getPlayer().giveExperiencePoints((int) ((Math.random() * (5 - 1)) + 1));
            }
        }

        if (breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_AXE.get().getDefaultInstance() ||
                breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_AXE.get().getDefaultInstance() ||
                breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_PICKAXE.get().getDefaultInstance() ||
                breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_SHOVEL.get().getDefaultInstance() ||
                breakEvent.getPlayer().getItemInHand(Hand.MAIN_HAND) == ItemInit.JASPER_SWORD.get().getDefaultInstance()) {
            List<Effect> effects = ForgeRegistries.POTIONS.getValues().parallelStream()
                    .filter(e -> !e.isInstantenous()).collect(Collectors.toList());
            Effect effect = effects.get((int) (Math.random() * effects.size()));
            breakEvent.getPlayer().addEffect(new EffectInstance(effect, 100, 1));
        }

    }

    private static final List<Item> alistofsavableitems = Arrays.asList(new Item[]{ItemInit.SAPPHIRE_AXE.get(),
            ItemInit.SAPPHIRE_PICKAXE.get(), ItemInit.SAPPHIRE_SHOVEL.get(), ItemInit.SAPPHIRE_SWORD.get()});

    private static Map<UUID, List<ItemStack>> savedData = new HashMap<>();

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void entityDeathEvent(final LivingDeathEvent deathEvent) {
        if (deathEvent.getEntityLiving() instanceof PlayerEntity) {
            List<ItemStack> itemsToSave = ((PlayerEntity) deathEvent.getEntityLiving()).inventory.items
                    .stream().filter(stack -> alistofsavableitems.contains(stack.getItem())).collect(Collectors.toList());
            savedData.put(deathEvent.getEntityLiving().getUUID(), itemsToSave);
            ((PlayerEntity) deathEvent.getEntityLiving()).inventory.items.removeAll(itemsToSave);
        }
    }

    @SubscribeEvent
    public static void respawnEvent(final PlayerEvent.PlayerRespawnEvent respawnEvent) {
        if (savedData.containsKey(respawnEvent.getPlayer().getUUID())) {
            for (ItemStack stack : savedData.get(respawnEvent.getPlayer().getUUID())) {
                respawnEvent.getPlayer().inventory.add(stack);
            }
        }
    }
}


