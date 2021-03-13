package com.cjdabomb.moreores.villager;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.core.init.BlockInit;

import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.Objects;

@EventBusSubscriber(modid = MoreOres.MOD_ID, bus = Bus.MOD)
@ObjectHolder(MoreOres.MOD_ID)
public class PointOfInterestTypes {
	
	public static final PointOfInterestType JEWELER = null;
	
	
	@SubscribeEvent
    public static void registerPointOfInterestTypes(Register<PointOfInterestType> event)
    {	
        IForgeRegistry<PointOfInterestType> registry = event.getRegistry();
        
        registry.register(Objects.requireNonNull(VillagerUtil.pointOfInterestType("jeweler", VillagerUtil.getAllStates(BlockInit.JEWELER_BARREL.get()), 1, 1))
        		.setRegistryName(MoreOres.MOD_ID, "jeweler"));
       
    }
}
