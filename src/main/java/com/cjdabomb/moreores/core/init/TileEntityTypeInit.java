package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.te.AlloySynthesizerTileEntity;
import com.cjdabomb.moreores.common.te.JewelerBarrelTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MoreOres.MOD_ID); 
	
	
	public static final RegistryObject<TileEntityType<JewelerBarrelTileEntity>> JEWELER_BARREL_TILE_ENTITY = TILE_ENTITIES.register("jeweler_barrel", 
			() ->  TileEntityType.Builder.of(JewelerBarrelTileEntity::new, BlockInit.JEWELER_BARREL.get()).build(null));
	
	public static final RegistryObject<TileEntityType<AlloySynthesizerTileEntity>> ALLOY_SYNTHESIZER_TILE_ENTITY = TILE_ENTITIES.register("alloy_synthesizer", 
			() ->  TileEntityType.Builder.of(AlloySynthesizerTileEntity::new, BlockInit.ALLOY_SYNTHESIZER.get()).build(null));
}
