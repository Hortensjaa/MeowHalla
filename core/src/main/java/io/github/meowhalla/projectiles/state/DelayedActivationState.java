package io.github.meowhalla.projectiles.state;

public class DelayedActivationState extends ProjectileState {

    public DelayedActivationState(float activateAfter, String chargingFrames) {
        super(activateAfter, Float.POSITIVE_INFINITY, chargingFrames, "weapons/Splash.png");
    }

}
