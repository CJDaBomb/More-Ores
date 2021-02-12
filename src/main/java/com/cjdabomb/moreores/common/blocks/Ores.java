package com.cjdabomb.moreores.common.blocks;

import java.util.Random;

import com.cjdabomb.moreores.core.init.BlockInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Ores extends Block{

	public Ores(AbstractBlock.Properties properties) {
		super(properties);

	}
	
	protected int getExperience(Random rand) {
	      if (this == BlockInit.ALEXANDRITE_ORE.get()) {
	         return MathHelper.nextInt(rand, 3, 9);
	      } else if (this == BlockInit.ALUMINIUM_ORE.get()) {
		         return MathHelper.nextInt(rand, 2, 6);
	      } else if (this == BlockInit.COBALT_ORE.get()) {
		         return MathHelper.nextInt(rand, 3, 7);
	      } else if (this == BlockInit.JASPER_ORE.get()) {
			     return MathHelper.nextInt(rand, 3, 7);
	      } else if (this == BlockInit.ROSE_QUARTZ_ORE.get()) {
		         return MathHelper.nextInt(rand, 2, 5);
	      } else if (this == BlockInit.SAPPHIRE_ORE.get()) {
		         return MathHelper.nextInt(rand, 3, 7);
	      } else if (this == BlockInit.SHADOW_ORE.get()) {
		         return MathHelper.nextInt(rand, 4, 10);
	      } else if (this == BlockInit.SILVER_ORE.get()) {
		         return MathHelper.nextInt(rand, 2, 6);
	      } else if (this == BlockInit.SUNSTONE_ORE.get()) {
		         return MathHelper.nextInt(rand, 4, 9);
	      } else if (this == BlockInit.TURQUOISE_ORE.get()) {
		         return MathHelper.nextInt(rand, 3, 7);
	      } else { return this == BlockInit.DIAMOND_LANTERN.get() ? MathHelper.nextInt(rand, 0, 3) : 0;
	      }
	   }
  
	 @Override
	   public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
			return silktouch == 0 ? this.getExperience(RANDOM) : 0;
	   }

	
	}

