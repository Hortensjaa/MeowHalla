package io.github.meowhalla.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.PlayerContext;
import io.github.meowhalla.data.KeyBindings;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;
import lombok.Getter;


public class PlayerPhysics {
    private final PlayerContext ctx;
    @Getter private final Vector2 velocity = new Vector2();
    private final float maxSpeed = 500f;
    private final float jumpStrength = 900f;
    private final float gravity = 2000f;
    @Getter private boolean isGrounded = true;

    public PlayerPhysics(PlayerContext ctx) {
        this.ctx = ctx;
    }

    public void moveLeft() {
        velocity.x = -maxSpeed;
        ctx.state.getActionState().setDirection(Direction.LEFT);
    }

    public void moveRight() {
        velocity.x = maxSpeed;
        ctx.state.getActionState().setDirection(Direction.RIGHT);
    }


    public void jump() {
        if (isGrounded) {
            velocity.y = jumpStrength;
            isGrounded = false;
        }
    }

    public void update(float delta) {
        float worldWidth = ctx.getGameContext().getViewport().getWorldWidth();
        Rectangle rect = ctx.state.getPosition();

        if (!Gdx.input.isKeyPressed(KeyBindings.LEFT.getKeyCode()) &&
            !Gdx.input.isKeyPressed(KeyBindings.RIGHT.getKeyCode())) {
            velocity.x = 0;
        }

        velocity.y -= gravity * delta;

        rect.y += velocity.y * delta;
        rect.x += velocity.x * delta;

        if (rect.y <= 0) {
            rect.y = 0;
            velocity.y = 0;
            isGrounded = true;
        } else {
            isGrounded = false;
        }

        if (velocity.y < 0) {
            ctx.state.getActionState().setAction(Action.FALL);
        }

        if (rect.x < 0) rect.x = 0;
        if (rect.x + rect.width > worldWidth) {
            rect.x = worldWidth - rect.width;
        }
    }
}


