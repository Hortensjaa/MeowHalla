package io.github.meowhalla.projectiles.delay;

public interface DelayStrategy {
    boolean isReady(float timeSinceSpawn);
}
