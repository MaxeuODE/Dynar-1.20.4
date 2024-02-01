package com.maxeu.dynar.items;

import com.maxeu.dynar.registry.ItemRegister;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ObsidianSword extends SwordItem {
    public ObsidianSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings,String name) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        ItemRegister.registerItemUtil(this,name);
    }
}
