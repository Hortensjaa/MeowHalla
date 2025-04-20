package io.github.meowhalla.logic;

import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.projectiles.ProjectileContext;
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

    public List<ProjectileContext> shoot() {
        if (timeSinceLastShot > ctx.activeWeapon.getWeaponContext().cooldown()) {
            timeSinceLastShot = 0f;
            return ctx.activeWeapon.generateProjectiles(ctx);
        }
        return null;
    }
}
