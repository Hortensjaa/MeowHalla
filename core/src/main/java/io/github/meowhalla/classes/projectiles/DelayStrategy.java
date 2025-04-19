package io.github.meowhalla.classes.projectiles;

public interface DelayStrategy {
    boolean isReady(float timeSinceSpawn);
}
