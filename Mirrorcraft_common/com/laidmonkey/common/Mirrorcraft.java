package com.laidmonkey.common;

import java.util.logging.Level;
import net.minecraft.creativetab.CreativeTabs;

import com.laidmonkey.common.Block.ModBlocks;
import com.laidmonkey.common.core.CommonProxy;
import com.laidmonkey.common.core.CreativTabMirrorcraft;
import com.laidmonkey.common.core.LoggerHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Mirrorcraft.modId, name = Mirrorcraft.modId, version = Mirrorcraft.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Mirrorcraft {
    // Texture editing time
    public static final String version = "1.0.0";
    public static final String modId = "Mirrorcraft";
    
    @Instance("Mirrorcraft")
    public static Mirrorcraft instance;

    @SidedProxy(clientSide = "com.laidmonkey.client.core.ClientProxy", serverSide = "com.laidmonkey.common.core.CommonProxy")
    public static CommonProxy proxy;

    public static final CreativeTabs tabMirrorcraft = new CreativTabMirrorcraft(
            "Mirrorcraft");

    @PreInit
    public void preLoad(FMLPreInitializationEvent event) {

    }

    @Init
    public void load(FMLInitializationEvent event) {

    }

    @PostInit
    public void postInit(FMLPostInitializationEvent event) {
     // Initialize mod blocks
        CommonProxy.initMod();
        ModBlocks.init();
        
        
    }
    

    @ServerStarting
    public void serverStarting(FMLServerStartingEvent event) {
        

    }

    @ServerStarted
    public void serverStarted(FMLServerStartedEvent event) {
    }

    public static int getNetId() {
        return FMLNetworkHandler.instance()
                .findNetworkModHandler(Mirrorcraft.instance).getNetworkId();
    }

    public static void console(String s, boolean warning) {
        StringBuilder sb = new StringBuilder();
        LoggerHelper.log(warning ? Level.WARNING : Level.INFO, sb.append("[")
                .append(version).append("] ").append(s).toString());
    }

    public static void console(String s) {
        console(s, false);
    }
}
