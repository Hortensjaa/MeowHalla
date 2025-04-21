package io.github.meowhalla.projectiles.base_transformation;

import com.badlogic.gdx.math.Vector2;

public class Translation implements BaseTransformationStrategy {
    Vector2 translation;

    public Translation(float dx, float dy) {
        this.translation = new Vector2(dx, dy);
    }

    public void setX(float x) {
        this.translation.set(x, translation.y);
    }

    public void setY(float y) {
        this.translation.set(translation.x, y);
    }

    @Override
    public Vector2 apply(Vector2 v) {
        return v.cpy().add(translation);
    }
}
