package com.laidmonkey.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativTabMirrorcraft extends CreativeTabs {

    public CreativTabMirrorcraft(String label) {
        super(label);
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Item.brick);
    }

    @Override
    public String getTranslatedTabLabel() {
        return "Mirrorcraft";
    }
}
