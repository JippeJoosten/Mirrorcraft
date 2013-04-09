package com.laidmonkey.common.core;

import com.laidmonkey.common.Block.TileEntity.TileEntitySolar;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class CommonProxy {
    public void initCommands(MinecraftServer server) {
        ICommandManager manager = server.getCommandManager();
        if (manager instanceof CommandHandler) {
            // CommandHandler handler = (CommandHandler)manager;
            // handler.registerCommand(new CommandMirrorcraft());
        }
    }

    public static void initMod() {
        GameRegistry.registerTileEntity(TileEntitySolar.class, "tileEntityCombiner");

    }

    public void initRenderersAndTextures() {

    }

    public void initSounds() {

    }

    public void initTickHandlers() {
    }

    public void loadData(WorldServer world) {

    }

    public void saveData(WorldServer world) {

    }

}
