package com.bingus.bingustruggles.client.mixins;

import net.minecraft.src.client.packets.Packet10Flying;
import net.minecraft.src.client.packets.Packet11PlayerPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Packet11PlayerPosition.class)
public class MixinPacket11PlayerPosition extends Packet10Flying {

    @Inject(method = "<init>(DDDDZ)V", at = @At("TAIL"))
    public void tryingAgain(CallbackInfo ci){
        this.stance = this.yPosition + 0.11;
    }
}
