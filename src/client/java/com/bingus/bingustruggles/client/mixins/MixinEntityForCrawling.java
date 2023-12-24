package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStrugglesClient;
import net.minecraft.src.client.player.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityForCrawling {
    boolean isProne = BingusStrugglesClient.isProne;
    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 0), index = 1 )
    public double changePushOutOfBlockBehaviorForTheFirstOne(double x){
        if(isProne) return (x-500);
        else return(x);
    }
    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 1), index = 1 )
    public double changePushOutOfBlockBehaviorForTheSecondOne(double x){
        if(isProne) return (x-500);
        else return(x);
    }
    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 2), index = 1 )
    public double changePushOutOfBlockBehaviorForTheThirdOne(double x){
        if(isProne) return (x-500);
        else return(x);
    }
    @ModifyArg(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/client/player/EntityPlayerSP;pushOutOfBlocks(DDD)Z", ordinal = 3), index = 1 )
    public double changePushOutOfBlockBehaviorForTheFourthOne(double x){
        if(isProne) return (x-500);
        else return(x);
    }

}
