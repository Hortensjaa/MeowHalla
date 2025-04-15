package io.github.meowhalla.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.contexts.PlayerContext;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.ActionState;
import io.github.meowhalla.states.Direction;
import lombok.Getter;


public class PlayerPhysics {
    private final PlayerContext ctx;
    @Getter
    private final Vector2 velocity = new Vector2();
    private final float maxSpeed = 350f;
    private final float acceleration = 1500f;
    private final float friction = 600f;

    public PlayerPhysics(PlayerContext ctx) {
        this.ctx = ctx;
    }

    public void update(float delta) {
        Rectangle rect = ctx.state.getPosition();
        ActionState state = ctx.state.getActionState();

        float targetX = 0;

        if (state.getDirection() == Direction.LEFT && state.getAction() == Action.RUN) {
            targetX = -maxSpeed;
        } else if (state.getDirection() == Direction.RIGHT && state.getAction() == Action.RUN) {
            targetX = maxSpeed;
        }

        if (velocity.x < targetX) {
            velocity.x = Math.min(velocity.x + acceleration * delta, targetX);
        } else if (velocity.x > targetX) {
            velocity.x = Math.max(velocity.x - acceleration * delta, targetX);
        }

        if (targetX == 0) {
            if (velocity.x > 0) {
                velocity.x = Math.max(0, velocity.x - friction * delta);
            } else if (velocity.x < 0) {
                velocity.x = Math.min(0, velocity.x + friction * delta);
            }
        }

        rect.x += velocity.x * delta;

        float screenWidth = Gdx.graphics.getWidth();
        rect.x = MathUtils.clamp(rect.x, 0, screenWidth - rect.width);
    }
}

