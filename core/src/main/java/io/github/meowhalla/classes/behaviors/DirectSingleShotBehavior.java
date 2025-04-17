package io.github.meowhalla.classes.behaviors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import io.github.meowhalla.classes.ProjectileContext;
import io.github.meowhalla.classes.WeaponContext;
import io.github.meowhalla.states.Direction;

import java.util.List;

public class DirectSingleShotBehavior implements WeaponBehavior {
    @Override
    public List<ProjectileContext> shoot(Vector2 origin, Direction direction, WeaponContext data, Pool<ProjectileContext> pool) {
        float vx = direction == Direction.RIGHT ? data.velocity().x : -data.velocity().x;
        Vector2 velocity = new Vector2(vx, data.velocity().y);

        ProjectileContext bullet = pool.obtain();
        bullet.reset(new Vector2(origin), velocity, data.fileName(), data.power(), data.radius());

        return List.of(bullet);
    }
}


