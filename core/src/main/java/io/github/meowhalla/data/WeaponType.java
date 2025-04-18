package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.weapons.WeaponContext;
import io.github.meowhalla.classes.weapons.DirectSingleShotBehavior;
import io.github.meowhalla.classes.weapons.MultipleShotBehavior;

import java.util.List;

public enum WeaponType {
    MAGIC_CRUSHER(
        new WeaponContext(
        "Magic Crusher",
            20,
            "weapons/Magic Orb.png",
            1.5f,
            new Vector2(350, 0),
            20,
            0.5f,
            new DirectSingleShotBehavior()
        )),

    SHURIKENS_OF_LIGHT(
        new WeaponContext(
            "Shurikens of light",
            13,
            "weapons/Pure Bolt 2.png",
            0.7f,
            new Vector2(700, 0),
            8,
            0.2f,
            new MultipleShotBehavior(List.of(0f, 30f, -30f))
        )),

    ASHES_OF_BLESSING(
        new WeaponContext(
            "Ashes of blessing",
            5,
            "weapons/Black And White Sparks.png",
            0.7f,
            new Vector2(700, 0),
            20,
            0.5f,
            new MultipleShotBehavior(List.of(0f, 30f, -30f, -15f, 15f, 45f, 60f))
        ));

    public final WeaponContext data;

    WeaponType(WeaponContext data) {
        this.data = data;
    }
}

