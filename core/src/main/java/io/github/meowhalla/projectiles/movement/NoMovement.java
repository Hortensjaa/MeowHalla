package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.structure.DynamicObject;

public class NoMovement implements MovementStrategy {
    @Override
    public Vector2 update(DynamicObject p, float delta) {
        return new Vector2(0, 0);
    }
}
