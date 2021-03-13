package com.cjdabomb.moreores.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ShadowPickaxeItem extends PickaxeItem {
    public ShadowPickaxeItem(IItemTier p_i48478_1_, int p_i48478_2_, float p_i48478_3_, Properties p_i48478_4_) {
        super(p_i48478_1_, p_i48478_2_, p_i48478_3_, p_i48478_4_);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack stack, @NotNull World worldIn, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity entityIn) {
        super.mineBlock(stack, worldIn, state, pos, entityIn);
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    if (asItem().isCorrectToolForDrops(state)) {
                        worldIn.removeBlock(pos.offset(i, j, k), true);
                    }

                }

            }
        }
        return true;
    }
}
