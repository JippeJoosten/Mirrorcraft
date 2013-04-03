package com.laidmonkey.common.core;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.laidmonkey.common.Mirrorcraft;

import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class CommonProxy {
    public void initCommands(MinecraftServer server)
    {
        ICommandManager manager = server.getCommandManager();
        if(manager instanceof CommandHandler)
        {
           // CommandHandler handler = (CommandHandler)manager;
          //  handler.registerCommand(new CommandMirrorcraft());
        }
    }

    public void initMod()
    {

    }

    public void initRenderersAndTextures() {}

    public void initSounds() {}

    public void initTickHandlers() 
    {
    }

    public void loadData(WorldServer world)
    {
        try
        {
            File file = new File(world.getChunkSaveLocation(), "Mirrorcraft.dat");
            if(!file.exists())
            {
                saveData = new NBTTagCompound();
                Mirrorcraft.console("Save data does not exist!");
                return;
            }
            saveData = CompressedStreamTools.readCompressed(new FileInputStream(file));
        }
        catch(EOFException e)
        {
            Mirrorcraft.console("Save data is corrupted! Attempting to read from backup.");
            try
            {
                File file = new File(world.getChunkSaveLocation(), "Mirrorcraft_backup.dat");
                if(!file.exists())
                {
                    saveData = new NBTTagCompound();
                    Mirrorcraft.console("No backup detected!");
                    return;
                }
                saveData = CompressedStreamTools.readCompressed(new FileInputStream(file));

                File file1 = new File(world.getChunkSaveLocation(), "Mirrorcraft.dat");
                file1.delete();
                file.renameTo(file1);
                Mirrorcraft.console("Restoring data from backup.");
            }
            catch(Exception e1)
            {
                saveData = new NBTTagCompound();
                Mirrorcraft.console("Even your backup data is corrupted. WMirrorcraft have you been doing?!", true);
            }
        }
        catch(IOException e)
        {
            saveData = new NBTTagCompound();
            Mirrorcraft.console("Failed to read save data!");
        }
    }
    
    public void saveData(WorldServer world)
    {
        if(saveData != null)
        {
            for(Map.Entry<String, MirrorcraftInfo> e : playerWornMirrorcraft.entrySet())
            {
                MirrorcraftInfo Mirrorcraft = e.getValue();
                saveData.setString(e.getKey() + "_wornMirrorcraft", Mirrorcraft.MirrorcraftName);
                saveData.setInteger(e.getKey() + "_colourR", Mirrorcraft.colourR);
                saveData.setInteger(e.getKey() + "_colourG", Mirrorcraft.colourG);
                saveData.setInteger(e.getKey() + "_colourB", Mirrorcraft.colourB);
            }
            
            try
            {
                if(world.getChunkSaveLocation().exists())
                {
                    File file = new File(world.getChunkSaveLocation(), "Mirrorcraft.dat");
                    if(file.exists())
                    {
                        File file1 = new File(world.getChunkSaveLocation(), "Mirrorcraft_backup.dat");
                        if(file1.exists())
                        {
                            if(file1.delete())
                            {
                                file.renameTo(file1);
                            }
                            else
                            {
                                Mirrorcraft.console("Failed to delete mod backup data!", true);
                            }
                        }
                        else
                        {
                            file.renameTo(file1);
                        }
                    }
                    CompressedStreamTools.writeCompressed(saveData, new FileOutputStream(file));
                }
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
                throw new RuntimeException("Failed to save Mirrorcraft data");
            }
        }
        else
        {
            Mirrorcraft.console("Mod data is null! This is a problem!", true);
        }
    }
    
    public void sendPlayerListOfWornMirrorcraft(EntityPlayer player, boolean sendAllPlayerMirrorcraftInfo)
    {
        this.sendPlayerListOfWornMirrorcraft(player, sendAllPlayerMirrorcraftInfo, true);
    }
    
    public void sendPlayerListOfWornMirrorcraft(EntityPlayer player, boolean sendAllPlayerMirrorcraftInfo, boolean ignorePlayer) //if false send the only player's info to all players
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream stream = new DataOutputStream(bytes);

        try
        {
            stream.writeByte(1); //packetID;

            if(sendAllPlayerMirrorcraftInfo)
            {
                Iterator<Entry<String, MirrorcraftInfo>> ite = Mirrorcraft.proxy.playerWornMirrorcraft.entrySet().iterator();

                while(ite.hasNext())
                {
                    Entry<String, MirrorcraftInfo> e = ite.next();

                    MirrorcraftInfo Mirrorcraft = e.getValue();

                    stream.writeUTF(e.getKey());
                    stream.writeUTF(Mirrorcraft.MirrorcraftName);
                    stream.writeInt(Mirrorcraft.colourR);
                    stream.writeInt(Mirrorcraft.colourG);
                    stream.writeInt(Mirrorcraft.colourB);

                    if(bytes.toByteArray().length > 32000)
                    {
                        stream.writeUTF("#endPacket");
                        stream.writeUTF("#endPacket");

                        PacketDispatcher.sendPacketToPlayer(new Packet250CustomPayload("Mirrorcraft", bytes.toByteArray()), (Player)player);

                        bytes = new ByteArrayOutputStream();
                        stream = new DataOutputStream(bytes);

                        stream.writeByte(1); //id
                    }
                }
                PacketDispatcher.sendPacketToPlayer(new Packet250CustomPayload("Mirrorcraft", bytes.toByteArray()), (Player)player);
            }
            else
            {
                MirrorcraftInfo Mirrorcraft = playerWornMirrorcraft.get(player.username);
                if(Mirrorcraft == null)
                {
                    Mirrorcraft = new MirrorcraftInfo();
                }

                stream.writeUTF(player.username);
                stream.writeUTF(Mirrorcraft.MirrorcraftName);
                stream.writeInt(Mirrorcraft.colourR);
                stream.writeInt(Mirrorcraft.colourG);
                stream.writeInt(Mirrorcraft.colourB);

                Packet250CustomPayload packet = new Packet250CustomPayload("Mirrorcraft", bytes.toByteArray());

                for(int i = 0; i < FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.size(); i++)
                {
                    EntityPlayer player1 = (EntityPlayer)FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.get(i);

                    if(player.username.equalsIgnoreCase(player1.username) && ignorePlayer)
                    {
                        continue;
                    }

                    PacketDispatcher.sendPacketToAllPlayers(packet);
                }
            }

        }
        catch(IOException e)
        {}

    }
    
    public static NBTTagCompound saveData = null;

    public static HashMap<String, MirrorcraftInfo> playerWornMirrorcraft = new HashMap<String, MirrorcraftInfo>();



}
