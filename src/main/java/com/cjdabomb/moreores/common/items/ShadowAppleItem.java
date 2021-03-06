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

public class ShadowAppleItem extends Item {
    public ShadowAppleItem(Item.Properties builder) {
        super(builder);
    }

    public boolean isFoil(@NotNull ItemStack stack) {
        return true;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull LivingEntity entityLiving) {
        if (random.nextFloat() < 0.2f) {
            if (random.nextFloat() > 0.6667f) {
                ItemStack godApple = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE);
                ItemHandlerHelper.giveItemToPlayer((PlayerEntity) entityLiving, godApple);
            } else if (random.nextFloat() > 0.3333f) {
                ItemStack totem = new ItemStack(Items.TOTEM_OF_UNDYING);
                ItemHandlerHelper.giveItemToPlayer((PlayerEntity) entityLiving, totem);
            } else {
                ItemStack netherite = new ItemStack(Items.NETHERITE_PICKAXE);
                netherite.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
                ItemHandlerHelper.giveItemToPlayer((PlayerEntity) entityLiving, netherite);
            }
        }

        return super.finishUsingItem(stack, worldIn, entityLiving);
    }
}	
