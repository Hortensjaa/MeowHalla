package io.github.meowhalla.projectiles.weapons;

public record WeaponContext(
    String name,
    float cooldown,
    float chargeTime
){}
