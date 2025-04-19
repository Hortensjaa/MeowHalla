package io.github.meowhalla.logic;


import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WolfBossLogic extends CharacterLogic {
    float currentComboTime = 0f;
    int currentComboId = 0;
    float[] comboTimes = {5f, 10f, 10f};
    Supplier<List<ProjectileContext>> combo0 = () -> {
        ctx.activeWeapon = WeaponType.ECLIPSE.data;
        return super.shoot();
    };
    Supplier<List<ProjectileContext>> combo1 = () -> {
        ctx.activeWeapon = WeaponType.FAN_OF_ORBS.data;
        return super.shoot();
    };
    Supplier<List<ProjectileContext>> combo2 = () -> {
        List<ProjectileContext> projectiles = super.shoot();
        if (projectiles == null) return null;
        ctx.activeWeapon = WeaponType.MAGIC_ORB_VOLLEY.data;
        Vector2 origin = ctx.state.getDirection() == Direction.RIGHT
            ? ctx.rightBorder()
            : ctx.leftBorder();
        double r = Math.random();
        if (r <= 0.5) {
            origin.y -= 100f;
        } else if (r <= 0.7) {
            origin.y += 100f;
        }
        return projectiles.stream()
            .peek(p -> p.getHitbox().y = origin.y)
            .collect(Collectors.toList());
    };
    List<Supplier<List<ProjectileContext>>> comboSuppliers = List.of(combo0, combo1, combo2);

    public WolfBossLogic(CharacterContext ctx, int max_hp) {
        super(ctx, max_hp);
    }

    public void update(float delta) {
        if (timeSinceLastShot > ctx.activeWeapon.weaponContext().cooldown()) {
            ctx.state.setAction(Action.ATTACK);
        } else if (timeSinceLastShot > ctx.activeWeapon.weaponContext().cooldown() * 0.2f) {
            ctx.state.setAction(Action.IDLE);
        }
        timeSinceLastShot += delta;
        currentComboTime += delta;
    }

    @Override
    public List<ProjectileContext> shoot() {
       if (currentComboTime > comboTimes[currentComboId]) {
            currentComboId++;
            currentComboId %= comboSuppliers.size();
            currentComboTime = 0f;
       }
       return comboSuppliers.get(currentComboId).get();
    }
}
