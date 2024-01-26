package com.maxeu.dynar.registry;

import com.maxeu.dynar.Dynar;
import com.maxeu.dynar.items.ModItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ItemRegister {
    public static void onRegistry(){
        final List<String> name= ModItem.ALL_ITEMS.keySet().stream().toList();
        final List<Item> item= ModItem.ALL_ITEMS.values().stream().toList();
        for (int i=0;i< name.size();i++) {
            Registry.register(Registries.ITEM, new Identifier(Dynar.MOD_ID, name.get(i)),item.get(i));
        }
    }

    public static void registerItemUtil(Item item,String name){
        ModItem.ALL_ITEMS.put(name,item);

    }

}
