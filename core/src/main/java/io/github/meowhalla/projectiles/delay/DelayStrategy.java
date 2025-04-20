package io.github.meowhalla.projectiles.delay;

public interface DelayStrategy {
    String waitingFramesFilename();
    boolean isReady(float timeSinceSpawn);
}
