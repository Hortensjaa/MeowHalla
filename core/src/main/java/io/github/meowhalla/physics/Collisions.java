package io.github.meowhalla.physics;

import io.github.meowhalla.classes.characters.CharacterContext;

public class Collisions {

    public void checkPlayerBoss(CharacterContext player, CharacterContext boss) {
        if (player.state.getPosition().overlaps(boss.state.getPosition())) {
            player.state.updateHp(-10);
        }
    }

}

