package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreOres.MOD_ID);

	// Items

	public static final RegistryObject<Item> ROSE_QUARTZ = ITEMS.register("rose_quartz",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> JASPER = ITEMS.register("jasper",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> AlEXANDRITE = ITEMS.register("alexandrite",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminium_ingot",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> COBALT_INGOT = ITEMS.register("cobalt_ingot",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> ROSE_GOLD_INGOT = ITEMS.register("rose_gold_ingot",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> SHADOW_INGOT = ITEMS.register("shadow_ingot",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> SUNSTONE = ITEMS.register("sunstone",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> TURQUOISE = ITEMS.register("turquoise",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> ELECTRUM_INGOT = ITEMS.register("electrum_ingot",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));

	// Block Items

	public static final RegistryObject<BlockItem> ALEXANDRITE_BLOCK = ITEMS.register("alexandrite_block",
			() -> new BlockItem(BlockInit.ALEXANDRITE_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ALUMINIUM_BLOCK = ITEMS.register("aluminium_block",
			() -> new BlockItem(BlockInit.ALUMINIUM_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> COBALT_BLOCK = ITEMS.register("cobalt_block",
			() -> new BlockItem(BlockInit.COBALT_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ELECTRUM_BLOCK = ITEMS.register("electrum_block",
			() -> new BlockItem(BlockInit.ELECTRUM_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> JASPER_BLOCK = ITEMS.register("jasper_block",
			() -> new BlockItem(BlockInit.JASPER_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ROSE_GOLD_BLOCK = ITEMS.register("rose_gold_block",
			() -> new BlockItem(BlockInit.ROSE_GOLD_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ROSE_QUARTZ_BLOCK = ITEMS.register("rose_quartz_block",
			() -> new BlockItem(BlockInit.ROSE_QUARTZ_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SAPPHIRE_BLOCK = ITEMS.register("sapphire_block",
			() -> new BlockItem(BlockInit.SAPPHIRE_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SHADOW_BLOCK = ITEMS.register("shadow_block",
			() -> new BlockItem(BlockInit.SHADOW_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SILVER_BLOCK = ITEMS.register("silver_block",
			() -> new BlockItem(BlockInit.SILVER_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SUNSTONE_BLOCK = ITEMS.register("sunstone_block",
			() -> new BlockItem(BlockInit.SUNSTONE_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> TURQUOISE_BLOCK = ITEMS.register("turquoise_block",
			() -> new BlockItem(BlockInit.TURQUOISE_BLOCK.get(), new Item.Properties().group(ItemGroup.MATERIALS)));

	public static final RegistryObject<BlockItem> DIAMOND_LANTERN = ITEMS.register("diamond_lantern",
			() -> new BlockItem(BlockInit.DIAMOND_LANTERN.get(), new Item.Properties().group(ItemGroup.MATERIALS)));

	public static final RegistryObject<BlockItem> ALEXANDRITE_ORE = ITEMS.register("alexandrite_ore",
			() -> new BlockItem(BlockInit.ALEXANDRITE_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ALUMINIUM_ORE = ITEMS.register("aluminium_ore",
			() -> new BlockItem(BlockInit.ALUMINIUM_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> COBALT_ORE = ITEMS.register("cobalt_ore",
			() -> new BlockItem(BlockInit.COBALT_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ELECTRUM_ORE = ITEMS.register("electrum_ore",
			() -> new BlockItem(BlockInit.ELECTRUM_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> JASPER_ORE = ITEMS.register("jasper_ore",
			() -> new BlockItem(BlockInit.JASPER_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ROSE_GOLD_ORE = ITEMS.register("rose_gold_ore",
			() -> new BlockItem(BlockInit.ROSE_GOLD_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> ROSE_QUARTZ_ORE = ITEMS.register("rose_quartz_ore",
			() -> new BlockItem(BlockInit.ROSE_QUARTZ_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SAPPHIRE_BRE = ITEMS.register("sapphire_ore",
			() -> new BlockItem(BlockInit.SAPPHIRE_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SHADOW_ORE = ITEMS.register("shadow_ore",
			() -> new BlockItem(BlockInit.SHADOW_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SILVER_ORE = ITEMS.register("silver_ore",
			() -> new BlockItem(BlockInit.SILVER_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> SUNSTONE_ORE = ITEMS.register("sunstone_ore",
			() -> new BlockItem(BlockInit.SUNSTONE_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));
	public static final RegistryObject<BlockItem> TURQUOISE_ORE = ITEMS.register("turquoise_ore",
			() -> new BlockItem(BlockInit.TURQUOISE_ORE.get(), new Item.Properties().group(ItemGroup.MATERIALS)));

}
