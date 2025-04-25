package io.github.meowhalla.contexts;

import io.github.meowhalla.graphics.PlayerGraphics;
import io.github.meowhalla.physics.PlayerPhysics;
import io.github.meowhalla.player.PlayerLogic;
import io.github.meowhalla.player.PlayerWeapons;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.states.Direction;
import io.github.meowhalla.states.PlayerState;

import java.util.List;

public class PlayerContext extends CharacterContext {
    public List<Weapon> weapons;

    public PlayerContext(Weapon startingWeapon) {
        super();
        state = new PlayerState(0, 0, 0, 0);
        state.setDirection(Direction.RIGHT);
        logic = new PlayerLogic(this, 100);
        physics = new PlayerPhysics(this);
        graphics = new PlayerGraphics(this, "player/player.png", 0.25f, 1, 2);
        activeWeapon = startingWeapon;
        weapons = List.of(PlayerWeapons.PHALANX_OF_LIGHT.data);
    }

}
