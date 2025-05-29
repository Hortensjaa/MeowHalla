package io.github.meowhalla.projectiles;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.state.ProjectileState;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import io.github.meowhalla.structure.character.CharacterContext;

import java.util.function.Supplier;

public class ProjectileFactory {
    private static ProjectileFactory instance;

    private ProjectileFactory() {
        instance = this;
    }

    public static ProjectileFactory getInstance() {
        if (instance == null) {
            instance = new ProjectileFactory();
        }
        return instance;
    }

    private Vector2 calculateOrigin(CharacterContext owner, ProjectileConfig config) {
        Vector2 width = new Vector2(config.radius(), 0);
        return owner.getCenter();
    }

    private ProjectileContext createProjectileHelper(
        CharacterContext owner,
        Vector2 origin,
        ProjectileConfig config,
        Supplier<MovementStrategy> movementSupplier,
        Supplier<ProjectileState> stateSupplier,
        Supplier<TransformationStrategy> transformationSupplier,
        Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier
        ) {
        ProjectileContext p = new ProjectileContext(
            origin,
            owner,
            config,
            stateSupplier,
            movementSupplier,
            transformationSupplier,
            baseTransformationStrategySupplier
        );
        p.hitbox.setPosition(p.baseTransformation.apply(new Vector2(p.hitbox.x, p.hitbox.y)));
        return p;
    }

    public ProjectileContext createProjectile(
        CharacterContext owner,
        ProjectileConfig config,
        Supplier<MovementStrategy> movementSupplier,
        Supplier<ProjectileState> stateSupplier,
        Supplier<TransformationStrategy> transformationSupplier,
        Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier
    ) {
        return createProjectileHelper(
            owner,
            calculateOrigin(owner, config),
            config,
            movementSupplier,
            stateSupplier,
            transformationSupplier,
            baseTransformationStrategySupplier
        );
    }
}
