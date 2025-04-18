package io.github.meowhalla.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.states.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class CharacterLogic {
    CharacterContext ctx;
    @Getter int max_hp;
    @Setter @Getter int hp;
    public float timeSinceLastShot = 0f;

    public CharacterLogic(CharacterContext ctx, int max_hp) {
        this.ctx = ctx;
        this.max_hp = max_hp;
        this.hp = max_hp;
    }

    public abstract void update(float delta);

    public List<ProjectileContext> shoot(Pool<ProjectileContext> bulletPool) {
        if (timeSinceLastShot > ctx.weapon.cooldown()) {
            Vector2 origin = ctx.state.getDirection() == Direction.RIGHT
                ? ctx.rightBorder()
                : ctx.leftBorder();
            timeSinceLastShot = 0f;
            return ctx.weapon.behavior().shoot(origin, ctx.state.getDirection(), ctx.weapon, ctx, bulletPool);
        }
        return null;
    }
}
