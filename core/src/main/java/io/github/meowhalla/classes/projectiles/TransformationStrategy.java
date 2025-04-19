package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;

public interface TransformationStrategy {
    public Vector2 apply(Vector2 v);
}
