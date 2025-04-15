package io.github.meowhalla.contexts;

import io.github.meowhalla.graphics.BossGraphics;
import io.github.meowhalla.logic.BossLogic;
import io.github.meowhalla.physics.BossPhysics;
import io.github.meowhalla.states.BossState;

public class BossContext {
    public BossState state;
    public BossLogic logic;
    public BossPhysics physics;
    public BossGraphics graphics;

    public BossContext() {
        this.state = new BossState(0, 0 ,50, 30);
        this.logic = new BossLogic(this);
        this.physics = new BossPhysics(this);
        this.graphics = new BossGraphics(this);
    }
}
