package io.github.meowhalla.classes.behaviors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import io.github.meowhalla.classes.ProjectileContext;
import io.github.meowhalla.classes.WeaponContext;
import io.github.meowhalla.states.Direction;

import java.util.List;

public interface WeaponBehavior {
    List<ProjectileContext> shoot(
        Vector2 origin,
        Direction direction,
        WeaponContext data,
        Pool<ProjectileContext> pool
    );
}


