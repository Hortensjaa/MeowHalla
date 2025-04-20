package io.github.meowhalla.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.DynamicObject;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.graphics.Graphics;
import io.github.meowhalla.graphics.ProjectileGraphics;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.delay.DelayStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import io.github.meowhalla.states.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;


@Getter
@Setter
public class ProjectileContext implements DynamicObject {

    // config values
    protected int power;                      // damage power
    protected CharacterContext owner;         // reference to character who cast it
    protected Circle hitbox;                  // current position with hitbox
    protected ProjectileGraphics graphics;
    protected Direction initialDirection;
    protected Graphics waitingFrames;

    // behavior
    DelayStrategy delay;
    MovementStrategy movement;
    TransformationStrategy transformation;
    BaseTransformationStrategy baseTransformation;

    // state
    protected float timeSinceSpawn = 0f;

    public ProjectileContext(
        Vector2 origin,
        CharacterContext owner,
        ProjectileConfig config,
        Supplier<MovementStrategy>movementSupplier,
        Supplier<DelayStrategy> delaySupplier,
        Supplier<TransformationStrategy> transformationSupplier,
        Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier
    ) {
        this.power = config.power();
        this.owner = owner;
        this.initialDirection = config.rotateWithPlayer() ? owner.getDirection() : Direction.RIGHT;

        movement = movementSupplier.get();

        delay = delaySupplier.get();
        waitingFrames = new ProjectileGraphics(this, delay.waitingFramesFilename());

        transformation = transformationSupplier.get();

        baseTransformation = baseTransformationStrategySupplier.get();
        this.hitbox = new Circle(baseTransformation.apply(origin), config.radius());

        graphics = new ProjectileGraphics(this, config.fileName());
    }

    public void update(float delta) {
        timeSinceSpawn += delta;
        if (delay.isReady(timeSinceSpawn)) {
            Vector2 v = new Vector2(movement.update(this, delta));
            v = transformation.apply(v);
            v = initialDirection == Direction.RIGHT ? v : new Vector2(-v.x, v.y);
            hitbox.setPosition(hitbox.x + v.x, hitbox.y + v.y);
        }
    }

    public void render(SpriteBatch batch) {
        if (delay.isReady(timeSinceSpawn)) {
            graphics.render(batch);
        } else {
            waitingFrames.render(batch);
        }
    }

    public boolean isOffScreen() {
        return hitbox.x + hitbox.radius < 0 ||
            hitbox.x - hitbox.radius > Gdx.graphics.getWidth() ||
            hitbox.y + hitbox.radius < 0 ||
            hitbox.y - hitbox.radius > Gdx.graphics.getHeight();
    }

    public void dispose() {
        graphics.dispose();
    }
}


