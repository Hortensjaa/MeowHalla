package io.github.meowhalla.projectiles.delay;

public class NoDelay implements DelayStrategy {

    @Override
    public String waitingFramesFilename() {
        return "weapons/Magic Sparks.png";
    }

    @Override
    public boolean isReady(float t) {
        return true;
    }
}
