package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.DynamicObject;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.graphics.ProjectileGraphics;
import io.github.meowhalla.states.Direction;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProjectileContext implements DynamicObject {

    // config values
    protected int power;                      // damage power
    protected CharacterContext owner;         // reference to character who cast it
    protected Circle hitbox;                  // current position with hitbox
    protected ProjectileGraphics graphics;
    protected Direction initialDirection;

    // behavior
    DelayStrategy delay;
    MovementStrategy movement;
    TransformationStrategy transformation;

    // state
    protected float timeSinceSpawn;

    public ProjectileContext(Vector2 origin, String fileName, int power, float radius, CharacterContext owner) {
        this.hitbox = new Circle(origin.x, origin.y, radius);
        this.power = power;
        this.owner = owner;
        this.initialDirection = owner.getDirection();
        graphics = new ProjectileGraphics(this, fileName);
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
        graphics.render(batch);
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


