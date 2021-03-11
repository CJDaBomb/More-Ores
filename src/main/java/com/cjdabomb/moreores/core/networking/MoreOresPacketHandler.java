package com.cjdabomb.moreores.core.networking;

import com.cjdabomb.moreores.MoreOres;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class MoreOresPacketHandler {

	private static final String NETWORK_VERSION = "0.19.0";

	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(MoreOres.MOD_ID, "network"), () -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), // Client acceptance predicate
			version -> version.equals(NETWORK_VERSION) // Server acceptance predicate
	);

	public static void initialize() {

	}
}
