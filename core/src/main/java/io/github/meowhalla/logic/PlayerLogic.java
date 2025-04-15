package io.github.meowhalla.logic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.meowhalla.contexts.PlayerContext;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;

public record PlayerLogic(PlayerContext ctx) {

    public void update() {
        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        if (left && !right) {
            ctx.state.setAction(Action.RUN);
            ctx.state.setDirection(Direction.LEFT);
        } else if (right && !left) {
            ctx.state.setAction(Action.RUN);
            ctx.state.setDirection(Direction.RIGHT);
        } else {
            ctx.state.setAction(Action.WAIT);
        }
    }

}

