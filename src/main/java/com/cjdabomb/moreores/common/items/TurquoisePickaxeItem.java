package com.cjdabomb.moreores.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.world.World;

public class TurquoisePickaxeItem extends PickaxeItem {


    public TurquoisePickaxeItem(IItemTier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
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
