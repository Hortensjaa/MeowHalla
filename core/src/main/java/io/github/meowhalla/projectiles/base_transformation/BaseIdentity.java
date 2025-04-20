package io.github.meowhalla.projectiles.base_transformation;

import com.badlogic.gdx.math.Vector2;

public class BaseIdentity implements BaseTransformationStrategy {
    @Override
    public Vector2 apply(Vector2 v) {
        return v;
    }
}
