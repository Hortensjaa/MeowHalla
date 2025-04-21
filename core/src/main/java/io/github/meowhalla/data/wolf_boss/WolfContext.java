package io.github.meowhalla.data.wolf_boss;

import io.github.meowhalla.contexts.BossContext;
import io.github.meowhalla.contexts.GameContext;
import io.github.meowhalla.logic.BossLogic;
import io.github.meowhalla.physics.BossPhysics;
import io.github.meowhalla.states.BossState;


public class WolfContext extends BossContext {

    public WolfContext(GameContext gameContext) {
        super(gameContext);
        float w = getGameContext().getViewport().getWorldWidth();
        name = "Wolf of Death";
        state = new BossState((int) (w - 380), 0, 0, 0);
        logic = new BossLogic(this, 2000, new WolfComboFactory(this));
        physics = new BossPhysics(this);
        graphics = new WolfGraphics(this, "bosses/wolf_of_death.png", 6f, 4, 7);
        activeWeapon = WolfWeapons.MAGIC_ORB_VOLLEY.data;
    }

}
