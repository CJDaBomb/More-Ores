package com.cjdabomb.moreores.common.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;

public class CobaltShovelItem extends ShovelItem {
    public CobaltShovelItem(IItemTier p_i48469_1_, float p_i48469_2_, float p_i48469_3_, Properties p_i48469_4_) {
        super(p_i48469_1_, p_i48469_2_, p_i48469_3_, p_i48469_4_);
    }

    int p_92097_1_;

    @Override
    public boolean isDamaged(ItemStack stack) {
        if (this.getItem() instanceof ArmorItem && random.nextFloat() < 0.5F) {
            return false;
        } else {

            return random.nextInt(p_92097_1_ + 1) > 0;
        }
    }
}
