package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.ProjectileConfig;
import io.github.meowhalla.projectiles.ProjectileFactoryBuilder;
import io.github.meowhalla.projectiles.movement.StraightMovement;
import io.github.meowhalla.projectiles.transformation.Rotation;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.projectiles.WeaponContext;

import java.util.List;


public enum WeaponType {

    PHALANX_OF_LIGHT(
        new Weapon(
            new WeaponContext("PHALANX OF LIGHT", 1f, 3f),
            List.of(
                new ProjectileFactoryBuilder()
                    .config(new ProjectileConfig(10, "weapons/Pure Bolt 2.png", 10, true))
                    .movement(() -> new StraightMovement(new Vector2(700, 0)))
                    .build(),

                new ProjectileFactoryBuilder()
                    .config(new ProjectileConfig(5, "weapons/Pure Bolt 2.png", 7, true))
                    .movement(() -> new StraightMovement(new Vector2(600, 0)))
                    .transformation(() -> new Rotation(30f))
                    .build(),

                new ProjectileFactoryBuilder()
                    .config(new ProjectileConfig(5, "weapons/Pure Bolt 2.png", 7, true))
                    .movement(() -> new StraightMovement(new Vector2(600, 0)))
                    .transformation(() -> new Rotation(-30f))
                    .build()
            )
        )
    );

    public final Weapon data;

    WeaponType(Weapon data) {
        this.data = data;
    }
}


