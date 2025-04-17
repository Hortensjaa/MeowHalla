package io.github.meowhalla.classes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
    private final OrthographicCamera camera;
    private final Viewport viewport;


    private final Pool<ProjectileContext> bulletPool = new Pool<>() {
        @Override
        protected ProjectileContext newObject() {
            return weapon.createProjectile();
        }
    };


    public GameContext() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        timeSinceLastShot = 0;
        player = new PlayerContext(this);
        weapon = WeaponType.SHURIKENS_OF_LIGHT.data;
    }

    public void update(float delta) {
        timeSinceLastShot += delta;

        if (Gdx.input.isKeyPressed(Input.Keys.X) && timeSinceLastShot >= weapon.cooldown()) {
            Vector2 origin = player.state.getDirection() == Direction.RIGHT
                ? player.rightBorder()
                : player.leftBorder();

            List<ProjectileContext> fired = weapon.behavior()
                .shoot(origin, player.state.getDirection(), weapon, bulletPool);
            projectiles.addAll(fired);
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

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }


    public void dispose() {
        player.dispose();
        for (DynamicObject p : projectiles) p.dispose();
    }
}

