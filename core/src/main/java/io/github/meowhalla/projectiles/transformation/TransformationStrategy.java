package io.github.meowhalla.projectiles.transformation;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.Strategy;

public interface TransformationStrategy extends Strategy {
    Vector2 apply(Vector2 v);
}
