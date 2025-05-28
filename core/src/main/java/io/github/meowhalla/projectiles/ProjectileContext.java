package io.github.meowhalla.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.structure.contexts.CharacterContext;
import io.github.meowhalla.structure.contexts.DynamicObject;
import io.github.meowhalla.player.PlayerContext;
import io.github.meowhalla.projectiles.base_transformation.BaseTransformationStrategy;
import io.github.meowhalla.projectiles.movement.MovementStrategy;
import io.github.meowhalla.projectiles.state.ProjectileState;
import io.github.meowhalla.projectiles.transformation.TransformationStrategy;
import io.github.meowhalla.structure.states.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Supplier;


@Getter
@Setter
public class ProjectileContext implements DynamicObject {

    // config values
    protected float power;                    // damage power
    protected boolean players_projectile;     // avoiding friendly fire
    protected Circle hitbox;                  // current position with hitbox
    protected Direction initialDirection;

    protected ProjectileGraphics chargingFrames;
    protected ProjectileGraphics activeFrames;
    protected ProjectileGraphics destroyingFrames;

    // behavior
    MovementStrategy movement;
    TransformationStrategy transformation;
    BaseTransformationStrategy baseTransformation;

    // state
    protected float timeSinceSpawn = 0f;
    protected ProjectileState state;

    public ProjectileContext(
        Vector2 origin,
        CharacterContext owner,
        ProjectileConfig config,
        Supplier<ProjectileState> stateSupplier,
        Supplier<MovementStrategy> movementSupplier,
        Supplier<TransformationStrategy> transformationSupplier,
        Supplier<BaseTransformationStrategy> baseTransformationStrategySupplier
    ) {
        this.power = config.power();
        players_projectile = owner instanceof PlayerContext;
        this.initialDirection = config.rotateWithPlayer() ? owner.getDirection() : Direction.RIGHT;

        movement = movementSupplier.get();

        state = stateSupplier.get();
        chargingFrames = new ProjectileGraphics(this, state.chargingFramesFilename());
        destroyingFrames = new ProjectileGraphics(this, state.destroyFramesFilename());

        transformation = transformationSupplier.get();

        baseTransformation = baseTransformationStrategySupplier.get();
        this.hitbox = new Circle(baseTransformation.apply(origin), config.radius());

        activeFrames = new ProjectileGraphics(this, config.fileName());
    }

    public void setBaseTransformation(BaseTransformationStrategy newBaseTransformation) {
        this.baseTransformation = newBaseTransformation;
        hitbox.set(newBaseTransformation.apply(new Vector2(hitbox.x, hitbox.y)), hitbox.radius);
    }

    public void update(float delta) {
        timeSinceSpawn += delta;
        if (state.isReady(timeSinceSpawn)) {
            Vector2 v = new Vector2(movement.update(this, delta));
            v = transformation.apply(v);
            v = initialDirection == Direction.RIGHT ? v : new Vector2(-v.x, v.y);
            hitbox.setPosition(hitbox.x + v.x, hitbox.y + v.y);
        }
    }

    public boolean timeToDestroy() {
        return state.isDestroyed(timeSinceSpawn);
    }

    public void render(SpriteBatch batch) {
        if (!state.isReady(timeSinceSpawn)) {
            chargingFrames.render(batch);
        } else if (state.isDestroying(timeSinceSpawn)) {
            destroyingFrames.render(batch);
        } else {
            activeFrames.render(batch);
        }
    }

    @Override
    public float getX() {
        return getHitbox().x;
    }

    @Override
    public float getY() {
        return getHitbox().y;
    }

    @Override
    public float getWidth() {
        return 2 * getHitbox().radius;
    }

    @Override
    public float getHeight() {
        return getWidth();
    }

    public boolean isOffScreen() {
        return hitbox.x + hitbox.radius < 0 ||
            hitbox.x - hitbox.radius > Gdx.graphics.getWidth() ||
            hitbox.y + hitbox.radius < 0 ||
            hitbox.y - hitbox.radius > Gdx.graphics.getHeight();
    }

    public void dispose() {
        chargingFrames.dispose();
        activeFrames.dispose();
        destroyingFrames.dispose();
    }
}


