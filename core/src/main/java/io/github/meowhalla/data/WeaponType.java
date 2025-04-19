package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.projectiles.MultipleBasicProjectileFactory;
import io.github.meowhalla.classes.weapons.Weapon;
import io.github.meowhalla.classes.weapons.WeaponContext;

import java.util.List;


public enum WeaponType {
    MAGIC_ORB_VOLLEY(
        new Weapon(
            new WeaponContext("Magic Orb Volley", 0.5f, 0.7f),
            new MultipleBasicProjectileFactory(
                20,
                "weapons/Magic Orb.png",
                25,
                new Vector2(700, 0),
                0f,
                List.of(0f)
            ))),

    FAN_OF_ORBS(
        new Weapon(
            new WeaponContext("Fan of Orbs", 1f, 0.7f),
            new MultipleBasicProjectileFactory(
                20,
                "weapons/Magic Orb.png",
                30,
                new Vector2(600, 0),
                0f,
                List.of(0f, 30f, 60f)
            ))),

    ECLIPSE(
        new Weapon(
            new WeaponContext("Eclipse", 2f, 2f),
            new MultipleBasicProjectileFactory(
                20,
                "weapons/Magic Orb.png",
                50,
                new Vector2(400, 0),
                -5f,
                List.of(0f)
            ))),

    SHURIKENS_OF_LIGHT(
        new Weapon(
        new WeaponContext("Shurikens of Light", 0.5f, 0.7f),
        new MultipleBasicProjectileFactory(
            20,
            "weapons/Pure Bolt 2.png",
            8,
            new Vector2(700, 0),
            0f,
            List.of(0f, 30f, -30f)
    )));

    public final Weapon data;

    WeaponType(Weapon data) {
        this.data = data;
    }
}

