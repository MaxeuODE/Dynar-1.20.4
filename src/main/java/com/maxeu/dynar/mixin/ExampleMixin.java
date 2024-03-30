//package com.maxeu.dynar.mixin;
//
//import com.maxeu.dynar.command.Test;
//import net.minecraft.server.MinecraftServer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//
//@Mixin(MinecraftServer.class)
//public abstract class ExampleMixin implements Test {
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        ExampleMixin.id = id;
//    }
//
//    @Unique
//    private static String id;
//    @Unique
//    @Override
//    public void print$0() {
//        System.out.println("aaa");
//    }
//    @ModifyArg(method = "setupSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerWorldProperties;setSpawnPos(Lnet/minecraft/util/math/BlockPos;F)V"), index = 1)
//    private static float modified(float par2) {
//        return 1.1F;
//    }

//    @Redirect(method = "setupSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerWorldProperties;setSpawnPos(Lnet/minecraft/util/math/BlockPos;F)V"))
//    private static void redirect(ServerWorldProperties instance, BlockPos blockPos, float v) {
//        System.out.println("aaa");
//    }

//    @Inject(method = "setupSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/ServerWorldProperties;setSpawnPos(Lnet/minecraft/util/math/BlockPos;F)V"))
//    private static void injected(ServerWorld world, ServerWorldProperties worldProperties, boolean bonusChest, boolean debugWorld, CallbackInfo ci) {
//        System.out.println("aaa");
//    }

//    @Inject(method = "setupSpawn", at = @At("HEAD"))
//    private static void injected(ServerWorld world, ServerWorldProperties worldProperties, boolean bonusChest, boolean debugWorld, CallbackInfo ci) {
//        System.out.println("aaa");
//        ExampleMixin.id = "aa";
//    }
//}