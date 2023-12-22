package com.bingus.bingustruggles;


import net.minecraft.client.Minecraft;
import net.minecraft.src.client.model.ModelPlayer;
import net.minecraft.src.game.MathHelper;
import net.minecraft.src.game.entity.Entity;

public class BingusStrugglesCrouchAnim extends ModelPlayer {
    public BingusStrugglesCrouchAnim(float scale) {
        super(scale);
    }

    @Override
    public void setRotationAngles(float forward, float haltDelta, float tick, float yaw, float pitch, float unused, Entity entity) {
        this.bipedHead.rotateAngleY = yaw / (float) (180.0 / Math.PI);
        this.bipedHead.rotateAngleX = pitch / (float) (180.0 / Math.PI);
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(forward * 0.6662F + 3.141593F) * 2.0F * haltDelta * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(forward * 0.6662F) * 2.0F * haltDelta * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(forward * 0.6662F) * 1.4F * haltDelta;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(forward * 0.6662F + 3.141593F) * 1.4F * haltDelta;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        if (this.isRiding) {
            this.bipedRightArm.rotateAngleX += -0.6283185F;
            this.bipedLeftArm.rotateAngleX += -0.6283185F;
            this.bipedRightLeg.rotateAngleX = -1.256637F;
            this.bipedLeftLeg.rotateAngleX = -1.256637F;
            this.bipedRightLeg.rotateAngleY = 0.3141593F;
            this.bipedLeftLeg.rotateAngleY = -0.3141593F;
        }

        if (this.heldItemLeft != 0) {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.3141593F * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F
                    - 0.3141593F * (float)this.heldItemRight;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        if (this.onGround > -9990.0F) {
            float f6 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f8 = MathHelper.sin(f6 * 3.141593F);
            float f10 = MathHelper.sin(this.onGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)(
                    (double)this.bipedRightArm.rotateAngleX - ((double)f8 * 1.2 + (double)f10)
            );
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.141593F) * -0.4F;
        }

        if (this.isSneak) {
            this.bipedBody.rotateAngleX -= 0.5F;
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
        } else {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.0F;
            this.bipedLeftLeg.rotationPointZ = 0.0F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
            this.bipedHeadwear.rotationPointY = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(tick * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(tick * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(tick * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(tick * 0.067F) * 0.05F;
        if (this.aimedBow) {
            float f7 = 0.0F;
            float f9 = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f7 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F - f7 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -1.570796F + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -1.570796F + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f7 * 1.2F - f9 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(tick * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(tick * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(tick * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(tick * 0.067F) * 0.05F;
        }
        Minecraft.theMinecraft.thePlayer.sendChatMessage("THIS IS RUNNING");
        syncLayerAngles(this.bipedLeftLeg, this.leftLegOverlay);
        syncLayerAngles(this.bipedRightLeg, this.rightLegOverlay);
        syncLayerAngles(this.bipedLeftArm, this.leftArmOverlay);
        syncLayerAngles(this.bipedRightArm, this.rightArmOverlay);
        syncLayerAngles(this.bipedBody, this.bodyOverlay);
    }
}

