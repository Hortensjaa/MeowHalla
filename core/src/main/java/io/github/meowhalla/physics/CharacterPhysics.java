package io.github.meowhalla.physics;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.contexts.CharacterContext;
import io.github.meowhalla.states.Direction;
import lombok.Getter;

public abstract class CharacterPhysics {
    protected CharacterContext ctx;
    @Getter protected Vector2 velocity = new Vector2();
    protected float maxSpeed = 500f;
    protected float jumpStrength = 900f;
    protected float gravity = 2000f;
    protected boolean canFloat = false;
    @Getter protected boolean isGrounded = true;

    abstract public void update(float delta);

    public void moveLeft() {
        velocity.x = -maxSpeed;
        ctx.state.setDirection(Direction.LEFT);
    }

    public void moveRight() {
        velocity.x = maxSpeed;
        ctx.state.setDirection(Direction.RIGHT);
    }

    public void jump() {
        if (isGrounded && !canFloat) {
            velocity.y = jumpStrength;
            isGrounded = false;
        }
    }
}
