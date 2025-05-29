package io.github.meowhalla.player;

import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.structure.states.Direction;

public class PlayerContext extends CharacterContext {
    public PlayerContext(Weapon startingWeapon) {
        super();
        activeWeapon = startingWeapon;
        state = new PlayerState(0, 0, 0, 0);
        state.setDirection(Direction.RIGHT);
        logic = new PlayerLogic(this, 100);
        physics = new PlayerPhysics(this);
        graphics = new PlayerGraphics(this, "player/player.png", 0.25f, 1, 2);
    }
}
