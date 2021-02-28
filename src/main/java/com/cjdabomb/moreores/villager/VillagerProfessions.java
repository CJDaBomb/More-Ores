package com.cjdabomb.moreores.villager;

import com.cjdabomb.moreores.MoreOres;
import com.google.common.collect.ImmutableSet;

import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;


@EventBusSubscriber(modid = MoreOres.MOD_ID, bus = Bus.MOD)
@ObjectHolder(MoreOres.MOD_ID)
public class VillagerProfessions
{
    public static final VillagerProfession JEWELER = null;
    
    
    @SubscribeEvent
    public static void registerVillagerProfessions(Register<VillagerProfession> event)
    {
        IForgeRegistry<VillagerProfession> registry = event.getRegistry();
        
        registry.register(VillagerUtil.villagerProfession("jeweler", PointOfInterestTypes.JEWELER, ImmutableSet.of(), ImmutableSet.of(), null).setRegistryName(MoreOres.MOD_ID, "jeweler"));
        
    }
}

	
