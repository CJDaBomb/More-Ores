package com.cjdabomb.moreores.common.items;

import com.cjdabomb.moreores.core.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class AluminiumAxeItem extends AxeItem {
    public AluminiumAxeItem(IItemTier p_i48530_1_, float p_i48530_2_, float p_i48530_3_, Properties p_i48530_4_) {
        super(p_i48530_1_, p_i48530_2_, p_i48530_3_, p_i48530_4_);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean p_77663_5_) {
        if (entityIn instanceof LivingEntity) {
            if (((LivingEntity) entityIn).getMainHandItem() == ItemInit.ALUMINIUM_AXE.get().getDefaultInstance()) {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.NIGHT_VISION, 100, 1));
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 100, 0));
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.INVISIBILITY, 100, 1));
            }
        }
    }
}

