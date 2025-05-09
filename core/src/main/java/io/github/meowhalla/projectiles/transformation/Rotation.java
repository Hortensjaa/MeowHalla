package io.github.meowhalla.projectiles.transformation;

import com.badlogic.gdx.math.Vector2;

public class Rotation implements TransformationStrategy {
    private final float angle;

    public Rotation(float angle) {
        this.angle = angle;
    }

    @Override
    public Vector2 apply(Vector2 v) {
        return new Vector2(v).rotateDeg(angle);
    }

}
