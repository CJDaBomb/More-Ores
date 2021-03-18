package com.cjdabomb.moreores.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class SunstoneSwordItem extends SwordItem {
    public SunstoneSwordItem(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity livingIn, LivingEntity entityIn) {
        livingIn.setSecondsOnFire(10);
        return true;
    }
}
