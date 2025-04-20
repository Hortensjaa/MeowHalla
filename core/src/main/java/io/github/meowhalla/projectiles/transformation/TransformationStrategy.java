package io.github.meowhalla.projectiles.transformation;

import com.badlogic.gdx.math.Vector2;

public interface TransformationStrategy {
    Vector2 applyStart(Vector2 v);
    Vector2 applyLoop(Vector2 v);
}
