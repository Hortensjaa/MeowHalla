package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;

public class Identity implements TransformationStrategy {

    @Override
    public Vector2 apply(Vector2 v) {
        return v;
    }
}
