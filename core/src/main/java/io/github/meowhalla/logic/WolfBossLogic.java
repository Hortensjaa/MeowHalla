package io.github.meowhalla.logic;


import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.classes.weapons.Weapon;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WolfBossLogic extends CharacterLogic {
    float currentComboTime = 0f;
    int currentComboId = -1;
    float[] comboTimes = {10f, 15f, 15f};
    Weapon[] comboWeapons = {WeaponType.ZIGZAG.data, WeaponType.FAN_OF_ORBS.data, WeaponType.MAGIC_ORB_VOLLEY.data};
    Supplier<List<ProjectileContext>> combo0 = super::shoot;
    Supplier<List<ProjectileContext>> combo1 = super::shoot;
    Supplier<List<ProjectileContext>> combo2 = () -> {
        List<ProjectileContext> projectiles = super.shoot();
        if (projectiles == null) return null;
        Vector2 origin = ctx.state.getDirection() == Direction.RIGHT
            ? ctx.rightBorder()
            : ctx.leftBorder();
        double r = Math.random();
        if (r <= 0.5) {
            origin.y -= 150f;
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

        // change combo
        if ((currentComboTime == 0 && ctx.state.getAction() != Action.CASTING)
            || currentComboTime >= comboTimes[currentComboId]) {
            currentComboId++;
            currentComboId %= comboSuppliers.size();
            currentComboTime = 0f;
            ctx.state.setAction(Action.CASTING);
            ctx.activeWeapon = comboWeapons[currentComboId];
        }

        // during combo
        else if (currentComboTime > ctx.activeWeapon.getWeaponContext().chargeTime()
            && timeSinceLastShot > ctx.activeWeapon.getWeaponContext().cooldown()) {
            ctx.state.setAction(Action.ATTACK);
        }

        timeSinceLastShot += delta;
        currentComboTime += delta;
    }

    @Override
    public List<ProjectileContext> shoot() {
        return comboSuppliers.get(currentComboId).get();
    }
}
