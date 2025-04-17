package io.github.meowhalla.data;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.WeaponContext;

public enum WeaponType {
    MAGIC_CRUSHER(
        new WeaponContext(
        "Magic Crusher",
            20,
            "weapons/Magic Orb.png",
            0.5f,
            new Vector2(350, 0),
            20
        )),

    LIGHT_BLESSING(
        new WeaponContext(
            "Light Blessing",
            13,
            "weapons/Pure Bolt 2.png",
            0.3f,
            new Vector2(700, 0),
            8
        ));

    public final WeaponContext data;

    WeaponType(WeaponContext data) {
        this.data = data;
    }
}

