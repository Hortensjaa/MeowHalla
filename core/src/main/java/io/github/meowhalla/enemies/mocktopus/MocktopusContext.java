package io.github.meowhalla.enemies.mocktopus;

import io.github.meowhalla.enemies.EnemyContext;
import io.github.meowhalla.enemies.EnemyLogic;
import io.github.meowhalla.enemies.EnemyState;

public class MocktopusContext extends EnemyContext {

    public MocktopusContext() {
        super();
        float w = getGameContext().getViewport().getWorldWidth();
        float h = getGameContext().getViewport().getWorldHeight();
        name = "Mocktopus";
        state = new EnemyState( w / 2 - 100, h / 2 - 200, 0, 0);
        logic = new EnemyLogic(this, 2000, new MocktopusComboFactory(this));
        physics = new MocktopusPhysics(this);
        graphics = new MocktopusGraphics(this, "bosses/mocktopus/octopus.png", 3f, 1, 4);
        activeWeapon = MocktopusWeapons.CHASING_THE_SUN.data;
    }

}
