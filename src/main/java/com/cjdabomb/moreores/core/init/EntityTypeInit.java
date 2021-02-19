package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.entities.DarkMatterEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeInit {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MoreOres.MOD_ID);
	
	
	public static final RegistryObject<EntityType<DarkMatterEntity>> DARK_MATTER = ENTITIES.register("dark_matter", () -> EntityType.Builder.<DarkMatterEntity>create
			(DarkMatterEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).trackingRange(10).func_233608_b_(10).build("dark_matter"));

}
