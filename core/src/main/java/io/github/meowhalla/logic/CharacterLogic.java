package io.github.meowhalla.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.states.Direction;

import java.util.List;

public abstract class CharacterLogic {
    CharacterContext ctx;

    public CharacterLogic(CharacterContext ctx) {
        this.ctx = ctx;
    }

    public abstract void update(float delta);

    public List<ProjectileContext> shoot(Pool<ProjectileContext> bulletPool) {
        if (ctx.timeSinceLastShot > ctx.weapon.cooldown()) {
            Vector2 origin = ctx.state.getDirection() == Direction.RIGHT
                ? ctx.rightBorder()
                : ctx.leftBorder();
            ctx.timeSinceLastShot = 0f;
            return ctx.weapon.behavior().shoot(origin, ctx.state.getDirection(), ctx.weapon, bulletPool);
        }
        return null;
    }
}
