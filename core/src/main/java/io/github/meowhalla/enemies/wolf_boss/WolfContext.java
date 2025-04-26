package io.github.meowhalla.enemies.wolf_boss;

import io.github.meowhalla.contexts.EnemyContext;
import io.github.meowhalla.logic.EnemyLogic;
import io.github.meowhalla.physics.EnemyPhysics;


public class WolfContext extends EnemyContext {

    public WolfContext() {
        super();
        float w = getGameContext().getViewport().getWorldWidth();
        name = "Wolf of Death";
        state = new io.github.meowhalla.states.EnemyState((int) (w - 380), 0, 0, 0);
        logic = new EnemyLogic(this, 2000, new WolfComboFactory(this));
        physics = new EnemyPhysics(this);
        graphics = new WolfGraphics(this, "bosses/wolf_of_death.png", 6f, 4, 7);
        activeWeapon = WolfWeapons.MAGIC_ORB_VOLLEY.data;
    }

}
