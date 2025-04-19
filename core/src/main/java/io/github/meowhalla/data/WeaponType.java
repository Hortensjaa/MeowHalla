package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.meowhalla.classes.projectiles.MultipleBasicProjectileFactory;
import io.github.meowhalla.classes.projectiles.WindingProjectileFactory;
import io.github.meowhalla.classes.weapons.Weapon;
import io.github.meowhalla.classes.weapons.WeaponContext;

import java.util.List;


public enum WeaponType {
    MAGIC_ORB_VOLLEY(
        new Weapon(
            new WeaponContext("Magic Orb Volley", 0.5f, 3f),
            new MultipleBasicProjectileFactory(
                30,
                "weapons/Magic Orb.png",
                25,
                new Vector2(700, 0),
                List.of(0f)
            ))),

    FAN_OF_ORBS(
        new Weapon(
            new WeaponContext("Fan of Orbs", 1f, 5f),
            new MultipleBasicProjectileFactory(
                20,
                "weapons/Magic Orb.png",
                30,
                new Vector2(600, 0),
                List.of(0f, 30f, 60f, -30f)
            ))),

    ECLIPSE(
        new Weapon(
            new WeaponContext("Eclipse", 2f, 2f),
            new MultipleBasicProjectileFactory(
                50,
                "weapons/Magic Orb.png",
                50,
                new Vector2(400, 0),
                -5f,
                List.of(0f)
            ))),

    ZIGZAG(
        new Weapon(
            new WeaponContext("Zigzag", 0.7f, 2f),
            new WindingProjectileFactory(
                15,
                "weapons/Magic Orb.png",
                15,
                List.of(new Vector3(-200, 400, 1f), new Vector3(-200, -400, 1f))
            ))),

    SHURIKENS_OF_LIGHT(
        new Weapon(
        new WeaponContext("Shurikens of Light", 0.5f, 0.7f),
        new MultipleBasicProjectileFactory(
            10,
            "weapons/Pure Bolt 2.png",
            8,
            new Vector2(700, 0),
            0f,
            List.of(0f, 20f, -20f)
    )));

    public final Weapon data;

    WeaponType(Weapon data) {
        this.data = data;
    }
}

