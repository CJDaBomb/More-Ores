package com.cjdabomb.moreores.common.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class SunstoneAxeItem extends AxeItem {
    public SunstoneAxeItem(IItemTier p_i48530_1_, float p_i48530_2_, float p_i48530_3_, Properties p_i48530_4_) {
        super(p_i48530_1_, p_i48530_2_, p_i48530_3_, p_i48530_4_);
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity livingIn, LivingEntity entityIn) {
        livingIn.setSecondsOnFire(5);
        return true;
    }
}
