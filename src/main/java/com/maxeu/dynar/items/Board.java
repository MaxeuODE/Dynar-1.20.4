package com.maxeu.dynar.items;

import com.maxeu.dynar.registry.ItemRegister;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Board extends Item {
    private int tick = 0;
    private int coolTick = 0;

    public Board(Settings settings, String name) {
        super(settings);
        ItemRegister.registerItemUtil(this, name);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (tick % 20 == 0 && entity instanceof PlayerEntity player) {
            player.sendMessage(Text.of(String.valueOf(tick / 20)));

        }
        tick++;
        if (coolTick > -120) {
            coolTick--;
        }
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (coolTick <= -120) {
            user.heal(10F);
            coolTick = 0;
        }
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.kill();
        return super.useOnEntity(stack, user, entity, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world,tooltip, context);
        if(coolTick%20==0)tooltip.add(Text.literal(String.valueOf((coolTick+120)/20)));
    }
}
