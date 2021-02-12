package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			MoreOres.MOD_ID);

	public static final RegistryObject<Block> ALEXANDRITE_BLOCK = BLOCKS.register("alexandrite_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> ALUMINIUM_BLOCK = BLOCKS.register("aluminium_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> COBALT_BLOCK = BLOCKS.register("cobalt_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(4).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> ELECTRUM_BLOCK = BLOCKS.register("electrum_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(4).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> JASPER_BLOCK = BLOCKS.register("jasper_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> ROSE_GOLD_BLOCK = BLOCKS.register("rose_gold_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> ROSE_QUARTZ_BLOCK = BLOCKS.register("rose_quartz_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(0.8F, 0.8F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> SAPPHIRE_BLOCK = BLOCKS.register("sapphire_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> SHADOW_BLOCK = BLOCKS.register("shadow_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(5).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("silver_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> SUNSTONE_BLOCK = BLOCKS.register("sunstone_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));
	public static final RegistryObject<Block> TURQUOISE_BLOCK = BLOCKS.register("turquoise_block",
			() -> new Block(AbstractBlock.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.METAL).setRequiresTool()));

	public static final RegistryObject<Block> DIAMOND_LANTERN = BLOCKS.register("diamond_lantern", 	
			() -> new Block(AbstractBlock.Properties.create(Material.GLASS).hardnessAndResistance(0.3F, 0.3F)
					.sound(SoundType.GLASS).setRequiresTool()));

	public static final RegistryObject<Block> ALEXANDRITE_ORE = BLOCKS.register("alexandrite_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> ALUMINIUM_ORE = BLOCKS.register("aluminium_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(3).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> COBALT_ORE = BLOCKS.register("cobalt_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(4).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> JASPER_ORE = BLOCKS.register("jasper_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> ROSE_QUARTZ_ORE = BLOCKS.register("rose_quartz_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> SAPPHIRE_ORE = BLOCKS.register("sapphire_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> SHADOW_ORE = BLOCKS.register("shadow_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(5).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> SILVER_ORE = BLOCKS.register("silver_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> SUNSTONE_ORE = BLOCKS.register("sunstone_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));
	public static final RegistryObject<Block> TURQUOISE_ORE = BLOCKS.register("turquoise_ore",
			() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F)
					.harvestTool(ToolType.PICKAXE).harvestLevel(2).sound(SoundType.STONE).setRequiresTool()));

}
