package io.github.meowhalla.projectiles.delay;

public class NoDelay implements DelayStrategy {
    public boolean isReady(float t) {
        return true;
    }
}
