package io.github.meowhalla.projectiles.transformation;

import com.badlogic.gdx.math.Vector2;

public interface TransformationStrategy {
    Vector2 apply(Vector2 v);
}
