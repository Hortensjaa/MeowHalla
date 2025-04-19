package io.github.meowhalla.classes.weapons;

import io.github.meowhalla.classes.projectiles.ProjectileFactory;


public record Weapon(WeaponContext weaponContext, ProjectileFactory projectileFactory) {
}
