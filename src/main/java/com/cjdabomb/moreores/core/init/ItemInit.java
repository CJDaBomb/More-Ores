package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.items.DiamondAppleItem;
import com.cjdabomb.moreores.common.items.DiamondChorusFruitItem;
import com.cjdabomb.moreores.common.items.LapisAppleItem;
import com.cjdabomb.moreores.common.items.RedstoneAppleItem;
import com.cjdabomb.moreores.common.items.ShadowAppleItem;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreOres.MOD_ID);

	// Items

	public static final RegistryObject<Item> ROSE_QUARTZ = ITEMS.register("rose_quartz",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> JASPER = ITEMS.register("jasper",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> ROSE_GOLD_INGOT = ITEMS.register("rose_gold_ingot",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> SHADOW_INGOT = ITEMS.register("shadow_ingot",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> SUNSTONE = ITEMS.register("sunstone",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> TURQUOISE = ITEMS.register("turquoise",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS)));
	
	
	public static final RegistryObject<Item> BLUEBERRIES = ITEMS.register("blueberry", () -> new Item(new Item.Properties().group(MoreOres.ITEMS)
			.food(new Food.Builder().hunger(4).saturation(0.5f).build())));
	public static final RegistryObject<Item> CHERRIES = ITEMS.register("cherry", () -> new Item(new Item.Properties().group(MoreOres.ITEMS)
			.food(new Food.Builder().hunger(4).saturation(0.5f).build())));
	public static final RegistryObject<Item> GRAPES = ITEMS.register("grape", () -> new Item(new Item.Properties().group(MoreOres.ITEMS)
			.food(new Food.Builder().hunger(4).saturation(0.5f).build())));
	public static final RegistryObject<Item> LEMON = ITEMS.register("lemon", () -> new Item(new Item.Properties().group(MoreOres.ITEMS)
			.food(new Food.Builder().hunger(4).saturation(0.5f).build())));
	
	public static final RegistryObject<Item> ALEXANDRITE_APPLE = ITEMS.register("alexandrite_apple", 
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.INVISIBILITY, 9600, 1), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 3600, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.JUMP_BOOST, 9600, 2), 1.0f).effect(() -> new EffectInstance(Effects.STRENGTH, 9600, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.SPEED, 9600, 2), 1.0f).hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	public static final RegistryObject<Item> ALUMINIUM_APPLE = ITEMS.register("aluminium_apple", 
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0f).effect(() -> new EffectInstance(Effects.CONDUIT_POWER, 16000, 2), 1.0f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	public static final RegistryObject<Item> COAL_APPLE = ITEMS.register("coal_apple", 
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.HASTE, 2400, 2), 1.0f).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 9600, 1), 1.0f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	
	public static final RegistryObject<DiamondAppleItem> DIAMOND_APPLE = ITEMS.register("diamond_apple", 
			() -> new DiamondAppleItem(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.REGENERATION, 800, 2), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.SPEED, 3600, 2), 1.0f).effect(() -> new EffectInstance(Effects.RESISTANCE, 2400, 2), 1.0f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	
	public static final RegistryObject<DiamondChorusFruitItem> DIAMOND_CHORUS_FRUIT = ITEMS.register("diamond_chorus_fruit", 
			() -> new DiamondChorusFruitItem(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.REGENERATION, 200, 1), 0.5f).effect(() -> new EffectInstance(Effects.ABSORPTION, 1200, 2), 0.5f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	public static final RegistryObject<Item> EMERALD_APPLE = ITEMS.register("emerald_apple", 
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 100000, 1), 1.0f).effect(() -> new EffectInstance(Effects.HEALTH_BOOST, 9600, 2), 1.0f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	public static final RegistryObject<Item> IRON_APPLE = ITEMS.register("iron_apple", 
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 9600, 1), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 1200, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.STRENGTH, 4800, 2), 1.0f).hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	
	public static final RegistryObject<LapisAppleItem> LAPIS_APPLE = ITEMS.register("lapis_apple", 
			() -> new LapisAppleItem(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 4800, 1), 1.0f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	
	public static final RegistryObject<RedstoneAppleItem> REDSTONE_APPLE = ITEMS.register("redstone_apple", 
			() -> new RedstoneAppleItem(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.NIGHT_VISION, 7200, 1), 1.0f).hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	
	public static final RegistryObject<ShadowAppleItem> SHADOW_APPLE = ITEMS.register("shadow_apple", 
			() -> new ShadowAppleItem(new Item.Properties().group(MoreOres.ITEMS).rarity(Rarity.RARE).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.REGENERATION, 1200, 1), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 12000, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 9600, 1), 1.0f).effect(() -> new EffectInstance(Effects.SPEED, 9600, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.INVISIBILITY, 19200, 1), 1.0f).effect(() -> new EffectInstance(Effects.STRENGTH, 12000, 2), 1.0f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).fastToEat().build())));
	
	public static final RegistryObject<Item> TURQUOISE_APPLE = ITEMS.register("turquoise_apple", 
			() -> new Item(new Item.Properties().group(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0f).effect(() -> new EffectInstance(Effects.JUMP_BOOST, 4800, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.SPEED, 4800, 2), 1.0f).effect(() -> new EffectInstance(Effects.CONDUIT_POWER, 9600, 2), 1.0f)
					.hunger(4).setAlwaysEdible().saturation(1.2f).build())));
	
	
	

	// Block Items

	public static final RegistryObject<BlockItem> ALEXANDRITE_BLOCK = ITEMS.register("alexandrite_block",
			() -> new BlockItem(BlockInit.ALEXANDRITE_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ALUMINIUM_BLOCK = ITEMS.register("aluminium_block",
			() -> new BlockItem(BlockInit.ALUMINIUM_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> COBALT_BLOCK = ITEMS.register("cobalt_block",
			() -> new BlockItem(BlockInit.COBALT_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ELECTRUM_BLOCK = ITEMS.register("electrum_block",
			() -> new BlockItem(BlockInit.ELECTRUM_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> JASPER_BLOCK = ITEMS.register("jasper_block",
			() -> new BlockItem(BlockInit.JASPER_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ROSE_GOLD_BLOCK = ITEMS.register("rose_gold_block",
			() -> new BlockItem(BlockInit.ROSE_GOLD_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ROSE_QUARTZ_BLOCK = ITEMS.register("rose_quartz_block",
			() -> new BlockItem(BlockInit.ROSE_QUARTZ_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SAPPHIRE_BLOCK = ITEMS.register("sapphire_block",
			() -> new BlockItem(BlockInit.SAPPHIRE_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SHADOW_BLOCK = ITEMS.register("shadow_block",
			() -> new BlockItem(BlockInit.SHADOW_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SILVER_BLOCK = ITEMS.register("silver_block",
			() -> new BlockItem(BlockInit.SILVER_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SUNSTONE_BLOCK = ITEMS.register("sunstone_block",
			() -> new BlockItem(BlockInit.SUNSTONE_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> TURQUOISE_BLOCK = ITEMS.register("turquoise_block",
			() -> new BlockItem(BlockInit.TURQUOISE_BLOCK.get(), new Item.Properties().group(MoreOres.BLOCKS)));

	public static final RegistryObject<BlockItem> DIAMOND_LANTERN = ITEMS.register("diamond_lantern",
			() -> new BlockItem(BlockInit.DIAMOND_LANTERN.get(), new Item.Properties().group(MoreOres.BLOCKS)));

	public static final RegistryObject<BlockItem> ALEXANDRITE_ORE = ITEMS.register("alexandrite_ore",
			() -> new BlockItem(BlockInit.ALEXANDRITE_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ALUMINIUM_ORE = ITEMS.register("aluminium_ore",
			() -> new BlockItem(BlockInit.ALUMINIUM_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> COBALT_ORE = ITEMS.register("cobalt_ore",
			() -> new BlockItem(BlockInit.COBALT_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> JASPER_ORE = ITEMS.register("jasper_ore",
			() -> new BlockItem(BlockInit.JASPER_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ROSE_QUARTZ_ORE = ITEMS.register("rose_quartz_ore",
			() -> new BlockItem(BlockInit.ROSE_QUARTZ_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SAPPHIRE_BRE = ITEMS.register("sapphire_ore",
			() -> new BlockItem(BlockInit.SAPPHIRE_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SHADOW_ORE = ITEMS.register("shadow_ore",
			() -> new BlockItem(BlockInit.SHADOW_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS).rarity(Rarity.RARE)));
	public static final RegistryObject<BlockItem> SILVER_ORE = ITEMS.register("silver_ore",
			() -> new BlockItem(BlockInit.SILVER_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SUNSTONE_ORE = ITEMS.register("sunstone_ore",
			() -> new BlockItem(BlockInit.SUNSTONE_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> TURQUOISE_ORE = ITEMS.register("turquoise_ore",
			() -> new BlockItem(BlockInit.TURQUOISE_ORE.get(), new Item.Properties().group(MoreOres.BLOCKS)));

}
