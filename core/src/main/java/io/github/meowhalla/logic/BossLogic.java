package io.github.meowhalla.logic;


import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Action;

public class BossLogic implements CharacterLogic {
    private CharacterContext ctx;
    private float lastShoot = 0;

    public BossLogic(CharacterContext ctx) {
        this.ctx = ctx;
    }

    public void update(float delta) {
        lastShoot += delta;
        if (lastShoot > 1) {
            ctx.state.setAction(Action.ATTACK);
        }
    }

}
