package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.items.*;
import com.cjdabomb.moreores.core.enums.ModItemTier;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreOres.MOD_ID);

	// Items

	public static final RegistryObject<Item> ROSE_QUARTZ = ITEMS.register("rose_quartz",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> JASPER = ITEMS.register("jasper",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> ROSE_GOLD_INGOT = ITEMS.register("rose_gold_ingot",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> SHADOW_INGOT = ITEMS.register("shadow_ingot",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> SUNSTONE = ITEMS.register("sunstone",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> TURQUOISE = ITEMS.register("turquoise",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<SilverIngotItem> SILVER_INGOT = ITEMS.register("silver_ingot",
			() -> new SilverIngotItem(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<ElectrumIngotItem> ELECTRUM_INGOT = ITEMS.register("electrum_ingot",
			() -> new ElectrumIngotItem(new Item.Properties().tab(MoreOres.ITEMS)));
	public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS)));
	

	
	
	public static final RegistryObject<VoidElytraItem> VOID_ELYTRA = ITEMS.register("void_elytra", 
			() -> new VoidElytraItem(new Item.Properties()
					.tab(ItemGroup.TAB_TRANSPORTATION).durability(556)));
	
	
	
	
	
	// Food

	public static final RegistryObject<Item> BLUEBERRIES = ITEMS.register("blueberry", () -> new Item(new Item.Properties().tab(MoreOres.ITEMS)
			.food(new Food.Builder().nutrition(4).saturationMod(0.5f).build())));
	public static final RegistryObject<Item> CHERRIES = ITEMS.register("cherry", () -> new Item(new Item.Properties().tab(MoreOres.ITEMS)
			.food(new Food.Builder().nutrition(4).saturationMod(0.5f).build())));
	public static final RegistryObject<Item> GRAPES = ITEMS.register("grape", () -> new Item(new Item.Properties().tab(MoreOres.ITEMS)
			.food(new Food.Builder().nutrition(4).saturationMod(0.5f).build())));
	public static final RegistryObject<Item> LEMON = ITEMS.register("lemon", () -> new Item(new Item.Properties().tab(MoreOres.ITEMS)
			.food(new Food.Builder().nutrition(4).saturationMod(0.5f).build())));
	
	
	
	
	
	// Apples
	
	public static final RegistryObject<Item> ALEXANDRITE_APPLE = ITEMS.register("alexandrite_apple", 
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.INVISIBILITY, 9600, 1), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 3600, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.JUMP, 9600, 2), 1.0f).effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 9600, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 9600, 2), 1.0f).nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	public static final RegistryObject<Item> ALUMINIUM_APPLE = ITEMS.register("aluminium_apple", 
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0f).effect(() -> new EffectInstance(Effects.CONDUIT_POWER, 16000, 2), 1.0f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	public static final RegistryObject<Item> COAL_APPLE = ITEMS.register("coal_apple", 
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.DIG_SPEED, 2400, 2), 1.0f).effect(() -> new EffectInstance(Effects.NIGHT_VISION, 9600, 1), 1.0f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	
	public static final RegistryObject<DiamondAppleItem> DIAMOND_APPLE = ITEMS.register("diamond_apple", 
			() -> new DiamondAppleItem(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.REGENERATION, 800, 2), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 3600, 2), 1.0f).effect(() -> new EffectInstance(Effects.DAMAGE_RESISTANCE, 2400, 2), 1.0f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	
	public static final RegistryObject<DiamondChorusFruitItem> DIAMOND_CHORUS_FRUIT = ITEMS.register("diamond_chorus_fruit", 
			() -> new DiamondChorusFruitItem(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.REGENERATION, 200, 1), 0.5f).effect(() -> new EffectInstance(Effects.ABSORPTION, 1200, 2), 0.5f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	public static final RegistryObject<Item> EMERALD_APPLE = ITEMS.register("emerald_apple", 
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 100000, 1), 1.0f).effect(() -> new EffectInstance(Effects.HEALTH_BOOST, 9600, 2), 1.0f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	public static final RegistryObject<Item> IRON_APPLE = ITEMS.register("iron_apple", 
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 9600, 1), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 1200, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 4800, 2), 1.0f).nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	
	public static final RegistryObject<LapisAppleItem> LAPIS_APPLE = ITEMS.register("lapis_apple", 
			() -> new LapisAppleItem(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 4800, 1), 1.0f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	
	public static final RegistryObject<RedstoneAppleItem> REDSTONE_APPLE = ITEMS.register("redstone_apple", 
			() -> new RedstoneAppleItem(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.NIGHT_VISION, 7200, 1), 1.0f).nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	
	public static final RegistryObject<ShadowAppleItem> SHADOW_APPLE = ITEMS.register("shadow_apple", 
			() -> new ShadowAppleItem(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.REGENERATION, 1200, 1), 1.0f).effect(() -> new EffectInstance(Effects.ABSORPTION, 12000, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 9600, 1), 1.0f).effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 9600, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.INVISIBILITY, 19200, 1), 1.0f).effect(() -> new EffectInstance(Effects.DAMAGE_BOOST, 12000, 2), 1.0f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).fast().build())));
	
	public static final RegistryObject<Item> TURQUOISE_APPLE = ITEMS.register("turquoise_apple", 
			() -> new Item(new Item.Properties().tab(MoreOres.ITEMS).food(new Food.Builder()
					.effect(() -> new EffectInstance(Effects.ABSORPTION, 2400, 2), 1.0f).effect(() -> new EffectInstance(Effects.JUMP, 4800, 2), 1.0f)
					.effect(() -> new EffectInstance(Effects.MOVEMENT_SPEED, 4800, 2), 1.0f).effect(() -> new EffectInstance(Effects.CONDUIT_POWER, 9600, 2), 1.0f)
					.nutrition(4).alwaysEat().saturationMod(1.2f).build())));
	
	
	
	
	// Totems
	
	public static final RegistryObject<DiamondTotem> DIAMOND_TOTEM = ITEMS.register("diamond_totem", 
			() -> new DiamondTotem(new Item.Properties().tab(MoreOres.ITEMS).stacksTo(1)));
	public static final RegistryObject<EmeraldTotem> EMERALD_TOTEM = ITEMS.register("emerald_totem", 
			() -> new EmeraldTotem(new Item.Properties().tab(MoreOres.ITEMS).stacksTo(1)));
	
	
	
	
	
	// Tools
	
	public static final RegistryObject<Item> ALEXANDRITE_AXE = ITEMS.register("alexandrite_axe", 
			() -> new AxeItem(ModItemTier.ALEXANDRITE, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ALEXANDRITE_PICKAXE = ITEMS.register("alexandrite_pickaxe", 
			() -> new PickaxeItem(ModItemTier.ALEXANDRITE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ALEXANDRITE_SHOVEL = ITEMS.register("alexandrite_shovel", 
			() -> new ShovelItem(ModItemTier.ALEXANDRITE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ALEXANDRITE_SWORD = ITEMS.register("alexandrite_sword", 
			() -> new SwordItem(ModItemTier.ALEXANDRITE, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));

	public static final RegistryObject<Item> ALUMINIUM_AXE = ITEMS.register("aluminium_axe", 
			() -> new AxeItem(ModItemTier.ALUMINIUM, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ALUMINIUM_PICKAXE = ITEMS.register("aluminium_pickaxe", 
			() -> new PickaxeItem(ModItemTier.ALUMINIUM, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ALUMINIUM_SHOVEL = ITEMS.register("aluminium_shovel", 
			() -> new ShovelItem(ModItemTier.ALUMINIUM, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ALUMINIUM_SWORD = ITEMS.register("aluminium_sword", 
			() -> new SwordItem(ModItemTier.ALUMINIUM, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> COBALT_AXE = ITEMS.register("cobalt_axe", 
			() -> new AxeItem(ModItemTier.COBALT, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> COBALT_PICKAXE = ITEMS.register("cobalt_pickaxe", 
			() -> new PickaxeItem(ModItemTier.COBALT, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> COBALT_SHOVEL = ITEMS.register("cobalt_shovel", 
			() -> new ShovelItem(ModItemTier.COBALT, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> COBALT_SWORD = ITEMS.register("cobalt_sword", 
			() -> new SwordItem(ModItemTier.COBALT, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> ELECTRUM_AXE = ITEMS.register("electrum_axe", 
			() -> new AxeItem(ModItemTier.ELECTRUM, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ELECTRUM_PICKAXE = ITEMS.register("electrum_pickaxe", 
			() -> new PickaxeItem(ModItemTier.ELECTRUM, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ELECTRUM_SHOVEL = ITEMS.register("electrum_shovel", 
			() -> new ShovelItem(ModItemTier.ELECTRUM, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ELECTRUM_SWORD = ITEMS.register("electrum_sword", 
			() -> new SwordItem(ModItemTier.ELECTRUM, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> JASPER_AXE = ITEMS.register("jasper_axe", 
			() -> new AxeItem(ModItemTier.JASPER, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> JASPER_PICKAXE = ITEMS.register("jasper_pickaxe", 
			() -> new PickaxeItem(ModItemTier.JASPER, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> JASPER_SHOVEL = ITEMS.register("jasper_shovel", 
			() -> new ShovelItem(ModItemTier.JASPER, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> JASPER_SWORD = ITEMS.register("jasper_sword", 
			() -> new SwordItem(ModItemTier.JASPER, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> ROSE_GOLD_AXE = ITEMS.register("rose_gold_axe", 
			() -> new AxeItem(ModItemTier.ROSE_GOLD, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ROSE_GOLD_PICKAXE = ITEMS.register("rose_gold_pickaxe", 
			() -> new PickaxeItem(ModItemTier.ALEXANDRITE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ROSE_GOLD_SHOVEL = ITEMS.register("rose_gold_shovel", 
			() -> new ShovelItem(ModItemTier.ROSE_GOLD, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> ROSE_GOLD_SWORD = ITEMS.register("rose_gold_sword", 
			() -> new SwordItem(ModItemTier.ROSE_GOLD, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe", 
			() -> new AxeItem(ModItemTier.SAPPHIRE, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe", 
			() -> new PickaxeItem(ModItemTier.SAPPHIRE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel", 
			() -> new ShovelItem(ModItemTier.SAPPHIRE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword", 
			() -> new SwordItem(ModItemTier.SAPPHIRE, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<ShadowAxeItem> SHADOW_AXE = ITEMS.register("shadow_axe",
			() -> new ShadowAxeItem(ModItemTier.SHADOW, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<ShadowPickaxeItem> SHADOW_PICKAXE = ITEMS.register("shadow_pickaxe",
			() -> new ShadowPickaxeItem(ModItemTier.SHADOW, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<ShadowShovelItem> SHADOW_SHOVEL = ITEMS.register("shadow_shovel",
			() -> new ShadowShovelItem(ModItemTier.SHADOW, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SHADOW_SWORD = ITEMS.register("shadow_sword", 
			() -> new SwordItem(ModItemTier.SHADOW, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> SILVER_AXE = ITEMS.register("silver_axe", 
			() -> new AxeItem(ModItemTier.SILVER, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SILVER_PICKAXE = ITEMS.register("silver_pickaxe", 
			() -> new PickaxeItem(ModItemTier.SILVER, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SILVER_SHOVEL = ITEMS.register("silver_shovel", 
			() -> new ShovelItem(ModItemTier.SILVER, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SILVER_SWORD = ITEMS.register("silver_sword", 
			() -> new SwordItem(ModItemTier.SILVER, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> SUNSTONE_AXE = ITEMS.register("sunstone_axe", 
			() -> new AxeItem(ModItemTier.SUNSTONE, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SUNSTONE_PICKAXE = ITEMS.register("sunstone_pickaxe", 
			() -> new PickaxeItem(ModItemTier.SUNSTONE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SUNSTONE_SHOVEL = ITEMS.register("sunstone_shovel", 
			() -> new ShovelItem(ModItemTier.SUNSTONE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> SUNSTONE_SWORD = ITEMS.register("sunstone_sword", 
			() -> new SwordItem(ModItemTier.SUNSTONE, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	public static final RegistryObject<Item> TURQUOISE_AXE = ITEMS.register("turquoise_axe", 
			() -> new AxeItem(ModItemTier.TURQUOISE, 5.0F, -3.1F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> TURQUOISE_PICKAXE = ITEMS.register("turquoise_pickaxe", 
			() -> new PickaxeItem(ModItemTier.TURQUOISE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> TURQUOISE_SHOVEL = ITEMS.register("turquoise_shovel", 
			() -> new ShovelItem(ModItemTier.ALEXANDRITE, 4, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	public static final RegistryObject<Item> TURQUOISE_SWORD = ITEMS.register("turquoise_sword", 
			() -> new SwordItem(ModItemTier.TURQUOISE, 6, -1.4F, (new Item.Properties()).tab(MoreOres.TOOLS)));
	
	
	
	
	
	
	// Block Items

	public static final RegistryObject<BlockItem> ALEXANDRITE_BLOCK = ITEMS.register("alexandrite_block",
			() -> new BlockItem(BlockInit.ALEXANDRITE_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ALUMINIUM_BLOCK = ITEMS.register("aluminium_block",
			() -> new BlockItem(BlockInit.ALUMINIUM_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> COBALT_BLOCK = ITEMS.register("cobalt_block",
			() -> new BlockItem(BlockInit.COBALT_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ELECTRUM_BLOCK = ITEMS.register("electrum_block",
			() -> new BlockItem(BlockInit.ELECTRUM_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> JASPER_BLOCK = ITEMS.register("jasper_block",
			() -> new BlockItem(BlockInit.JASPER_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ROSE_GOLD_BLOCK = ITEMS.register("rose_gold_block",
			() -> new BlockItem(BlockInit.ROSE_GOLD_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ROSE_QUARTZ_BLOCK = ITEMS.register("rose_quartz_block",
			() -> new BlockItem(BlockInit.ROSE_QUARTZ_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SAPPHIRE_BLOCK = ITEMS.register("sapphire_block",
			() -> new BlockItem(BlockInit.SAPPHIRE_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SHADOW_BLOCK = ITEMS.register("shadow_block",
			() -> new BlockItem(BlockInit.SHADOW_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SILVER_BLOCK = ITEMS.register("silver_block",
			() -> new BlockItem(BlockInit.SILVER_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SUNSTONE_BLOCK = ITEMS.register("sunstone_block",
			() -> new BlockItem(BlockInit.SUNSTONE_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> TURQUOISE_BLOCK = ITEMS.register("turquoise_block",
			() -> new BlockItem(BlockInit.TURQUOISE_BLOCK.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	
	public static final RegistryObject<BlockItem> ALLOY_SYNTHESIZER = ITEMS.register("alloy_synthesizer", 
			() -> new BlockItem(BlockInit.ALLOY_SYNTHESIZER.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> JEWELER_BARREL = ITEMS.register("jeweler_barrel", 
			() -> new BlockItem(BlockInit.JEWELER_BARREL.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> DIAMOND_LANTERN = ITEMS.register("diamond_lantern",
			() -> new BlockItem(BlockInit.DIAMOND_LANTERN.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> DARK_MATTER = ITEMS.register("dark_matter", 
			() -> new BlockItem(BlockInit.DARK_MATTER.get(), new Item.Properties().tab(MoreOres.BLOCKS)));

	public static final RegistryObject<BlockItem> ALEXANDRITE_ORE = ITEMS.register("alexandrite_ore",
			() -> new BlockItem(BlockInit.ALEXANDRITE_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ALUMINIUM_ORE = ITEMS.register("aluminium_ore",
			() -> new BlockItem(BlockInit.ALUMINIUM_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> COBALT_ORE = ITEMS.register("cobalt_ore",
			() -> new BlockItem(BlockInit.COBALT_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> JASPER_ORE = ITEMS.register("jasper_ore",
			() -> new BlockItem(BlockInit.JASPER_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> ROSE_QUARTZ_ORE = ITEMS.register("rose_quartz_ore",
			() -> new BlockItem(BlockInit.ROSE_QUARTZ_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SAPPHIRE_BRE = ITEMS.register("sapphire_ore",
			() -> new BlockItem(BlockInit.SAPPHIRE_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SHADOW_ORE = ITEMS.register("shadow_ore",
			() -> new BlockItem(BlockInit.SHADOW_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS).rarity(Rarity.RARE)));
	public static final RegistryObject<BlockItem> SILVER_ORE = ITEMS.register("silver_ore",
			() -> new BlockItem(BlockInit.SILVER_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> SUNSTONE_ORE = ITEMS.register("sunstone_ore",
			() -> new BlockItem(BlockInit.SUNSTONE_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));
	public static final RegistryObject<BlockItem> TURQUOISE_ORE = ITEMS.register("turquoise_ore",
			() -> new BlockItem(BlockInit.TURQUOISE_ORE.get(), new Item.Properties().tab(MoreOres.BLOCKS)));

	}

