package com.maxeu.dynar.items;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Rarity;

import java.util.HashMap;
import java.util.Map;

public class ModItem {
    public static Map<String, Item> ALL_ITEMS= new HashMap<>();
    public static final Obsidian obsidian =new Obsidian(new Item.Settings().fireproof().maxCount(16).rarity(Rarity.RARE),"obsidian");
    public static final ObsidianSword obsidianSwords =new ObsidianSword(ToolMaterials.NETHERITE,9,1.4f,new Item.Settings(),"obsidian_sword");
    public static final Board COUNTBOARD =new Board(new Item.Settings(),"board");
}
