package io.github.meowhalla.projectiles.delay;

import io.github.meowhalla.projectiles.Strategy;

public interface DelayStrategy extends Strategy {
    String waitingFramesFilename();
    boolean isReady(float timeSinceSpawn);
}
