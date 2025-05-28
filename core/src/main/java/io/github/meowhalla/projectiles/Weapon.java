package io.github.meowhalla.projectiles;

import io.github.meowhalla.structure.contexts.CharacterContext;
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
    List<ProjectileData> projectileDataList;

    public Weapon(WeaponContext w, ProjectileData f) {
        this.weaponContext = w;
        this.projectileDataList = List.of(f);
    }

    public Weapon(WeaponContext w, ProjectileData projectileData, List<TransformationStrategy> transformations) {
        this.weaponContext = w;
        this.projectileDataList = new ArrayList<>();

        for (TransformationStrategy t : transformations) {
            this.projectileDataList.add(projectileData.transform(() -> (TransformationStrategy) t));
        }
    }

    public List<ProjectileContext> generateProjectiles(CharacterContext ctx) {
        List<ProjectileContext> projectiles = new ArrayList<>();
        for (ProjectileData data : projectileDataList) {
            ProjectileContext p = data.createProjectile(ctx);
            projectiles.add(p);
        }
        return projectiles;
    }

    public Weapon transform(Supplier<Strategy> t) {
        List<ProjectileData> transformed = projectileDataList.stream()
                .map(f -> f.transform(t))
                .collect(Collectors.toList());
        return new Weapon(weaponContext, transformed);
    }

    public Weapon copy() {
        List<ProjectileData> copiedFactories = new ArrayList<>();
        for (ProjectileData factory : projectileDataList) {
            copiedFactories.add(factory.copy());
        }
        return new Weapon(weaponContext, copiedFactories);
    }

}
