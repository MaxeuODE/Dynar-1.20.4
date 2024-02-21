package com.maxeu.dynar.event;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

public class PlayerUseEvent {
    public static void onPlayerUse(){
        UseItemCallback.EVENT.register((player, world, hand) -> {

            return new TypedActionResult<>(ActionResult.PASS,player.getStackInHand(hand));
        });
    }
}
