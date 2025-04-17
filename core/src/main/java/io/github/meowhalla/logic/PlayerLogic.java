package io.github.meowhalla.logic;


import com.badlogic.gdx.Gdx;
import io.github.meowhalla.classes.PlayerContext;
import io.github.meowhalla.data.KeyBindings;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;

public record PlayerLogic(PlayerContext ctx) {

    public void update(float delta) {
        boolean left = Gdx.input.isKeyPressed(KeyBindings.LEFT.getKeyCode());
        boolean right = Gdx.input.isKeyPressed(KeyBindings.RIGHT.getKeyCode());

        if (Gdx.input.isKeyPressed(KeyBindings.ATTACK.getKeyCode())) {
            ctx.state.setAction(Action.ATTACK);
        } else if (Gdx.input.isKeyPressed(KeyBindings.JUMP.getKeyCode())) {
            ctx.state.setAction(Action.JUMP);
            ctx.physics.jump();
        } else if (left && !right) {
            if (ctx.physics.isGrounded()) {
                ctx.state.setAction(Action.RUN);
            }
            ctx.state.setDirection(Direction.LEFT);
            ctx.physics.moveLeft();
        } else if (ctx.state.getAction() != Action.JUMP && right && !left) {
            if (ctx.physics.isGrounded()) {
                ctx.state.setAction(Action.RUN);
            }
            ctx.state.setDirection(Direction.RIGHT);
            ctx.physics.moveRight();
        } else if (ctx.physics.isGrounded()) {
            ctx.state.setAction(Action.WAIT);
        }
    }

}

