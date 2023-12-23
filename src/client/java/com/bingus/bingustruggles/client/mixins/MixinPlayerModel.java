package com.bingus.bingustruggles.client.mixins;

import com.bingus.bingustruggles.BingusStruggles;
import com.bingus.bingustruggles.BingusStrugglesClient;
import net.minecraft.client.Minecraft;
import net.minecraft.src.client.model.ModelBase2;
import net.minecraft.src.client.model.ModelBiped2;
import net.minecraft.src.client.model.Piece;
import net.minecraft.src.client.model.PieceNew;
import net.minecraft.src.game.MathHelper;
import net.minecraft.src.game.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.logging.Logger;

@Mixin(ModelBiped2.class)
public abstract class MixinPlayerModel extends ModelBase2{
    @Shadow
    public PieceNew bipedBody;
    @Shadow
    public PieceNew bipedRightArm;
    @Shadow
    public PieceNew bipedLeftArm;
    @Shadow
    public PieceNew bipedRightLeg;
    @Shadow
    public PieceNew bipedLeftLeg;
    @Shadow
    public PieceNew bipedHead;
    @Shadow
    public PieceNew bipedHeadwear;
    @Shadow public boolean isSneak;
    float f6 = this.onGround;

    @Inject(method = "setRotationAngles", at = @At("TAIL"))
    public void injectRotationAngles (float forward, float haltDelta, float tick, float yaw, float pitch, float unused, Entity entity, CallbackInfo ci){
        if (this.isSneak) {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightLeg.rotateAngleX -= 0.0F;
            this.bipedLeftLeg.rotateAngleX -= 0.0F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
            this.bipedHeadwear.rotationPointY = 1.0F;
        }
        else if(BingusStrugglesClient.isProne){

            this.bipedBody.rotateAngleY = 0.0F;
            this.bipedHead.rotationPointY = 22.0F;
            this.bipedBody.rotationPointY = 22.0F;
            this.bipedBody.rotateAngleX = (float) Math.PI/2;

            this.bipedRightArm.rotationPointY = 22.0F;
            this.bipedLeftArm.rotationPointY = 22.0F;
            this.bipedRightArm.rotateAngleX = (float) ((MathHelper.cos(forward * 0.6662F + 3.141593F) * 0.7F * haltDelta) -Math.PI/2);
            this.bipedLeftArm.rotateAngleX = (float) ((MathHelper.cos(forward * 0.6662F) * 0.7F * haltDelta) -Math.PI/2);


            this.bipedRightLeg.rotationPointY = 22.0F;
            this.bipedLeftLeg.rotationPointY = 22.0F;
            this.bipedRightLeg.rotationPointZ = 12.0F;
            this.bipedLeftLeg.rotationPointZ = 12.0F;
            this.bipedRightLeg.rotateAngleX = (float) ((MathHelper.cos(forward * 0.6662F) * 0.7F * haltDelta) +Math.PI/2);
            this.bipedLeftLeg.rotateAngleX = (float) ((MathHelper.cos(forward * 0.6662F + 3.141593F) * 0.7F * haltDelta) +Math.PI/2);
        }
        else{
            this.bipedRightArm.rotationPointY = 2.0F;
            this.bipedLeftArm.rotationPointY = 2.0F;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
            this.bipedBody.rotationPointY = 0.0F;
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.0F;
            this.bipedLeftLeg.rotationPointZ = 0.0F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.bipedHeadwear.rotationPointY = 0.0F;
        }

    }

}
