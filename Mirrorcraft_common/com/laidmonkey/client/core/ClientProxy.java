package com.laidmonkey.client.core;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import com.laidmonkey.common.core.CommonProxy;

public class ClientProxy extends CommonProxy 
{
    @Override
    public void initRenderersAndTextures() 
    {
       // RenderingRegistry.registerEntityRenderingHandler(EntityMirrorcraft.class, new RenderMirrorcraft());
    }

    @Override
    public void initTickHandlers() 
    {
        
    }
        
    public static HashMap<BufferedImage, Integer> bufferedImageID = new HashMap<BufferedImage, Integer>();
    public static HashMap<String, BufferedImage> bufferedImages = new HashMap<String, BufferedImage>();

}
