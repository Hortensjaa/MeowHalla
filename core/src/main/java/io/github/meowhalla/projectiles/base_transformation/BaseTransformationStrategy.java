package io.github.meowhalla.projectiles.base_transformation;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.Strategy;

public interface BaseTransformationStrategy extends Strategy {
    Vector2 apply(Vector2 v);
}
