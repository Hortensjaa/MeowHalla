package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.meowhalla.classes.weapons.Weapon;
import io.github.meowhalla.classes.weapons.WeaponContext;
import io.github.meowhalla.projectiles.ProjectileConfig;
import io.github.meowhalla.projectiles.ProjectileFactory;
import io.github.meowhalla.projectiles.delay.NoDelay;
import io.github.meowhalla.projectiles.movement.StraightMovement;
import io.github.meowhalla.projectiles.movement.WindingMovement;
import io.github.meowhalla.projectiles.transformation.Identity;
import io.github.meowhalla.projectiles.transformation.Rotation;

import java.util.List;


public enum WeaponType {
    MAGIC_ORB_VOLLEY(
        new Weapon(
            new WeaponContext("Magic Orb Volley", 0.5f, 3f),
            new ProjectileFactory(
                new ProjectileConfig(30, "weapons/Magic Orb.png", 25, true),
                () -> new StraightMovement(new Vector2(700, 0)),
                NoDelay::new,
                Identity::new
            ))),

    FAN_OF_ORBS(
        new Weapon(
            new WeaponContext("FAN OF ORBS", 1f, 3f),
            new ProjectileFactory(
                new ProjectileConfig(30, "weapons/Magic Orb.png", 25, true),
                () -> new StraightMovement(new Vector2(600, 0)),
                NoDelay::new,
                Identity::new),
            List.of(new Rotation(0f), new Rotation(-15f), new Rotation(30f), new Rotation(60f))
            )),

    ECLIPSE(
        new Weapon(
            new WeaponContext("ECLIPSE", 2f, 2f),
            new ProjectileFactory(
                new ProjectileConfig(60, "weapons/Magic Orb.png", 50, true),
                () -> new StraightMovement(new Vector2(400, 0)),
                NoDelay::new,
                Identity::new
            ))),

    ZIGZAG(
        new Weapon(
            new WeaponContext("ZIGZAG ORB", 0.6f, 2f),
            new ProjectileFactory(
                new ProjectileConfig(10, "weapons/Magic Orb.png", 15, false),
                () -> new WindingMovement(List.of(new Vector3(-200, 400, 1f), new Vector3(-200, -400, 1f))),
                NoDelay::new,
                Identity::new
            ))),

    PHALANX_OF_LIGHT(
        new Weapon(
            new WeaponContext("PHALANX OF LIGHT", 1f, 3f),
            List.of(
                new ProjectileFactory(
                    new ProjectileConfig(10, "weapons/Pure Bolt 2.png", 10, true),
                    () -> new StraightMovement(new Vector2(700, 0)),
                    NoDelay::new,
                    Identity::new
                ),
                new ProjectileFactory(
                    new ProjectileConfig(5, "weapons/Pure Bolt 2.png", 7, true),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    () -> new Rotation(30f)
                ),
                new ProjectileFactory(
                    new ProjectileConfig(5, "weapons/Pure Bolt 2.png", 7, true),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    () -> new Rotation(-30f)
                )
            )));

    public final Weapon data;

    WeaponType(Weapon data) {
        this.data = data;
    }
}

