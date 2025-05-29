package io.github.meowhalla.projectiles;

import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.state.ProjectileState;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import lombok.AllArgsConstructor;

import java.util.function.Supplier;


@AllArgsConstructor
public class ProjectileData {
    private final ProjectileConfig config;
    private final Supplier<MovementStrategy> movementSupplier;
    private final Supplier<ProjectileState> stateSupplier;
    private final Supplier<TransformationStrategy> transformationSupplier;
    private final Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier;

    ProjectileContext createProjectile(CharacterContext owner) {
        ProjectileFactory factory = ProjectileFactory.getInstance();
        return factory.createProjectile(owner, config, movementSupplier, stateSupplier, transformationSupplier, baseTransformationStrategySupplier);
    }

    public ProjectileData transform(Supplier<Strategy> s) {
        Strategy strategy = s.get();

        if (strategy instanceof TransformationStrategy) {
            TransformationStrategy newTransformation = (TransformationStrategy) s.get();

            if (transformationSupplier != null) {
                TransformationStrategy existingTransformation = transformationSupplier.get();
                Supplier<TransformationStrategy> compositeSupplier =
                    () -> vector -> newTransformation.apply(existingTransformation.apply(vector));

                return new ProjectileData(config, movementSupplier, stateSupplier,
                    compositeSupplier, baseTransformationStrategySupplier);
            }

            Supplier<TransformationStrategy> wrapped = () -> (TransformationStrategy) s.get();
            return new ProjectileData(config, movementSupplier, stateSupplier,
                wrapped, baseTransformationStrategySupplier);
        }

        if (strategy instanceof BaseTransformationStrategy) {
            if (baseTransformationStrategySupplier != null) {
                Supplier<BaseTransformationStrategy> compositeSupplier = () -> {
                    BaseTransformationStrategy existing = baseTransformationStrategySupplier.get();
                    BaseTransformationStrategy newTrans = (BaseTransformationStrategy) s.get();
                   return vector -> newTrans.apply(existing.apply(vector));
                };

                return new ProjectileData(config, movementSupplier, stateSupplier,
                    transformationSupplier, compositeSupplier);
            }

            Supplier<BaseTransformationStrategy> wrapped = () -> (BaseTransformationStrategy) s.get();
            return new ProjectileData(config, movementSupplier, stateSupplier,
                transformationSupplier, wrapped);
        }

        if (strategy instanceof ProjectileState) {
            Supplier<ProjectileState> wrapped = () -> (ProjectileState) s.get();
            return new ProjectileData(config, movementSupplier, wrapped, transformationSupplier, baseTransformationStrategySupplier);
        }

        if (strategy instanceof MovementStrategy) {
            Supplier<MovementStrategy> wrapped = () -> (MovementStrategy) s.get();
            return new ProjectileData(config, wrapped, stateSupplier, transformationSupplier, baseTransformationStrategySupplier);
        }

        throw new IllegalArgumentException("Unknown strategy type: " + strategy.getClass());
    }

    public ProjectileData copy() {
        return new ProjectileData(
            config,
            movementSupplier,
            stateSupplier,
            transformationSupplier,
            baseTransformationStrategySupplier
        );
    }

}
