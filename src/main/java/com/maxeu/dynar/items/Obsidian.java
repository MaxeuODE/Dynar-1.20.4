package com.maxeu.dynar.items;

import com.maxeu.dynar.registry.ItemRegister;
import net.minecraft.item.Item;

public class Obsidian extends Item {
    public Obsidian(Settings settings,String name) {
        super(settings);
        ItemRegister.registerItemUtil(this,name);
    }
}
