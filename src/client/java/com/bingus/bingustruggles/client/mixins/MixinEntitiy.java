package com.bingus.bingustruggles.client.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.src.game.block.Material;
import net.minecraft.src.game.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntitiy {
    @Inject(method = "isEntityInsideOpaqueBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/game/level/World;isBlockNormalCube(III)Z", shift = At.Shift.BEFORE))
    public void AccountForCrawling(CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 1) LocalRef<Float> LocalRef){
        LocalRef.set(LocalRef.get()-1.5F);
        //TODO - make this a case only for when crawling
    }

//    @Inject(method = "isInsideOfMaterial", at = @At(value = "HEAD", shift = At.Shift.BY, by = 1))
//    public void StopDoingThat(Material material, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 0) LocalRef<Double> LocalRef){
//        LocalRef.set(LocalRef.get() -1.5);
//    }
}
