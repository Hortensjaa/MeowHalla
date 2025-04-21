package io.github.meowhalla.projectiles;

public record WeaponContext(
    String name,
    float cooldown,
    float chargeTime
){}
