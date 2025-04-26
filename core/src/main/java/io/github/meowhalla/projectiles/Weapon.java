package io.github.meowhalla.projectiles;

import io.github.meowhalla.contexts.CharacterContext;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


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
            this.projectileFactories.add(baseFactory.transform(() -> (TransformationStrategy) t));
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

    public Weapon transform(Supplier<Strategy> t) {
        List<ProjectileFactory> transformed = projectileFactories.stream()
                .map(f -> f.transform(t))
                .collect(Collectors.toList());
        return new Weapon(weaponContext, transformed);
    }

    public Weapon copy() {
        List<ProjectileFactory> copiedFactories = new ArrayList<>();
        for (ProjectileFactory factory : projectileFactories) {
            copiedFactories.add(factory.copy());
        }
        return new Weapon(weaponContext, copiedFactories);
    }

}
