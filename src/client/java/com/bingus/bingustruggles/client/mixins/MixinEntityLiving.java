package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStrugglesClient;
import net.minecraft.src.game.entity.EntityLiving;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLiving.class)
public class MixinEntityLiving {
    @Shadow public boolean isJumping;

    //Makes it so that the player cannot jump when crawling
    @Inject(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/game/entity/EntityLiving;handleLavaMovement()Z"))
    public void dontJumpWhenCrawling(CallbackInfo ci){
        if(BingusStrugglesClient.isProne) this.isJumping = false;
    }
}
