package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.ProjectileContext;

public class StraightMovement implements MovementStrategy {
    private final Vector2 velocity;

    public StraightMovement(Vector2 velocity) {
        this.velocity = velocity.cpy();
    }

    @Override
    public Vector2 update(ProjectileContext p, float delta) {
        return new Vector2(velocity.x * delta, velocity.y * delta);
    }
}

