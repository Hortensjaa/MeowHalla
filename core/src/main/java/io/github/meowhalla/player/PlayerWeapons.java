package io.github.meowhalla.player;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.ProjectileConfig;
import io.github.meowhalla.projectiles.ProjectileFactoryBuilder;
import io.github.meowhalla.projectiles.movement.AcceleratedMovement;
import io.github.meowhalla.projectiles.movement.StraightMovement;
import io.github.meowhalla.projectiles.transformation.Rotation;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.projectiles.WeaponContext;

import java.util.List;


public enum PlayerWeapons {

    PHALANX_OF_LIGHT(
        new Weapon(
            new WeaponContext("PHALANX OF LIGHT",
                "Not very strong, but quite fast weapon; Sends 3 projectiles on different angles at time.",
                0.8f, 0.5f),

            List.of(
                new ProjectileFactoryBuilder()
                    .config(new ProjectileConfig(10, "weapons/Pure Bolt 2.png", 10, true))
                    .movement(() -> new StraightMovement(new Vector2(800, 0)))
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
    ),

    RAY_OF_SUN(
        new Weapon(
            new WeaponContext("RAY OF SUN",
                "Gives you an opportunity to continuously cause damage, with no cooldown.",
                0f, 0f),

            new ProjectileFactoryBuilder()
                .config(new ProjectileConfig(0.25f, "weapons/Black And White Ray.png", 5, true))
                .movement(() -> new StraightMovement(new Vector2(800, 0)))
                .build())
    ),

    LUNAR_ECLIPSE(
        new Weapon(
            new WeaponContext("LUNAR ECLIPSE",
                "Deals a lot of damage, but also takes a lot of energy to cast; needs long cooldown.",
                2f, 2f),

            new ProjectileFactoryBuilder()
                .config(new ProjectileConfig(50f, "weapons/Bolt Of Purity.png", 30, true))
                .movement(() -> new AcceleratedMovement(400, 0, -500, 0))
                .build())
    );

    public final Weapon data;

    PlayerWeapons(Weapon data) {
        this.data = data;
    }
}


