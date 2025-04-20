package io.github.meowhalla.projectiles;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.characters.CharacterContext;
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

    public ProjectileFactory transformFactory(TransformationStrategy t) {
        return new ProjectileFactory(config, movementSupplier, delaySupplier, () -> t, baseTransformationStrategySupplier);
    }

    public ProjectileFactory transformFactory(BaseTransformationStrategy bt) {
        return new ProjectileFactory(config, movementSupplier, delaySupplier, transformationSupplier, () -> bt);
    }
}
