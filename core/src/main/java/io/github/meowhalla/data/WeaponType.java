package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.WeaponContext;
import io.github.meowhalla.classes.behaviors.DirectSingleShotBehavior;
import io.github.meowhalla.classes.behaviors.MultipleShotBehavior;

import java.util.List;

public enum WeaponType {
    MAGIC_CRUSHER(
        new WeaponContext(
        "Magic Crusher",
            20,
            "weapons/Magic Orb.png",
            0.5f,
            new Vector2(350, 0),
            20,
            new DirectSingleShotBehavior()
        )),

    SHURIKENS_OF_LIGHT(
        new WeaponContext(
            "Light Blessing",
            13,
            "weapons/Pure Bolt 2.png",
            0.3f,
            new Vector2(700, 0),
            8,
            new MultipleShotBehavior(List.of(0f, 15f, -15f, 30f, -30f))
        ));

    public final WeaponContext data;

    WeaponType(WeaponContext data) {
        this.data = data;
    }
}

