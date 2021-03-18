package com.cjdabomb.moreores.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.DamageSource;
import org.jetbrains.annotations.NotNull;

public class ShadowSwordItem extends SwordItem {
    public ShadowSwordItem(IItemTier tier, int i, float p_i48460_3_, Properties items) {
        super(tier, i, p_i48460_3_, items);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity livingEntityIn, @NotNull LivingEntity entityIn) {


        if (random.nextFloat() < 0.25) {
            if (entityIn instanceof PlayerEntity) {
                livingEntityIn.hurt(DamageSource.playerAttack((PlayerEntity) entityIn), livingEntityIn.getMaxHealth());
            }
        }
        return true;
    }
}
