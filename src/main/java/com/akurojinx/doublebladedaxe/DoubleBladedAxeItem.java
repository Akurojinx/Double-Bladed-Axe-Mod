package com.akurojinx.doublebladedaxe;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DoubleBladedAxeItem extends AxeItem{

	
	public static final float HIT_RADIUS = 2.79f;


	
	public DoubleBladedAxeItem(IItemTier tier) {
		this(tier, new Item.Properties()
				.maxStackSize(1)
				.group(ItemGroup.COMBAT)
				);
	}
	
	public DoubleBladedAxeItem(IItemTier tier, Item.Properties properties) {
		super(tier,6.7f,-3.7f, properties
				.maxStackSize(1)
				.group(ItemGroup.COMBAT)
				);
	}
	
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		      return !player.isCreative();
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		World world = target.world;
		if(!world.isRemote) {
			List<EffectInstance> effects = PotionUtils.getEffectsFromStack(stack);
			AxisAlignedBB aabb = new AxisAlignedBB(target.getPositionVec().add(-HIT_RADIUS,-HIT_RADIUS,-HIT_RADIUS), target.getPositionVec().add(HIT_RADIUS,HIT_RADIUS,HIT_RADIUS));
			for(LivingEntity entity:world.getEntitiesWithinAABB(LivingEntity.class,aabb)) {
				
				if( !entity.equals(attacker) && entity.getDistance(target)<=HIT_RADIUS) {
					entity.attackEntityFrom(DamageSource.causeMobDamage(attacker),10.0f);
					for(EffectInstance effectInstance:effects) {
						if (effectInstance.getPotion().isInstant()) {
				               effectInstance.getPotion().affectEntity(attacker, attacker, entity, effectInstance.getAmplifier(), 1.0D);
						} else {
				            	entity.addPotionEffect(new EffectInstance(
				            			effectInstance.getPotion(), 
				            			(int)((effectInstance.getPotion() == Effects.POISON)? 40: (effectInstance.getDuration()/8.2f)),
				            			1
				            			));
				        }
					}
				}
			}
		}
		
			
		stack.damageItem(1, attacker, (p_220045_0_) -> {
	         p_220045_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	     });
		
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
	}

	
	
}
