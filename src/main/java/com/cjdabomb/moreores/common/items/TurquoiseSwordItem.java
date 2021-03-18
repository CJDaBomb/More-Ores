package com.cjdabomb.moreores.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;

public class TurquoiseSwordItem extends SwordItem {

    public TurquoiseSwordItem(IItemTier p_i48460_1_, int p_i48460_2_, float p_i48460_3_, Properties p_i48460_4_) {
        super(p_i48460_1_, p_i48460_2_, p_i48460_3_, p_i48460_4_);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean p_77663_5_) {
        if (worldIn.isRaining()) {
            if (random.nextFloat() < 0.3F) {
                setDamage(stack, getDamage(stack) - 1);
            }
        }
    }
}
