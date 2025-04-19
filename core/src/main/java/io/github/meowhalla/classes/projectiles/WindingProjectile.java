package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

public class WindingProjectile extends ProjectileContext {
    private List<Vector3> vectors;  // <velocitity.x, velocity.y, time>
    int currentId = 0;
    float timeSinceChange = 0f;

    public WindingProjectile(Vector2 position, List<Vector3> vectors, String fileName, int power, float radius) {
        super(position, fileName, power, radius);
        this.vectors = vectors;
    }

    @Override
    public void update(float delta) {
        if (timeSinceChange > vectors.get(currentId).z) {
            currentId++;
            currentId %= vectors.size();
            timeSinceChange = 0f;
        }
        hitbox.x += vectors.get(currentId).x * delta;
        hitbox.y += vectors.get(currentId).y * delta;
        timeSinceChange += delta;
    }
}
