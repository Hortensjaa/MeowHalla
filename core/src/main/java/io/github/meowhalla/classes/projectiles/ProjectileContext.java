package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.DynamicObject;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.graphics.ProjectileGraphics;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
abstract public class ProjectileContext implements DynamicObject {

    protected int power;                      // damage power
    protected CharacterContext owner;         // reference to character who cast it
    protected ProjectileGraphics graphics;    // graphics reference
    protected Circle hitbox;                  // current position with hitbox

    public ProjectileContext(Vector2 origin, String fileName, int power, float radius) {
        this.hitbox = new Circle(origin.x, origin.y, radius);
        this.power = power;
        graphics = new ProjectileGraphics(this, fileName);
    }

    abstract public void update(float delta);

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


