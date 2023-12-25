package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStrugglesClient;
import net.minecraft.src.client.renderer.entity.RenderPlayer;
import net.minecraft.src.game.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public class MixinPlayerRenderer {
    @Inject(method = "drawFirstPersonHand", at = @At("HEAD"), cancellable = true)
    public void dontDrawWhenCrawling(EntityPlayer player, float deltaTicks, CallbackInfo ci){
        if (BingusStrugglesClient.isProne) ci.cancel();
    }
}
