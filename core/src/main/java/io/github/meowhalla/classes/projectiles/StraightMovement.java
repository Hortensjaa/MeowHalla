package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;

public class StraightMovement implements MovementStrategy {
    private Vector2 velocity;

    public StraightMovement(Vector2 velocity) {
        this.velocity = velocity.cpy();
    }

    @Override
    public Vector2 update(ProjectileContext p, float delta) {
        return new Vector2(velocity.x * delta, velocity.y * delta);
    }
}

