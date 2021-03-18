package com.cjdabomb.moreores.common.items;

import com.cjdabomb.moreores.core.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class AlexandriteShovelItem extends ShovelItem {
    public AlexandriteShovelItem(IItemTier p_i48469_1_, float p_i48469_2_, float p_i48469_3_, Properties p_i48469_4_) {
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

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int p_77663_4_, boolean p_77663_5_) {
        if (entityIn instanceof LivingEntity) {
            if (((LivingEntity) entityIn).getMainHandItem() == ItemInit.ALEXANDRITE_SHOVEL.get().getDefaultInstance()) {
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.DIG_SPEED, 100, 1));
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.INVISIBILITY, 100, 1));
                ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 100, 1));
            }
        }
    }
}
