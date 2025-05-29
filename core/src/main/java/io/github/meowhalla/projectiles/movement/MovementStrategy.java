package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.Strategy;
import io.github.meowhalla.structure.DynamicObject;

public interface MovementStrategy extends Strategy {
    Vector2 update(DynamicObject context, float deltaTime);
}
