package io.github.meowhalla.enemies.mocktopus;

import io.github.meowhalla.game.GameContext;
import io.github.meowhalla.projectiles.ProjectileConfig;
import io.github.meowhalla.projectiles.ProjectileDataBuilder;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.projectiles.WeaponContext;
import io.github.meowhalla.projectiles.movement.ChasingMovementStrategy;
import io.github.meowhalla.projectiles.state.DestroyAfterState;

public enum MocktopusWeapons {
    CHASING_THE_SUN(
        new Weapon(
            new WeaponContext("Chasing the sun", 2f),
            new ProjectileDataBuilder()
                .config(new ProjectileConfig(30, "weapons/Fireball.png", 25, false))
                .movement(() -> new ChasingMovementStrategy(GameContext.getInstance().getPlayer(), 250))
                .state(() -> new DestroyAfterState(5f, "weapons/Black And White Sparks.png"))
                .build()
        )
    );

    public final Weapon data;

    MocktopusWeapons(Weapon data) {
        this.data = data;
    }

}

