package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.DynamicObject;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.graphics.ProjectileGraphics;
import lombok.Getter;
import lombok.Setter;

public class ProjectileContext implements DynamicObject {

    @Getter @Setter private Vector2 velocity;
    @Getter private int power;
    @Getter private Circle position;
    @Getter private CharacterContext owner;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private ProjectileGraphics graphics;

    public ProjectileContext(Vector2 position, Vector2 velocity, String fileName, int power, float radius) {
        this.position = new Circle(position.x, position.y, radius);
        this.velocity = new Vector2(velocity);
        this.power = power;
        graphics = new ProjectileGraphics(this, fileName);
    }

    public void update(float delta) {
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
    }

    public void render(SpriteBatch batch) {
        graphics.render(batch);
    }

    public void reset(Vector2 position, Vector2 velocity, String fileName, int power, float radius, CharacterContext owner) {
        this.position = new Circle(position.x, position.y, radius);
        this.velocity.set(velocity);
        this.power = power;
        this.graphics = new ProjectileGraphics(this, fileName);
        this.owner = owner;
    }


    public boolean isOffScreen() {
        return position.x + position.radius < 0 ||
            position.x - position.radius > Gdx.graphics.getWidth() ||
            position.y + position.radius < 0 ||
            position.y - position.radius > Gdx.graphics.getHeight();
    }

    public void dispose() {
        shapeRenderer.dispose();
        graphics.dispose();
    }
}


