package io.github.meowhalla.states;

import lombok.Getter;

public class PlayerState extends CharacterState {
    final float invincibilityTime = 3f;
    @Getter float timeSinceLastHit;

    public PlayerState(int x, int y, int width, int height) {
        super(x, y, width, height);
        timeSinceLastHit = invincibilityTime;
    }

    public void increaseTime(float delta) {
        timeSinceLastHit += delta;
    }

    public void resetNoHitTime() {
        timeSinceLastHit = 0;
    }

    @Override
    public boolean isInvincible() {
        return timeSinceLastHit < invincibilityTime;
    }
}
