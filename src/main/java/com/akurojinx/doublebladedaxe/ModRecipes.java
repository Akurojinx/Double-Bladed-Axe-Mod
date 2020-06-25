package com.akurojinx.doublebladedaxe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS,DoubleBladedAxeMod.MOD_ID);
	
	public static final RegistryObject<SpecialRecipeSerializer<PotionAxeRecipe>> POTION_AXE_RECIPE = RECIPES.register("crafting_special_axe_potion",() -> (new SpecialRecipeSerializer<>(PotionAxeRecipe::new)));

	public static void init(IEventBus modbus) {
		RECIPES.register(modbus);
	}
	
}
