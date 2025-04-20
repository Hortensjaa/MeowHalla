package io.github.meowhalla.projectiles.transformation;

import com.badlogic.gdx.math.Vector2;

public class Identity implements TransformationStrategy {

    @Override
    public Vector2 applyStart(Vector2 v) {
        return v;
    }

    @Override
    public Vector2 applyLoop(Vector2 v) {
        return v;
    }
}
