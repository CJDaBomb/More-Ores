package com.cjdabomb.moreores.core.init;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.containers.AlloySynthesizerContainer;
import com.cjdabomb.moreores.common.containers.JewelerBarrelContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {

	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, MoreOres.MOD_ID);

	public static final RegistryObject<ContainerType<JewelerBarrelContainer>> JEWELER_BARREL_CONTAINER = CONTAINERS
			.register("jeweler_barrel", () -> IForgeContainerType.create(JewelerBarrelContainer::new));
	public static final RegistryObject<ContainerType<AlloySynthesizerContainer>> ALLOY_SYNTHESIZER_CONTAINER = CONTAINERS
			.register("alloy_synthesizer", () -> IForgeContainerType.create(AlloySynthesizerContainer::new));

}
