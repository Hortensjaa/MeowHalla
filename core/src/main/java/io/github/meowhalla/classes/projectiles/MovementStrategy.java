package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;

public interface MovementStrategy {
    Vector2 update(ProjectileContext context, float deltaTime);
}
