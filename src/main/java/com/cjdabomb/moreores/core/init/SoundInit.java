package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
	
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MoreOres.MOD_ID);
	
	public static final RegistryObject<SoundEvent> JEWELER_BARREL_OPEN = SOUNDS.register("jewel", 
			() -> new SoundEvent(new ResourceLocation("moreores:sounds/jewel")));
}
