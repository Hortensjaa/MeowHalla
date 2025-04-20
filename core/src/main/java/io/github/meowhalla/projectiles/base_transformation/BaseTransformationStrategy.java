package io.github.meowhalla.projectiles.base_transformation;

import com.badlogic.gdx.math.Vector2;

public interface BaseTransformationStrategy {
    Vector2 apply(Vector2 v);
}
