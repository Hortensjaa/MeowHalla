package io.github.meowhalla.classes;

import com.badlogic.gdx.math.Vector2;


public record WeaponContext(
    String name,
    float power,
    String fileName,
    float cooldown,
    Vector2 velocity,
    float radius
) {
    ProjectileContext createProjectile() {
        return new ProjectileContext(new Vector2(0, 0), velocity, fileName, power, radius);
    }
}
