package io.github.meowhalla.physics;

import io.github.meowhalla.contexts.BossContext;
import io.github.meowhalla.contexts.PlayerContext;

public class Collisions {

    public void checkPlayerBoss(PlayerContext player, BossContext boss) {
        if (player.state.getPosition().overlaps(boss.state.getPosition())) {
            player.state.updateHp(-10);
        }
    }

}

