package com.tntimer.mixin;

import com.tntimer.TNTimerMod;
import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
public class TNTEntityMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void setCustomFuse(CallbackInfo ci) {
        TntEntity tntEntity = (TntEntity) (Object) this;

        if (tntEntity.getFuse() < 0) {
            tntEntity.setFuse(TNTimerMod.TntFuseTicks);
        }
        System.out.println("Custom TNT fuse set to: " + TNTimerMod.TntFuseTicks);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTntTick(CallbackInfo ci) {
        TntEntity tntEntity = (TntEntity) (Object) this;

        if (tntEntity.getFuse() > 0) {
            TNTimerMod.TntFuseTicks--;
        }

        if (TNTimerMod.TntFuseTicks < 0) {
            TNTimerMod.TntFuseTicks = TNTimerMod.globalTntFuseTicks;
            tntEntity.setFuse(TNTimerMod.TntFuseTicks);
        }

        System.out.println("TNT ticking, current fuse: " + tntEntity.getFuse());
    }

}
