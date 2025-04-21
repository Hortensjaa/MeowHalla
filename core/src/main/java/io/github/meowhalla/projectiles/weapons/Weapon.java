package io.github.meowhalla.projectiles.weapons;

import io.github.meowhalla.contexts.CharacterContext;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.ProjectileFactory;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.delay.DelayStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
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
            this.projectileFactories.add(baseFactory.withTransformation(() -> (TransformationStrategy) t));
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

    public Weapon changeMovement(Supplier<MovementStrategy> t) {
        List<ProjectileFactory> transformed = projectileFactories.stream()
                .map(f -> f.withMovement(t))
                .collect(Collectors.toList());
        return new Weapon(weaponContext, transformed);
    }

    public Weapon changeDelay(Supplier<DelayStrategy> t) {
        List<ProjectileFactory> transformed = projectileFactories.stream()
                .map(f -> f.withDelay(t))
                .collect(Collectors.toList());
        return new Weapon(weaponContext, transformed);
    }

    public Weapon changeTransform(Supplier<TransformationStrategy> t) {
        List<ProjectileFactory> transformed = projectileFactories.stream()
                .map(f -> f.withTransformation(t))
                .collect(Collectors.toList());
        return new Weapon(weaponContext, transformed);
    }

    public Weapon changeBase(Supplier<BaseTransformationStrategy> t) {
        List<ProjectileFactory> transformed = projectileFactories.stream()
                .map(f -> f.withBaseTransformation(t))
                .collect(Collectors.toList());
        return new Weapon(weaponContext, transformed);
    }

}
