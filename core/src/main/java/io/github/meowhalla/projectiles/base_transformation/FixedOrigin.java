package io.github.meowhalla.projectiles.base_transformation;

import com.badlogic.gdx.math.Vector2;

public class FixedOrigin implements BaseTransformationStrategy {
    Vector2 origin;

    public FixedOrigin(float dx, float dy) {
        this.origin = new Vector2(dx, dy);
    }

    public void setX(float x) {
        this.origin.set(x, origin.y);
    }

    public void setY(float y) {
        this.origin.set(origin.x, y);
    }
    @Override
    public Vector2 apply(Vector2 v) {
        return origin;
    }
}
