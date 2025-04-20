package io.github.meowhalla.projectiles.transformation;

import com.badlogic.gdx.math.Vector2;

public class Translation implements TransformationStrategy {
    Vector2 translation;

    public Translation(float dx, float dy) {
        this.translation = new Vector2(dx, dy);
    }

    @Override
    public Vector2 applyStart(Vector2 v) {
        return v.cpy().add(translation);
    }

    @Override
    public Vector2 applyLoop(Vector2 v) {
        return v;
    }
}
