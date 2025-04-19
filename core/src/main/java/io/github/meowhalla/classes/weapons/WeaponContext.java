package io.github.meowhalla.classes.weapons;

public record WeaponContext(
    String name,
    float cooldown,
    float chargeTime
){}
