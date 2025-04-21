package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.ProjectileContext;

public class AcceleratedMovement implements MovementStrategy {
    private final Vector2 velocity;
    private final Vector2 acceleration;

    public AcceleratedMovement(float dx, float dy, float g) {
        this.velocity = new Vector2(dx, dy);
        this.acceleration = new Vector2(0, g);
    }

    public AcceleratedMovement(float dx, float dy, float ax, float ay) {
        this.velocity = new Vector2(dx, dy);
        this.acceleration = new Vector2(ax, ay);
    }

    @Override
    public Vector2 update(ProjectileContext p, float delta) {
        velocity.x -= acceleration.x * delta;
        velocity.y -= acceleration.y * delta;
        return new Vector2(velocity.x * delta, velocity.y * delta);
    }

}
