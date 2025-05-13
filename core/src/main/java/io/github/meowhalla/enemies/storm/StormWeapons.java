package io.github.meowhalla.enemies.storm;


import io.github.meowhalla.projectiles.ProjectileConfig;
import io.github.meowhalla.projectiles.ProjectileDataBuilder;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.projectiles.WeaponContext;
import io.github.meowhalla.projectiles.movement.AcceleratedMovement;

public enum StormWeapons {

    HEAVY_RAIN(
        new Weapon(
            new WeaponContext("Heavy Rain", 0.25f, 2f),
            new ProjectileDataBuilder()
                .config(new ProjectileConfig(25, "weapons/Water Orb.png", 5, false))
                .movement(() -> new AcceleratedMovement(0, -400, 1000))
                .build())
    );

    public final Weapon data;

    StormWeapons(Weapon data) {
        this.data = data;
    }

}

