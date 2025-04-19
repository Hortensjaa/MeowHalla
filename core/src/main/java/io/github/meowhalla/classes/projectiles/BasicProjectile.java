package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;

public class BasicProjectile extends ProjectileContext {
    private Vector2 velocity;
    private float gravity = 0f;

    public BasicProjectile(Vector2 origin, Vector2 vel, String fileName, int power, float radius) {
        super(origin, fileName, power, radius);
        this.velocity = vel;
    }

    public BasicProjectile(Vector2 origin, Vector2 vel, String fileName, int power, float radius, float g) {
        super(origin, fileName, power, radius);
        this.velocity = vel;
        this.gravity = g;
    }

    @Override
    public void update(float delta) {
        hitbox.x += velocity.x * delta;
        hitbox.y += velocity.y * delta;
        velocity.y -= gravity;
    }
}
