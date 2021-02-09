package com.cjdabomb.moreores;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cjdabomb.moreores.core.init.BlockInit;
import com.cjdabomb.moreores.core.init.ItemInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(MoreOres.MOD_ID)
public class MoreOres
{
    
    public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "moreores";

    public MoreOres() {
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }
}
