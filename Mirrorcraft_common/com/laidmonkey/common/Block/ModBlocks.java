package com.laidmonkey.common.Block;

import com.laidmonkey.common.lib.BlockIds;
import com.laidmonkey.common.lib.Strings;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


public class ModBlocks {

    /* Mod block instances */
    public static Block blockSolar;

    public static void init() {

        blockSolar = new BlockSolar(BlockIds.SOLAR_DEFAULT, Material.glass, "MirrorCraft/Mirrorcraft_resources:SteamProducer");

        initBlockRecipes();
        gameRegistry();
        languageRegistry();

    }
    private static void gameRegistry()
    {
        GameRegistry.registerBlock(blockSolar, Strings.SOLAR_NAME);
    }
    private static void languageRegistry()
    {
        LanguageRegistry.addName(blockSolar, "Solar");
    }

    private static void initBlockRecipes() {

        // Calcinator Recipe
        /*
         * Temporarily disabled for pre-release 1, as it is not completely
         * functional GameRegistry.addRecipe(new ItemStack(calcinator), new
         * Object[] {"i i","iii","sfs", Character.valueOf('i'), Item.ingotIron,
         * Character.valueOf('s'), Block.stone, Character.valueOf('f'),
         * Item.flintAndSteel });
         */
    }
}