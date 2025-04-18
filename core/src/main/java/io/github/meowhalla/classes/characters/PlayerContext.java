package io.github.meowhalla.classes.characters;

import io.github.meowhalla.classes.GameContext;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.graphics.PlayerGraphics;
import io.github.meowhalla.logic.PlayerLogic;
import io.github.meowhalla.physics.PlayerPhysics;
import io.github.meowhalla.states.CharacterState;
import io.github.meowhalla.states.Direction;

public class PlayerContext extends CharacterContext {

    public PlayerContext(GameContext gameContext) {
        super(gameContext);
        state = new CharacterState(0, 0, 0, 0, 100);
        state.setDirection(Direction.RIGHT);
        logic = new PlayerLogic(this);
        physics = new PlayerPhysics(this);
        graphics = new PlayerGraphics(this, "player/player.png", 0.25f);
        weapon = WeaponType.SHURIKENS_OF_LIGHT.data;
    }
}
