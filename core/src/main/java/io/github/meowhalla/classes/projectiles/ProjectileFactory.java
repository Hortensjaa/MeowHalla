package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Direction;

import java.util.List;


abstract public class ProjectileFactory {

    // projectile stats
    protected int power;
    protected String fileName;
    protected float radius;

    public ProjectileFactory(int power, String fileName, float radius) {
        this.power = power;
        this.fileName = fileName;
        this.radius = radius;
    }

    private Vector2 calculateOrigin(CharacterContext owner) {
        Vector2 width = new Vector2(radius, 0);
        return owner.getDirection() == Direction.RIGHT ? owner.rightBorder() : owner.leftBorder().cpy().sub(width);
    }

    public abstract List<ProjectileContext> createProjectiles(CharacterContext owner, Vector2 origin);

    public List<ProjectileContext> createProjectiles(CharacterContext owner) {
        return createProjectiles(owner, calculateOrigin(owner));
    }
}
