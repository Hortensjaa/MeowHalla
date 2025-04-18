package io.github.meowhalla.classes.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.states.Direction;

import java.util.ArrayList;
import java.util.List;

public class MultipleShotBehavior implements WeaponBehavior {
    private final List<Float> angles;

    public MultipleShotBehavior(List<Float> angles) {
        this.angles = angles;
    }

    @Override
    public List<ProjectileContext> shoot (Vector2 origin, Direction direction, WeaponContext data, Pool<ProjectileContext> pool) {
        float speed = data.velocity().len(); // base speed

        List<ProjectileContext> shots = new ArrayList<>();

        for (float angleOffset : angles) {
            float angle = direction == Direction.RIGHT ? angleOffset : 180 - angleOffset;

            Vector2 velocity = new Vector2(speed, 0).setAngleDeg(angle);

            ProjectileContext bullet = pool.obtain();
            bullet.reset(new Vector2(origin), velocity, data.fileName(), data.power(), data.radius());
            shots.add(bullet);
        }

        return shots;
    }
}

