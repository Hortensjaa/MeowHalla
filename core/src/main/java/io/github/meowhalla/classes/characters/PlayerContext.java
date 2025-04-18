package io.github.meowhalla.classes.characters;

import io.github.meowhalla.classes.GameContext;
import io.github.meowhalla.classes.weapons.WeaponContext;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.graphics.PlayerGraphics;
import io.github.meowhalla.logic.PlayerLogic;
import io.github.meowhalla.physics.PlayerPhysics;

public class PlayerContext extends CharacterContext {
    public final WeaponContext weapon;

    public PlayerContext(GameContext gameContext) {
        super(gameContext);
        logic = new PlayerLogic(this);
        physics = new PlayerPhysics(this);
        graphics = new PlayerGraphics(this, "player/player.png", 0.25f);
        weapon = WeaponType.SHURIKENS_OF_LIGHT.data;
    }
}
