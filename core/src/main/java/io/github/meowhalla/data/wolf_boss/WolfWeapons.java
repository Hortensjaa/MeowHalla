package io.github.meowhalla.data.wolf_boss;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.meowhalla.projectiles.ProjectileConfig;
import io.github.meowhalla.projectiles.ProjectileFactoryBuilder;
import io.github.meowhalla.projectiles.base_transformation.FixedOrigin;
import io.github.meowhalla.projectiles.delay.TimedDelay;
import io.github.meowhalla.projectiles.movement.StraightMovement;
import io.github.meowhalla.projectiles.movement.WindingMovement;
import io.github.meowhalla.projectiles.transformation.Rotation;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.projectiles.WeaponContext;

import java.util.List;

public enum WolfWeapons {
    MAGIC_ORB_VOLLEY(
        new Weapon(
            new WeaponContext("Magic Orb Volley", 1f, 3f),
            new ProjectileFactoryBuilder()
                .config(new ProjectileConfig(30, "weapons/Magic Orb.png", 25, false))
                .movement(() -> new StraightMovement(new Vector2(700, 0)))
                .baseTransformation(() -> new FixedOrigin(25, 200))
                .delay(() -> new TimedDelay(1f, "weapons/Magic Sparks.png"))
                .build()
        )
    ),

    FAN_OF_ORBS(
        new Weapon(
            new WeaponContext("FAN OF ORBS", 1f, 3f),
            new ProjectileFactoryBuilder()
                .config(new ProjectileConfig(30, "weapons/Magic Orb.png", 25, true))
                .movement(() -> new StraightMovement(new Vector2(600, 0)))
                .build(),
            List.of(
                new Rotation(0f),
                new Rotation(-15f),
                new Rotation(30f),
                new Rotation(60f)
            ))
        ),

    ECLIPSE(
        new Weapon(
            new WeaponContext("ECLIPSE", 2f, 2f),
            new ProjectileFactoryBuilder()
                .config(new ProjectileConfig(60, "weapons/Magic Orb.png", 50, true))
                .movement(() -> new StraightMovement(new Vector2(400, 0)))
                .build())
    ),

    ZIGZAG(
        new Weapon(
            new WeaponContext("ZIGZAG ORB", 0.6f, 2f),
            new ProjectileFactoryBuilder()
                .config(new ProjectileConfig(10, "weapons/Magic Orb.png", 15, false))
                .movement(() -> new WindingMovement(List.of(
                    new Vector3(-200, 400, 1f),
                    new Vector3(-200, -400, 1f)
                )))
                .build())
    ),

    REVERSED_ZIGZAG(
        ZIGZAG.data
            .transform(() -> new FixedOrigin(25, 600))
            .transform(() -> new WindingMovement(List.of(
                new Vector3(200, -800, 0.75f),
                new Vector3(200, 800, 0.75f)
            )))
    );

    public final Weapon data;

    WolfWeapons(Weapon data) {
        this.data = data;
    }

}
