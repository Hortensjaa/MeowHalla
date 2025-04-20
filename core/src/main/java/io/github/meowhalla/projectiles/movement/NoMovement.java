package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.ProjectileContext;

public class NoMovement implements MovementStrategy {
    @Override
    public Vector2 update(ProjectileContext p, float delta) {
        return new Vector2(0, 0);
    }
}
