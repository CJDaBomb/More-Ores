package com.cjdabomb.moreores.world;

import com.cjdabomb.moreores.core.init.BlockInit;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {

	public static void generateOres(final BiomeLoadingEvent event) {
		if(!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
		   generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
				   BlockInit.ALUMINIUM_ORE.get().defaultBlockState(), 9, 20, 64, 20);
		   generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
				   BlockInit.COBALT_ORE.get().defaultBlockState(), 9, 5, 30, 12);
		   generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
				   BlockInit.JASPER_ORE.get().defaultBlockState(), 9, 5, 64, 20);
		   generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
				   BlockInit.SAPPHIRE_ORE.get().defaultBlockState(), 9, 5, 32, 12);
		   generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
				   BlockInit.SILVER_ORE.get().defaultBlockState(), 8, 5, 32, 10);
		   generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
				   BlockInit.SHADOW_ORE.get().defaultBlockState(), 5, 5, 10, 1);
		   generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
				   BlockInit.TURQUOISE_ORE.get().defaultBlockState(), 9, 5, 32, 1);
		   
		}
		else if(event.getCategory().equals(Biome.Category.NETHER)) {
			generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, 
					   BlockInit.ROSE_QUARTZ_ORE.get().defaultBlockState(), 6, 5, 64, 10);
			generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NETHER_ORE_REPLACEABLES, 
					   BlockInit.SUNSTONE_ORE.get().defaultBlockState(), 10, 5, 128, 16);
			
		}
		else if(event.getCategory().equals(Biome.Category.DESERT)) {
			generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
					BlockInit.ALEXANDRITE_ORE.get().defaultBlockState(), 5, 5, 15, 1);
		}
		
	}
	
	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerBlockType, BlockState state, int veinSize, int minHeight, int maxHeight, int veinsPerChuck) {
		settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.configured(new OreFeatureConfig(fillerBlockType, state, veinSize))
		.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(veinSize, minHeight, maxHeight))).squared().count(veinsPerChuck));
	}
}
