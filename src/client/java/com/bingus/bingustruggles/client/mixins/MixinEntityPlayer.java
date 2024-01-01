package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStrugglesClient;
import net.minecraft.src.client.renderer.EntityRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityPlayer {

    //Orients the camera when crawling
@Inject(method = "orientCamera", at = @At("TAIL"))
    private void orientCameraCrawling(float deltaTicks, CallbackInfo ci ){
    if(BingusStrugglesClient.isProne) GL11.glTranslatef(0.0F, 1.25F, 0.0F);
    }
}
