package io.github.meowhalla.projectiles;

import io.github.meowhalla.projectiles.base_transformation.BaseIdentity;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.movement.NoMovement;
import io.github.meowhalla.projectiles.state.NoDelayNoDestroyState;
import io.github.meowhalla.projectiles.state.ProjectileState;
import io.github.meowhalla.projectiles.transformation.Identity;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;

import java.util.function.Supplier;

public class ProjectileDataBuilder {
    private ProjectileConfig config;
    private Supplier<ProjectileState> stateSupplier = NoDelayNoDestroyState::new;
    private Supplier<MovementStrategy> movementSupplier = NoMovement::new;
    private Supplier<TransformationStrategy> transformationSupplier = Identity::new;
    private Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier = BaseIdentity::new;

    public ProjectileDataBuilder config(ProjectileConfig config) {
        this.config = config;
        return this;
    }

    public ProjectileDataBuilder movement(Supplier<MovementStrategy> supplier) {
        this.movementSupplier = supplier;
        return this;
    }

    public ProjectileDataBuilder state(Supplier<ProjectileState> supplier) {
        this.stateSupplier = supplier;
        return this;
    }

    public ProjectileDataBuilder transformation(Supplier<TransformationStrategy> supplier) {
        this.transformationSupplier = supplier;
        return this;
    }

    public ProjectileDataBuilder baseTransformation(Supplier<BaseTransformationStrategy> supplier) {
        this.baseTransformationStrategySupplier = supplier;
        return this;
    }

    public ProjectileData build() {
        return new ProjectileData(
            config,
            movementSupplier,
            stateSupplier,
            transformationSupplier,
            baseTransformationStrategySupplier
        );
    }
}

