package io.github.meowhalla.projectiles;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.contexts.CharacterContext;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.delay.DelayStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import io.github.meowhalla.states.Direction;
import lombok.AllArgsConstructor;

import java.util.function.Supplier;


@AllArgsConstructor
public class ProjectileFactory {
    private final ProjectileConfig config;
    private final Supplier<MovementStrategy> movementSupplier;
    private final Supplier<DelayStrategy> delaySupplier;
    private final Supplier<TransformationStrategy> transformationSupplier;
    private final Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier;

    private Vector2 calculateOrigin(CharacterContext owner) {
        Vector2 width = new Vector2(config.radius(), 0);
        return owner.getDirection() == Direction.RIGHT ? owner.rightBorder() : owner.leftBorder().cpy().sub(width);
    }

    private ProjectileContext createProjectileHelper(CharacterContext owner, Vector2 origin) {
        ProjectileContext p = new ProjectileContext(
            origin,
            owner,
            config,
            movementSupplier,
            delaySupplier,
            transformationSupplier,
            baseTransformationStrategySupplier
        );
        p.hitbox.setPosition(p.baseTransformation.apply(new Vector2(p.hitbox.x, p.hitbox.y)));
        return p;
    }

    public ProjectileContext createProjectile(CharacterContext owner) {
        return createProjectileHelper(owner, calculateOrigin(owner));
    }

    public ProjectileFactory transform(Supplier<Strategy> s) {
        Strategy strategy = s.get();

        if (strategy instanceof TransformationStrategy) {
            Supplier<TransformationStrategy> wrapped = () -> (TransformationStrategy) s.get();
            return new ProjectileFactory(config, movementSupplier, delaySupplier, wrapped, baseTransformationStrategySupplier);
        }

        if (strategy instanceof BaseTransformationStrategy) {
            Supplier<BaseTransformationStrategy> wrapped = () -> (BaseTransformationStrategy) s.get();
            return new ProjectileFactory(config, movementSupplier, delaySupplier, transformationSupplier, wrapped);
        }

        if (strategy instanceof DelayStrategy) {
            Supplier<DelayStrategy> wrapped = () -> (DelayStrategy) s.get();
            return new ProjectileFactory(config, movementSupplier, wrapped, transformationSupplier, baseTransformationStrategySupplier);
        }

        if (strategy instanceof MovementStrategy) {
            Supplier<MovementStrategy> wrapped = () -> (MovementStrategy) s.get();
            return new ProjectileFactory(config, wrapped, delaySupplier, transformationSupplier, baseTransformationStrategySupplier);
        }

        throw new IllegalArgumentException("Unknown strategy type: " + strategy.getClass());
    }
}
