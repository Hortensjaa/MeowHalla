package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.meowhalla.classes.projectiles.*;
import io.github.meowhalla.classes.weapons.Weapon;
import io.github.meowhalla.classes.weapons.WeaponContext;

import java.util.List;


public enum WeaponType {
    MAGIC_ORB_VOLLEY(
        new Weapon(
            new WeaponContext("Magic Orb Volley", 0.5f, 3f),
            new ProjectileFactory(
                new ProjectileConfig(30, "weapons/Magic Orb.png", 25),
                () -> new StraightMovement(new Vector2(700, 0)),
                NoDelay::new,
                Identity::new
            ))),

    FAN_OF_ORBS(
        new Weapon(
            new WeaponContext("FAN OF ORBS", 1f, 3f),
            List.of(
                new ProjectileFactory(
                    new ProjectileConfig(30, "weapons/Magic Orb.png", 25),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    Identity::new
                ),
                new ProjectileFactory(
                    new ProjectileConfig(30, "weapons/Magic Orb.png", 25),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    () -> new Rotate(60f)
                ),
                new ProjectileFactory(
                    new ProjectileConfig(30, "weapons/Magic Orb.png", 25),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    () -> new Rotate(30f)
                ),
                new ProjectileFactory(
                    new ProjectileConfig(30, "weapons/Magic Orb.png", 25),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    () -> new Rotate(-15f)
                )
            ))),

    ECLIPSE(
        new Weapon(
            new WeaponContext("ECLIPSE", 2f, 2f),
            new ProjectileFactory(
                new ProjectileConfig(60, "weapons/Magic Orb.png", 50),
                () -> new StraightMovement(new Vector2(400, 0)),
                NoDelay::new,
                Identity::new
            ))),

    ZIGZAG(
        new Weapon(
            new WeaponContext("ZIGZAG ORB", 0.6f, 2f),
            new ProjectileFactory(
                new ProjectileConfig(10, "weapons/Magic Orb.png", 15),
                () -> new WindingMovement(List.of(new Vector3(200, 400, 1f), new Vector3(200, -400, 1f))),
                NoDelay::new,
                Identity::new
            ))),

    PHALANX_OF_LIGHT(
        new Weapon(
            new WeaponContext("PHALANX OF LIGHT", 1f, 3f),
            List.of(
                new ProjectileFactory(
                    new ProjectileConfig(10, "weapons/Pure Bolt 2.png", 15),
                    () -> new StraightMovement(new Vector2(700, 0)),
                    NoDelay::new,
                    Identity::new
                ),
                new ProjectileFactory(
                    new ProjectileConfig(5, "weapons/Pure Bolt 2.png", 10),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    () -> new Rotate(30f)
                ),
                new ProjectileFactory(
                    new ProjectileConfig(5, "weapons/Pure Bolt 2.png", 10),
                    () -> new StraightMovement(new Vector2(600, 0)),
                    NoDelay::new,
                    () -> new Rotate(-30f)
                )
            )));

    public final Weapon data;

    WeaponType(Weapon data) {
        this.data = data;
    }
}

