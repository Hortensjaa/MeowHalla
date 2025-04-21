package io.github.meowhalla.contexts;

import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.graphics.PlayerGraphics;
import io.github.meowhalla.player.PlayerLogic;
import io.github.meowhalla.physics.PlayerPhysics;
import io.github.meowhalla.states.Direction;
import io.github.meowhalla.states.PlayerState;

import java.util.List;

public class PlayerContext extends CharacterContext {
    public List<Weapon> weapons;

    public PlayerContext(GameContext gameContext) {
        super(gameContext);
        state = new PlayerState(0, 0, 0, 0);
        state.setDirection(Direction.RIGHT);
        logic = new PlayerLogic(this, 100);
        physics = new PlayerPhysics(this);
        graphics = new PlayerGraphics(this, "player/player.png", 0.25f, 1, 2);
        activeWeapon = WeaponType.PHALANX_OF_LIGHT.data;
        weapons = List.of(WeaponType.PHALANX_OF_LIGHT.data);
    }

}
