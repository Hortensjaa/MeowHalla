package io.github.meowhalla.classes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.classes.characters.PlayerContext;
import io.github.meowhalla.classes.characters.WolfBossContext;
import io.github.meowhalla.classes.projectiles.ProjectileContext;
import io.github.meowhalla.states.Action;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public class GameContext {
    private final PlayerContext player;
    private final CharacterContext boss;
    private final List<DynamicObject> projectiles = new ArrayList<>();
    private final List<PlatformContext> platforms = new ArrayList<>();
    private final OrthographicCamera camera;
    private final Viewport viewport;


    private final Pool<ProjectileContext> bulletPool = new Pool<>() {
        @Override
        protected ProjectileContext newObject() {
            return new ProjectileContext(new Vector2(), new Vector2(), "weapons/Arcane Bolt.png", 1, 10f);
        }
    };


    public GameContext() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        player = new PlayerContext(this);
        boss = new WolfBossContext(this);

        platforms.add(new PlatformContext(200, 150, 300, 20));
        platforms.add(new PlatformContext(600, 300, 200, 20));
        platforms.add(new PlatformContext(400, 490, 250, 20));


    }

    public void update(float delta) {
        player.update(delta);
        boss.update(delta);

        shoot(player);
        shoot(boss);

        checkCollisions(delta);
    }

    private void shoot(CharacterContext character) {
        if (character.getAction() == Action.ATTACK) {
            List<ProjectileContext> fired = character.shoot(bulletPool);
            if (fired != null) {
                projectiles.addAll(fired);
            }
        }
    }

    public void render(SpriteBatch batch) {
        player.render(batch);
        boss.render(batch);
        for (DynamicObject p : projectiles) p.render(batch);
        for (PlatformContext p : platforms) p.render(batch);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }

    public void checkCollisions(float delta) {

        Iterator<DynamicObject> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            ProjectileContext p = (ProjectileContext) iterator.next();
            p.update(delta);

            if (p.isOffScreen()) {
                iterator.remove();
                bulletPool.free(p);
                continue;
            }

            Circle hitbox = p.getPosition();

            if (p.getOwner() != player && Intersector.overlaps(hitbox, player.getPosition())) {
                player.state.updateHp(-p.getPower());
                iterator.remove();
                bulletPool.free(p);
                continue;
            }

            if (p.getOwner() != boss && Intersector.overlaps(hitbox, boss.getPosition())) {
                boss.state.updateHp(-p.getPower());
                iterator.remove();
                bulletPool.free(p);
            }
        }
    }

    public void dispose() {
        player.dispose();
        boss.dispose();
        for (DynamicObject p : projectiles) p.dispose();
        for (PlatformContext p : platforms) p.dispose();
    }
}

