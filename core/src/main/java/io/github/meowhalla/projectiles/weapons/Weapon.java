package io.github.meowhalla.projectiles.weapons;

import io.github.meowhalla.contexts.CharacterContext;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.ProjectileFactory;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class Weapon {
    WeaponContext weaponContext;
    List<ProjectileFactory> projectileFactories;

    public Weapon(WeaponContext w, ProjectileFactory f) {
        this.weaponContext = w;
        this.projectileFactories = List.of(f);
    }

    public Weapon(WeaponContext w, ProjectileFactory baseFactory, List<TransformationStrategy> transformations) {
        this.weaponContext = w;
        this.projectileFactories = new ArrayList<>();

        for (TransformationStrategy t : transformations) {
            this.projectileFactories.add(baseFactory.transformFactory(t));
        }
    }

    public List<ProjectileContext> generateProjectiles(CharacterContext ctx) {
        List<ProjectileContext> projectiles = new ArrayList<>();
        for (ProjectileFactory factory : projectileFactories) {
            ProjectileContext p = factory.createProjectile(ctx);
            projectiles.add(p);
        }
        return projectiles;
    }
}
