package io.github.meowhalla.enemies.storm;

import io.github.meowhalla.enemies.EnemyContext;
import io.github.meowhalla.game.ViewportUtils;
import io.github.meowhalla.enemies.EnemyLogic;
import io.github.meowhalla.enemies.EnemyState;

public class StormContext extends EnemyContext {

    public StormContext() {
        super();
        name = "Stormy Eyes";
        state = new EnemyState(ViewportUtils.centerX(), 500, 0, 0);
        logic = new EnemyLogic(this, 100000, new StormComboFactory(this));
        physics = new StormPhysics(this);
        graphics = new StormGraphics(this, "bosses/cloud/merged.png", 2f, 3, 1);
        activeWeapon = StormWeapons.HEAVY_RAIN.data;
    }

}
