package com.akurojinx.doublebladedaxe;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DoubleBladedAxeMod.MOD_ID)
public class DoubleBladedAxeMod
{
	
	public static final String MOD_ID = "doublebladedaxemod";
	
    
    public DoubleBladedAxeMod() {
    	IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();    	
        ModItems.init(modbus);
        ModRecipes.init(modbus);
        MinecraftForge.EVENT_BUS.register(this);
    }

}
