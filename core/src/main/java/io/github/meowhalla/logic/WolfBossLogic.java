package io.github.meowhalla.logic;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;

import java.util.List;

public class WolfBossLogic extends CharacterLogic {
    public WolfBossLogic(CharacterContext ctx, int max_hp) {
        super(ctx, max_hp);
    }

    public void update(float delta) {
        if (timeSinceLastShot > ctx.weapon.cooldown()) {
            ctx.state.setAction(Action.ATTACK);
        } else if (timeSinceLastShot > ctx.weapon.cooldown() * 0.2f) {
            ctx.state.setAction(Action.IDLE);
        }
        timeSinceLastShot += delta;
    }

    @Override
    public List<ProjectileContext> shoot(Pool<ProjectileContext> bulletPool) {
        if (timeSinceLastShot > ctx.weapon.cooldown()) {
            Vector2 origin = ctx.state.getDirection() == Direction.RIGHT
                ? ctx.rightBorder()
                : ctx.leftBorder();
            origin.y *= 0.5f;
            double r = Math.random();
            if (r <= 0.5) {
                origin.y *= 0.5f;
            } else if (r <= 0.7) {
                origin.y *= 1.5f;
            }
            timeSinceLastShot = 0f;
            return ctx.weapon.behavior().shoot(origin, ctx.state.getDirection(), ctx.weapon, ctx, bulletPool);
        }
        return null;
    }
}
