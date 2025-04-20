package io.github.meowhalla.projectiles;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.projectiles.delay.DelayStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import io.github.meowhalla.states.Direction;

import java.util.function.Supplier;



public class ProjectileFactory {
    private final ProjectileConfig config;
    private final Supplier<MovementStrategy> movementSupplier;
    private final Supplier<DelayStrategy> delaySupplier;
    private final Supplier<TransformationStrategy> transformationSupplier;

    public ProjectileFactory(
        ProjectileConfig config,
        Supplier<MovementStrategy> movementSupplier,
        Supplier<DelayStrategy> delaySupplier,
        Supplier<TransformationStrategy> transformationSupplier
    ) {
        this.config = config;
        this.movementSupplier = movementSupplier;
        this.delaySupplier = delaySupplier;
        this.transformationSupplier = transformationSupplier;
    }

    private Vector2 calculateOrigin(CharacterContext owner) {
        Vector2 width = new Vector2(config.radius(), 0);
        return owner.getDirection() == Direction.RIGHT ? owner.rightBorder() : owner.leftBorder().cpy().sub(width);
    }

    public ProjectileContext createProjectile(CharacterContext owner, Vector2 origin) {
        ProjectileContext p = new ProjectileContext(origin, owner, config);
        p.setMovement(movementSupplier.get());
        p.setDelay(delaySupplier.get());
        p.setTransformation(transformationSupplier.get());
        p.hitbox.setPosition(p.transformation.applyStart(new Vector2(p.hitbox.x, p.hitbox.y)));
        return p;
    }

    public ProjectileContext createProjectile(CharacterContext owner) {
        return createProjectile(owner, calculateOrigin(owner));
    }

    public ProjectileFactory transformFactory(TransformationStrategy t) {
        return new ProjectileFactory(config, movementSupplier, delaySupplier, () -> t);
    }
}
