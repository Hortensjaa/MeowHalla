package io.github.meowhalla.projectiles.base_transformation;

import com.badlogic.gdx.math.Vector2;

public class Translation implements BaseTransformationStrategy {
    Vector2 translation;

    public Translation(float dx, float dy) {
        this.translation = new Vector2(dx, dy);
    }

    @Override
    public Vector2 apply(Vector2 v) {
        return v.cpy().add(translation);
    }
}
