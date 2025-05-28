package io.github.meowhalla.projectiles.state;

import io.github.meowhalla.projectiles.Strategy;

public class ProjectileState implements Strategy {
    private final float activateAfter;
    private final float destroyAfter;
    private final String chargingFrames;
    private final String destroyFrames;

    public ProjectileState(float activateAfter, float destroyAfter, String chargingFrames, String destroyFrames) {
        this.activateAfter = activateAfter;
        this.destroyAfter = destroyAfter;
        this.chargingFrames = chargingFrames;
        this.destroyFrames = destroyFrames;
    }

    public String chargingFramesFilename() {
        return chargingFrames;
    }

    public String destroyFramesFilename() {
        return destroyFrames;
    }

    public boolean isReady(float t) {
        return t >= activateAfter;
    }

    public boolean isDestroying(float t) {
        return t >= destroyAfter;
    }

    public boolean isDestroyed(float t) {
        float destroyAnimationDuration = 1f;
        return t >= destroyAfter + destroyAnimationDuration;
    }
}
