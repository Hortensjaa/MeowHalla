package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.Strategy;

public interface MovementStrategy extends Strategy {
    Vector2 update(ProjectileContext context, float deltaTime);
}
