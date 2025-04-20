package io.github.meowhalla.projectiles;

import io.github.meowhalla.projectiles.base_transformation.BaseIdentity;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.delay.DelayStrategy;
import io.github.meowhalla.projectiles.delay.NoDelay;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.movement.NoMovement;
import io.github.meowhalla.projectiles.transformation.Identity;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;

import java.util.function.Supplier;

public class ProjectileFactoryBuilder {
    private ProjectileConfig config;
    private Supplier<MovementStrategy> movementSupplier = NoMovement::new;
    private Supplier<DelayStrategy> delaySupplier = NoDelay::new;
    private Supplier<TransformationStrategy> transformationSupplier = Identity::new;
    private Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier = BaseIdentity::new;

    public ProjectileFactoryBuilder config(ProjectileConfig config) {
        this.config = config;
        return this;
    }

    public ProjectileFactoryBuilder movement(Supplier<MovementStrategy> supplier) {
        this.movementSupplier = supplier;
        return this;
    }

    public ProjectileFactoryBuilder delay(Supplier<DelayStrategy> supplier) {
        this.delaySupplier = supplier;
        return this;
    }

    public ProjectileFactoryBuilder transformation(Supplier<TransformationStrategy> supplier) {
        this.transformationSupplier = supplier;
        return this;
    }

    public ProjectileFactoryBuilder baseTransformation(Supplier<BaseTransformationStrategy> supplier) {
        this.baseTransformationStrategySupplier = supplier;
        return this;
    }

    public ProjectileFactory build() {
        return new ProjectileFactory(
            config,
            movementSupplier,
            delaySupplier,
            transformationSupplier,
            baseTransformationStrategySupplier
        );
    }
}

