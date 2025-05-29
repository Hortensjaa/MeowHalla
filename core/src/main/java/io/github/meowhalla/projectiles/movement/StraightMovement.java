package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.structure.DynamicObject;

public class StraightMovement implements MovementStrategy {
    private final Vector2 velocity;

    public StraightMovement(Vector2 velocity) {
        this.velocity = velocity.cpy();
    }

    public StraightMovement(float dx, float dy) {
        this.velocity = new Vector2(dx, dy);
    }

    @Override
    public Vector2 update(DynamicObject p, float delta) {
        return new Vector2(velocity.x * delta, velocity.y * delta);
    }
}

