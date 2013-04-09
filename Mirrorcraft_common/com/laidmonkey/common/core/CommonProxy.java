package com.laidmonkey.common.core;

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

    public void initMod() {

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
