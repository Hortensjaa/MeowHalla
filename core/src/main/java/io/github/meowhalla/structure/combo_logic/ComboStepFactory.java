package io.github.meowhalla.structure.combo_logic;

import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.game.GameContext;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.structure.states.Action;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public static ComboStep randomizedFire(
            float duration,
            CharacterContext ctx,
            GameContext game,
            float cooldown,
            Function<ProjectileContext, ProjectileContext> modifier
    ) {
        return new ComboStep(
            duration,
            () -> ctx.state.setAction(Action.ATTACK),
            new Consumer<>() {
                float shootTimer = 0f;

                @Override
                public void accept(Float delta) {
                    shootTimer += delta;
                    if (shootTimer >= cooldown) {
                        shootTimer = 0f;
                        List<ProjectileContext> projectiles = ctx.activeWeapon.generateProjectiles(ctx);
                        if (projectiles != null) {
                            game.getProjectiles().addAll(projectiles.stream().map(modifier).toList());
                        }
                    }
                }
            }
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

    public static ComboStep continuousFire(
        float duration,
        CharacterContext ctx,
        GameContext game
    ) {
        return new ComboStep(
            duration,
            () -> ctx.state.setAction(Action.ATTACK),
            new Consumer<>() {
                float shootTimer = 0f;

                @Override
                public void accept(Float delta) {
                    shootTimer += delta;
                    if (shootTimer >= ctx.activeWeapon.getWeaponContext().cooldown()) {
                        shootTimer = 0f;
                        List<ProjectileContext> projectiles = ctx.activeWeapon.generateProjectiles(ctx);
                        game.getProjectiles().addAll(projectiles);
                    }
                }
            }
        );
    }
}

