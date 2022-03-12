package io.github.anonymous123_code.endermantic_overhaul;

import io.github.anonymous123_code.endermantic_overhaul.blocks.EnderForceConcentratorBlock;
import io.github.anonymous123_code.endermantic_overhaul.blocks.NegativeEnderForceEmitterBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndermanticOverhaulMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("endermantic_overhaul");
	public static final Block ENDER_FORCE_CONCENTRATOR = new EnderForceConcentratorBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(4.0f));
	public static final Block NEGATIVE_ENDER_FORCE_EMITTER = new NegativeEnderForceEmitterBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(4));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.BLOCK, new Identifier("endermantic_overhaul", "ender_force_concentrator"), ENDER_FORCE_CONCENTRATOR);
		Registry.register(Registry.ITEM, new Identifier("endermantic_overhaul", "ender_force_concentrator"), new BlockItem(ENDER_FORCE_CONCENTRATOR, new FabricItemSettings().group(ItemGroup.REDSTONE)));

		Registry.register(Registry.BLOCK, new Identifier("endermantic_overhaul", "negative_ender_force_emitter"), NEGATIVE_ENDER_FORCE_EMITTER);
		Registry.register(Registry.ITEM, new Identifier("endermantic_overhaul", "negative_ender_force_emitter"), new BlockItem(NEGATIVE_ENDER_FORCE_EMITTER, new FabricItemSettings().group(ItemGroup.REDSTONE)));
	}
}
