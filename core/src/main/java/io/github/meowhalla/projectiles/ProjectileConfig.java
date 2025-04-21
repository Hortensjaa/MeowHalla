package io.github.meowhalla.projectiles;

public record ProjectileConfig (
    float power,
    String fileName,
    float radius,
    boolean rotateWithPlayer
){}

