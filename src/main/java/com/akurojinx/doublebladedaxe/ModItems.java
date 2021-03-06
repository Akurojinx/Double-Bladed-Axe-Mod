package com.akurojinx.doublebladedaxe;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DoubleBladedAxeMod.MOD_ID);
	
	
	public static final RegistryObject<Item> WOOD_DOUBLE_BLADED_AXE = ITEMS.register("wood_double_bladed_axe", () -> new DoubleBladedAxeItem(ItemTier.WOOD));
	public static final RegistryObject<Item> STONE_DOUBLE_BLADED_AXE = ITEMS.register("stone_double_bladed_axe", () -> new DoubleBladedAxeItem(ItemTier.STONE));
	public static final RegistryObject<Item> IRON_DOUBLE_BLADED_AXE = ITEMS.register("iron_double_bladed_axe", () -> new DoubleBladedAxeItem(ItemTier.IRON));
	public static final RegistryObject<Item> GOLD_DOUBLE_BLADED_AXE = ITEMS.register("gold_double_bladed_axe", () -> new DoubleBladedAxeItem(ItemTier.GOLD));
	public static final RegistryObject<Item> DIAMOND_DOUBLE_BLADED_AXE = ITEMS.register("diamond_double_bladed_axe", () -> new DoubleBladedAxeItem(ItemTier.DIAMOND));
	public static final RegistryObject<Item> NETHERITE_DOUBLE_BLADED_AXE = ITEMS.register("netherite_double_bladed_axe", () -> new DoubleBladedAxeItem(ItemTier.NETHERITE));
	public static final RegistryObject<Item> HAMBURGER_BATTLE_AXE = ITEMS.register("hamburger_battle_axe", () -> new DoubleBladedAxeItem(ItemTier.WOOD,new Item.Properties().food(new Food.Builder().meat().saturation(10).hunger(10).build())));
	public static final RegistryObject<Item> TNT_DOUBLE_BLADED_AXE = ITEMS.register("tnt_double_bladed_axe", ()-> new TNTDoubleBladedAxeItem(ItemTier.STONE));
	
	public static void init(IEventBus modbus) {
		ITEMS.register(modbus);
	}
	
	
	
}
