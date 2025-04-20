package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.meowhalla.projectiles.ProjectileContext;

import java.util.List;

public class WindingMovement implements MovementStrategy {
    private final List<Vector3> vectors;
    private int currentId = 0;
    private float timeSinceChange = 0f;

    public WindingMovement(List<Vector3> vectors) {
        this.vectors = vectors;
    }

    @Override
    public Vector2 update(ProjectileContext p, float delta) {
        timeSinceChange += delta;
        if (timeSinceChange > vectors.get(currentId).z) {
            currentId = (currentId + 1) % vectors.size();
            timeSinceChange = 0f;
        }

        Vector3 vec = vectors.get(currentId);
        return new Vector2(vec.x * delta, vec.y * delta);
    }
}

