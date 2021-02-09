package com.cjdabomb.moreores.registries;

import java.util.Set;

import com.cjdabomb.moreores.MoreOres;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class VillagerProfessionsRegistry {
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.PROFESSIONS, MoreOres.MOD_ID);
    public static final DeferredRegister<PointOfInterestType> POINTS_OF_INTEREST = DeferredRegister.create(ForgeRegistries.POI_TYPES, MoreOres.MOD_ID);
	
    public static final PointOfInterestType JEWELER_POI = register("jeweler", getAllStates(Blocks.EMERALD_BLOCK), 1, 1);
	
	
    public static final RegistryObject<VillagerProfession> JEWELER = PROFESSIONS.register("jeweler",
            () -> new VillagerProfession("jewler",
            	    JEWELER_POI, 
              		ImmutableSet.of(),
            		ImmutableSet.of(),
                    SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH));

    public static Set<BlockState> getAllStates(Block blockIn) {
        return ImmutableSet.copyOf(blockIn.getStateContainer().getValidStates());
     }

	private static PointOfInterestType register(String string2,
			java.util.Set<BlockState> allStates, int i, int j) {
		return null;
	}

	void string ( Set<BlockState> allStates) {
		
	}

}

	
