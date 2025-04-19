package io.github.meowhalla.classes.projectiles;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Direction;

import java.util.ArrayList;
import java.util.List;


public class MultipleBasicProjectileFactory extends ProjectileFactory {

    // projectile stats
    private final Vector2 velocity;
    private float gravity;

    // factory stats
    protected List<Float> angles;

    public MultipleBasicProjectileFactory(int power, String fileName, float radius, Vector2 velocity, float gravity, List<Float> angles) {
        super(power, fileName, radius);
        this.velocity = velocity;
        this.gravity = gravity;
        this.angles = angles;
    }

    public MultipleBasicProjectileFactory(int power, String fileName, float radius, Vector2 velocity, List<Float> angles) {
        super(power, fileName, radius);
        this.velocity = velocity;
        this.angles = angles;
    }

    @Override
    public List<ProjectileContext> createProjectiles(CharacterContext owner, Vector2 origin) {
        float speed = velocity.len();
        List<ProjectileContext> shots = new ArrayList<>();
        for (float angleOffset : angles) {
            float angle = owner.getDirection() == Direction.RIGHT ? angleOffset : 180 - angleOffset;
            Vector2 v = new Vector2(speed, 0).setAngleDeg(angle);
            BasicProjectile bullet = new BasicProjectile(origin, v, fileName, power, radius, gravity);
            shots.add(bullet);
        }
        return shots;
    }
}
