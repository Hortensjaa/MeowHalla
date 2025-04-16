package io.github.meowhalla.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.states.Direction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public class GameContext {
    private final PlayerContext player;
    private final List<DynamicObject> projectiles = new ArrayList<>();
    private final WeaponContext weapon;
    private float timeSinceLastShot;
    private final Pool<ProjectileContext> bulletPool = new Pool<>() {
        @Override
        protected ProjectileContext newObject() {
            return weapon.createProjectile();
        }
    };


    public GameContext() {
        timeSinceLastShot = 0;
        player = new PlayerContext();
        weapon = WeaponType.MAGIC_CRUSHER.data;
    }

    public void update(float delta) {
        timeSinceLastShot += delta;

        if (Gdx.input.isKeyPressed(Input.Keys.X) && timeSinceLastShot >= weapon.cooldown()) {
            ProjectileContext bullet = bulletPool.obtain();
            float vx = weapon.velocity().x;
            System.out.println(player.state.getDirection());
            if (player.state.getDirection() == Direction.RIGHT) {
                bullet.reset(player.rightBorder().x, player.rightBorder().y, vx, 0f);
            } else {
                bullet.reset(player.leftBorder().x, player.leftBorder().y, -vx, 0f);
            }
            projectiles.add(bullet);
            timeSinceLastShot = 0f;
        }

        player.update(delta);

        Iterator<DynamicObject> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            ProjectileContext p = (ProjectileContext) iterator.next();
            p.update(delta);

            if (p.isOffScreen()) {
                iterator.remove();
                bulletPool.free(p);
            }
        }
    }

    public void render(SpriteBatch batch) {
        player.render(batch);
        for (DynamicObject p : projectiles) p.render(batch);
    }

    public void dispose() {
        player.dispose();
        for (DynamicObject p : projectiles) p.dispose();
    }
}

