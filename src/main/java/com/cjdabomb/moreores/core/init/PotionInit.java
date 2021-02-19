package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;

import net.minecraft.potion.Effect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
	
	public static final DeferredRegister<Effect> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MoreOres.MOD_ID);
	

}
