package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Direction;

import java.util.List;

public class WindingProjectileFactory extends ProjectileFactory {
    private List<Vector3> vectors;

    public WindingProjectileFactory(int power, String fileName, float radius, List<Vector3> vectors) {
        super(power, fileName, radius);
        this.vectors = vectors;
    }

    @Override
    public List<ProjectileContext> createProjectiles(CharacterContext owner) {
        Vector2 width = new Vector2(radius, 0);
        Vector2 origin = owner.getDirection() == Direction.RIGHT ? owner.rightBorder() : owner.leftBorder().cpy().sub(width);
        return List.of(new WindingProjectile(origin, vectors, fileName, power, radius));
    }
}
