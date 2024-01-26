package com.maxeu.dynar.items;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModItem {
    public static Map<String, Item> ALL_ITEMS= new HashMap<>();
    public static final Obsidian obsidian =new Obsidian(new Item.Settings(),"obsidian");

}
