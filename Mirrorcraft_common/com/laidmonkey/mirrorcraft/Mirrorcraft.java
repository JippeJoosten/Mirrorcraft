package com.laidmonkey.mirrorcraft;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = Mirrorcraft.modId, 
     name = Mirrorcraft.modId, 
     version = Mirrorcraft.version  )

@NetworkMod(clientSideRequired = true,
            serverSideRequired = true)

public class Mirrorcraft {
    //Texture editing time
    public static final String version = "1.0.0";
    public static final String modId = "Mirrorcraft";
    
    @Instance("Mirrorcraft")
    public static Mirrorcraft instance;
    
    @PreInit
    public void preLoad(FMLPreInitializationEvent event)
    {
    
    }
    
    @Init
    public void load(FMLInitializationEvent event)
    {
        
    }
    
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
    
    @ForgeSubscribe
    public void onWorldLoad(WorldEvent.Load event)
    {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && event.world.provider.dimensionId == 0)
        {
            
        }
    }

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event)
    {
        
    }

    @ServerStarted
    public void serverStarted(FMLServerStartedEvent event)
    {
    }


}
