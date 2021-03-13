package com.cjdabomb.moreores.common.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ShadowAxeItem extends AxeItem {
    public ShadowAxeItem(IItemTier p_i48530_1_, float p_i48530_2_, float p_i48530_3_, Properties p_i48530_4_) {
        super(p_i48530_1_, p_i48530_2_, p_i48530_3_, p_i48530_4_);
    }

    @Override
    public boolean mineBlock(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityIn) {
        for (int i = -3; i < 4; i++) {
            for (int j = -8; j < 16; j++) {
                for (int k = -3; k < 4; k++) {
                    if (state.isToolEffective(ToolType.AXE)) {
                        worldIn.removeBlock(pos.offset(i, j, k), true);
                    }

                }

            }
        }
        return true;
    }
}
