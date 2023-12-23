package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStrugglesClient;
import net.minecraft.client.Minecraft;
import net.minecraft.src.client.renderer.EntityRenderer;
import net.minecraft.src.game.entity.EntityLiving;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityPlayer {
@Inject(method = "orientCamera", at = @At("TAIL"))
    private void orientCamera(float deltaTicks, CallbackInfo ci ){
    if(BingusStrugglesClient.isProne) GL11.glTranslatef(0.0F, 1.25F, 0.0F);
    }
}
