package com.cjdabomb.moreores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cjdabomb.moreores.core.init.BlockInit;
import com.cjdabomb.moreores.core.init.ContainerTypeInit;
import com.cjdabomb.moreores.core.init.EntityTypeInit;
import com.cjdabomb.moreores.core.init.ItemInit;
import com.cjdabomb.moreores.core.init.TileEntityTypeInit;
import com.cjdabomb.moreores.villager.PointOfInterestTypes;
import com.cjdabomb.moreores.villager.VillagerUtil;
import com.cjdabomb.moreores.world.OreGeneration;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreOres.MOD_ID)
public class MoreOres {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "moreores";
	public static final ItemGroup ITEMS = new MoreOresItemGroup("more_ores_items");
	public static final ItemGroup BLOCKS = new MoreOresBlockGroup("more_ores_blocks");
	public static final ItemGroup TOOLS = new MoreOresToolGroup("more_ores_tools");
	

	public MoreOres() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);

		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		EntityTypeInit.ENTITIES.register(bus);
		TileEntityTypeInit.TILE_ENTITIES.register(bus);
		ContainerTypeInit.CONTAINERS.register(bus);

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);

		MinecraftForge.EVENT_BUS.register(this);
		
	}

	private void setup(final FMLCommonSetupEvent event) {

		VillagerUtil.fixPOITypeBlockStates(PointOfInterestTypes.JEWELER);

	}


	public static class MoreOresItemGroup extends ItemGroup {

		public MoreOresItemGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack createIcon() {
			return ItemInit.JASPER.get().getDefaultInstance();
		}

	}

	public static class MoreOresBlockGroup extends ItemGroup {

		public MoreOresBlockGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack createIcon() {
			return ItemInit.JASPER_BLOCK.get().getDefaultInstance();
		}

	}

	public static class MoreOresToolGroup extends ItemGroup {

		public MoreOresToolGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack createIcon() {
			return ItemInit.ROSE_QUARTZ.get().getDefaultInstance();
		}

	}
}
