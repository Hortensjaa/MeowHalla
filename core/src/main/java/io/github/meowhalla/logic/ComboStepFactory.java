package io.github.meowhalla.logic;

import io.github.meowhalla.contexts.GameContext;
import io.github.meowhalla.contexts.CharacterContext;
import io.github.meowhalla.projectiles.weapons.Weapon;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.states.Action;

import java.util.List;
import java.util.function.Consumer;

public class ComboStepFactory {

    public static ComboStep casting(float duration, CharacterContext ctx, Weapon weapon) {
        return new ComboStep(
            duration,
            () -> {
                ctx.state.setAction(Action.CASTING);
                ctx.activeWeapon = weapon;
            },
            null
        );
    }

    public static ComboStep idle(float duration, CharacterContext ctx) {
        return new ComboStep(
            duration,
            () -> ctx.state.setAction(Action.IDLE),
            null
        );
    }

    public static ComboStep continuousFire(
        float duration,
        CharacterContext ctx,
        GameContext game,
        float cooldownOverride
    ) {
        return new ComboStep(
            duration,
            () -> ctx.state.setAction(Action.ATTACK),
            new Consumer<>() {
                float shootTimer = 0f;

                @Override
                public void accept(Float delta) {
                    shootTimer += delta;
                    if (shootTimer >= cooldownOverride) {
                        shootTimer = 0f;
                        List<ProjectileContext> projectiles = ctx.activeWeapon.generateProjectiles(ctx);
                        game.getProjectiles().addAll(projectiles);
                    }
                }
            }
        );
    }
}

