package io.github.meowhalla.classes.weapons;

import com.badlogic.gdx.math.Vector2;


public record WeaponContext(
    String name,
    int power,
    String fileName,
    float cooldown,
    Vector2 velocity,
    float radius,
    float chargeTime,
    WeaponBehavior behavior
){}
