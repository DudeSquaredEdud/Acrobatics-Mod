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
public class MixinPlayerModel extends ModelBase2 {
    @Shadow
    public Piece bipedBody;

    @Inject(method = "setRotationAngles", at = @At("HEAD"))
    public void setRotationAngles(float forward, float haltDelta, float tick, float yaw, float pitch, float unused, Entity entity, CallbackInfo ci){
        ModelBiped2 instance = (ModelBiped2) (Object) this;
        if (Minecraft.theMinecraft.thePlayer.isCollidedHorizontally){
            bipedBody.rotateAngleX += 0.9F;
            Minecraft.theMinecraft.thePlayer.sendChatMessage("WALL");
        }
    }

    @Override
    protected void init() {
    }
}
