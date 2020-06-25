package com.akurojinx.doublebladedaxe;

import org.apache.logging.log4j.LogManager;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PotionAxeRecipe extends SpecialRecipe{
	
	public PotionAxeRecipe(ResourceLocation idIN) {
		super(idIN);
	}

	@Override
	public boolean matches(CraftingInventory inv, World worldIn) {
		int numAxe = 0;
		int numPotions = 0;
		
		for(int i = 0; i < inv.getWidth(); i++) {
			for(int j = 0; j < inv.getHeight(); j++) {
				ItemStack itemStack= inv.getStackInSlot(i + j * inv.getWidth());
				if(itemStack.getItem() instanceof DoubleBladedAxeItem) {
					if(PotionUtils.getPotionFromItem(itemStack).getEffects().size() != 0)
						return false;
					
					if(numAxe > 0)
						return false;
					numAxe++;
				}
				else if(itemStack.getItem() instanceof PotionItem) {
					if(numPotions > 0)
						return false;
					Potion potion = PotionUtils.getPotionFromItem(itemStack);
					for(EffectInstance e:potion.getEffects())
						if(e.getPotion().equals(Effects.INSTANT_DAMAGE))
							return false;
					numPotions++;
				}

					
				
			}
		}
		
		LogManager.getLogger().info("Matcher man "+(numAxe == 1)+ (numPotions == 1));
		return numAxe == 1 && numPotions == 1;
		
	}

	@Override
	public ItemStack getCraftingResult(CraftingInventory inv) {
		
		Potion potionEffect = null;
		Item axeItem = null;
		
		for(int i=0;i<inv.getSizeInventory();i++) {
			if(inv.getStackInSlot(i).getItem() instanceof DoubleBladedAxeItem)
				axeItem = inv.getStackInSlot(i).getItem();
			else if(inv.getStackInSlot(i).getItem() instanceof PotionItem)
				potionEffect = PotionUtils.getPotionFromItem(inv.getStackInSlot(i));
		}
		LogManager.getLogger().info("I will stand on a plain and pretend it's a hill");
		if(potionEffect == null || axeItem ==null)
			return ItemStack.EMPTY;
		
		ItemStack out = new ItemStack(axeItem);

		
		PotionUtils.addPotionToItemStack(out, potionEffect);
		
		return out;
		
			
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {

		return ModRecipes.POTION_AXE_RECIPE.get();
	}
}
