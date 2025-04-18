package io.github.meowhalla.logic;


import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Action;

public class WolfBossLogic implements CharacterLogic {
    private CharacterContext ctx;

    public WolfBossLogic(CharacterContext ctx) {
        this.ctx = ctx;
    }

    public void update(float delta) {
        double value = Math.random();
        if (value < 0.2) {
            ctx.state.setAction(Action.ATTACK);
        }
    }

}
