package io.github.meowhalla.classes.weapons;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.projectiles.ProjectileContext;


public record WeaponContext(
    String name,
    float power,
    String fileName,
    float cooldown,
    Vector2 velocity,
    float radius,
    WeaponBehavior behavior
) {
    public ProjectileContext createProjectile() {
        return new ProjectileContext(new Vector2(0, 0), velocity, fileName, power, radius);
    }
}
