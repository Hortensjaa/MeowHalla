package io.github.meowhalla.projectiles;



public record WeaponContext(
    String name,
    String description,
    float cooldown,
    float chargeTime
){
    public WeaponContext(String name, float cooldown, float chargeTime) {
        this(name, "description", cooldown, chargeTime);
    }

    public WeaponContext(String name, float cooldown) {
        this(name, "description", cooldown, 0);
    }
}
