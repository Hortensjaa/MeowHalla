package io.github.meowhalla.classes.characters;

import io.github.meowhalla.classes.GameContext;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.graphics.WolfBossGraphics;
import io.github.meowhalla.logic.BossLogic;
import io.github.meowhalla.logic.wolf_boss.WolfComboFactory;
import io.github.meowhalla.physics.BossPhysics;
import io.github.meowhalla.states.CharacterState;


public class WolfBossContext extends CharacterContext {

    public WolfBossContext(GameContext gameContext) {
        super(gameContext);
        float w = getGameContext().getViewport().getWorldWidth();
        name = "Wolf of Death";
        state = new CharacterState((int) (w - 380), 0, 0, 0);
        logic = new BossLogic(this, 2000, new WolfComboFactory(this));
        physics = new BossPhysics(this);
        graphics = new WolfBossGraphics(this, "bosses/wolf_of_death.png", 6f, 4, 7);
        activeWeapon = WeaponType.MAGIC_ORB_VOLLEY.data;
    }

}
