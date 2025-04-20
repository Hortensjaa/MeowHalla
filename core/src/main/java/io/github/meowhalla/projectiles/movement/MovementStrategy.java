package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.ProjectileContext;

public interface MovementStrategy {
    Vector2 update(ProjectileContext context, float deltaTime);
}
