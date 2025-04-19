package io.github.meowhalla.classes.projectiles;

public class NoDelay implements DelayStrategy {
    public boolean isReady(float t) {
        return true;
    }
}
