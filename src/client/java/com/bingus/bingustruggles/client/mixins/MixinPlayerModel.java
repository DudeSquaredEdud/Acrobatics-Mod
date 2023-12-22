package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStrugglesClient;
import net.minecraft.client.Minecraft;
import net.minecraft.src.client.model.ModelBase2;
import net.minecraft.src.client.model.ModelBiped2;
import net.minecraft.src.client.model.Piece;
import net.minecraft.src.game.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelBiped2.class)
public abstract class MixinPlayerModel extends ModelBase2 {
    @Shadow
    public Piece bipedBody;
    @Shadow
    public Piece bipedRightArm;
    @Shadow
    public Piece bipedLeftArm;

    @Inject(method = "setRotationAngles", at = @At("HEAD"))
    public void injectRotationAngles (float forward, float haltDelta, float tick, float yaw, float pitch, float unused, Entity entity, CallbackInfo ci){
        this.bipedBody.rotateAngleX += 0.9F;
        this.bipedRightArm.rotateAngleX += -0.6283185F;
        this.bipedLeftArm.rotateAngleX += -0.6283185F;
    }

    @Override
    protected void init() {
    }
}
