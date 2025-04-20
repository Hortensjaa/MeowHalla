package io.github.meowhalla.projectiles;

public record ProjectileConfig (
    int power,
    String fileName,
    float radius,
    boolean rotateWithPlayer
){}

