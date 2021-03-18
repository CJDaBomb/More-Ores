package com.cjdabomb.moreores.common.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class CobaltSwordItem extends SwordItem {


    public CobaltSwordItem(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
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
