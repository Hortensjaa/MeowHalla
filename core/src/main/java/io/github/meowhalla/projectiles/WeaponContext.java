package io.github.meowhalla.projectiles;



public record WeaponContext(
    String name,
    String description,
    float cooldown
){
    public WeaponContext(String name, float cooldown) {
        this(name, "description", cooldown);
    }
}
