package com.cjdabomb.moreores.common.items;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class CobaltPickaxeItem extends PickaxeItem {
    public CobaltPickaxeItem(IItemTier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
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
