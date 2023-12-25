package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStrugglesClient;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.Minecraft;
import net.minecraft.src.client.GameSettings;
import net.minecraft.src.client.player.EntityPlayerSP;
import net.minecraft.src.client.player.MovementInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityForCrawling {

    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 0), index = 1 )
    public double changePushOutOfBlockBehaviorForTheFirstOne(double x){
        if(BingusStrugglesClient.isProne) return (x-500);
        else return(x);
    }
    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 1), index = 1 )
    public double changePushOutOfBlockBehaviorForTheSecondOne(double x){
        if(BingusStrugglesClient.isProne) return (x-500);
        else return(x);
    }
    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 2), index = 1 )
    public double changePushOutOfBlockBehaviorForTheThirdOne(double x){
        if(BingusStrugglesClient.isProne) return (x-500);
        else return(x);
    }
    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 3), index = 1 )
    public double changePushOutOfBlockBehaviorForTheFourthOne(double x){
        if(BingusStrugglesClient.isProne) return (x-500);
        else return(x);
    }

}
