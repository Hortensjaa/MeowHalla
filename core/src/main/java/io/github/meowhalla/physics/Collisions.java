package io.github.meowhalla.physics;

import io.github.meowhalla.classes.BossContext;
import io.github.meowhalla.classes.PlayerContext;

public class Collisions {

    public void checkPlayerBoss(PlayerContext player, BossContext boss) {
        if (player.state.getPosition().overlaps(boss.state.getPosition())) {
            player.state.updateHp(-10);
        }
    }

}

