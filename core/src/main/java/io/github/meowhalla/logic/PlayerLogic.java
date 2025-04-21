package io.github.meowhalla.logic;


import com.badlogic.gdx.Gdx;
import io.github.meowhalla.classes.characters.PlayerContext;
import io.github.meowhalla.data.KeyBindings;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;
import io.github.meowhalla.states.PlayerState;

import java.util.List;

public class PlayerLogic extends CharacterLogic {

    public PlayerLogic(PlayerContext ctx, int max_hp) {
        super(ctx, max_hp);
    }

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
            ctx.state.setAction(Action.IDLE);
        } else {
            ctx.state.setAction(Action.FALL);
        }
        timeSinceLastShot += delta;
        ((PlayerState) ctx.state).increaseTime(delta);
    }

    public List<ProjectileContext> shoot() {
        if (timeSinceLastShot > ctx.activeWeapon.getWeaponContext().cooldown()) {
            timeSinceLastShot = 0f;
            return ctx.activeWeapon.generateProjectiles(ctx);
        }
        return null;
    }
}

