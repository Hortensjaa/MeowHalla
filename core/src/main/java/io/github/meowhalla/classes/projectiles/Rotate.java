package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;

public class Rotate implements TransformationStrategy {
    private float angle;

    public Rotate(float angle) {
        this.angle = angle;
    }

    @Override
    public Vector2 apply(Vector2 v) {
        float speed = v.len();
        return new Vector2(speed, 0).setAngleDeg(angle);
    }
}
