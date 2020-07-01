package com.akurojinx.doublebladedaxe;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class TNTDoubleBladedAxeItem extends DoubleBladedAxeItem{

	public TNTDoubleBladedAxeItem(IItemTier tier) {
		super(tier);
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		super.hitEntity(stack, target, attacker);
		stack.shrink(1);
		World world = target.world;
		if(!world.isRemote) {
			world.createExplosion(attacker, target.getPosX(), target.getPosY(), target.getPosZ(), 3, Explosion.Mode.DESTROY);
		}
		return true;
	}
	
	
}
