package io.github.meowhalla.classes.weapons;

import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.classes.projectiles.ProjectileFactory;
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

    public List<ProjectileContext> generateProjectiles(CharacterContext ctx) {
        List<ProjectileContext> projectiles = new ArrayList<>();
        for (ProjectileFactory factory : projectileFactories) {
            ProjectileContext p = factory.createProjectile(ctx);
            projectiles.add(p);
        }
        return projectiles;
    }
}
