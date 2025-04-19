package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import io.github.meowhalla.classes.characters.CharacterContext;

import java.util.List;

public class WindingProjectileFactory extends ProjectileFactory {
    private List<Vector3> vectors;

    public WindingProjectileFactory(int power, String fileName, float radius, List<Vector3> vectors) {
        super(power, fileName, radius);
        this.vectors = vectors;
    }

    @Override
    public List<ProjectileContext> createProjectiles(CharacterContext owner, Vector2 origin) {
        return List.of(new WindingProjectile(origin, vectors, fileName, power, radius));
    }
}
