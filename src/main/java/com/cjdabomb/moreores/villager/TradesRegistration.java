package com.cjdabomb.moreores.villager;

import com.cjdabomb.moreores.MoreOres;

import net.minecraft.item.Items;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreOres.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class TradesRegistration {
	
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event){

        if (event.getType() == VillagerProfessions.JEWELER)
        {
        	event.getTrades().get(1).add((new RandomTradeBuilder(16, 2, 0.05F).setRoseQuartzPrice(15,22).setForSale(Items.COAL_ORE, 1, 2).build()));
        	event.getTrades().get(1).add((new RandomTradeBuilder(16, 3, 0.05F).setPrice(Items.GOLD_INGOT, 4,8).setPrice2(Items.APPLE, 2,2).setForSale(Items.GOLDEN_APPLE, 1, 2).build()));
        	event.getTrades().get(1).add((new RandomTradeBuilder(16, 2, 0.05F).setPrice(Items.WITHER_SKELETON_SKULL, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.NETHER_STAR, 1, 2).build()));
        	
        	event.getTrades().get(2).add((new RandomTradeBuilder(16, 3, 0.2F).setPrice(Items.IRON_INGOT, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.IRON_ORE, 1, 2).build()));
        	event.getTrades().get(2).add((new RandomTradeBuilder(16, 4, 0.2F).setPrice(Items.GOLD_INGOT, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.GOLD_ORE, 1, 2).build()));
        	event.getTrades().get(2).add((new RandomTradeBuilder(16, 4, 0.2F).setPrice(Items.GOLD_INGOT, 1,1).setPrice2(Items.NETHERRACK, 8,8).setForSale(Items.NETHER_GOLD_ORE, 1, 2).build()));
        	
        	event.getTrades().get(3).add((new RandomTradeBuilder(16, 5, 0.05F).setPrice(Items.REDSTONE, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.REDSTONE_ORE, 1, 2).build()));
        	event.getTrades().get(3).add((new RandomTradeBuilder(16, 5, 0.05F).setPrice(Items.LAPIS_LAZULI, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.LAPIS_ORE, 1, 2).build()));
        	
        	event.getTrades().get(4).add((new RandomTradeBuilder(16, 5, 0.2F).setPrice(Items.QUARTZ, 1,1).setPrice2(Items.NETHERRACK, 8,8).setForSale(Items.NETHER_QUARTZ_ORE, 1, 2).build()));
        	event.getTrades().get(4).add((new RandomTradeBuilder(16, 5, 0.2F).setPrice(Items.DIAMOND, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.DIAMOND_ORE, 1, 2).build()));
        	
        	event.getTrades().get(5).add((new RandomTradeBuilder(16, 6, 0.2F).setPrice(Items.EMERALD, 1,1).setPrice2(Items.STONE, 8,8).setForSale(Items.EMERALD_ORE, 1, 2).build()));    
        	event.getTrades().get(5).add((new RandomTradeBuilder(16, 6, 0.2F).setPrice(Items.NETHERITE_INGOT, 1,1).setPrice2(Items.NETHERRACK, 8,8).setForSale(Items.ANCIENT_DEBRIS, 1, 1).build()));  
        	event.getTrades().get(5).add((new RandomTradeBuilder(16, 6, 0.05F).setPrice(Items.WITHER_SKELETON_SKULL, 3,6).setPrice2(Items.SOUL_SAND, 4,4).setForSale(Items.NETHER_STAR, 1, 1).build()));    
        } 
        
    }
}
