package io.github.meowhalla.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.meowhalla.contexts.BossContext;
import io.github.meowhalla.contexts.DynamicObject;
import io.github.meowhalla.contexts.PlatformContext;
import io.github.meowhalla.contexts.PlayerContext;
import io.github.meowhalla.data.wolf_boss.WolfContext;
import io.github.meowhalla.player.PlayerLogic;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.PlayerState;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public class GameContext {
    @Getter
    private static GameContext instance;

    private final PlayerContext player;
    private final BossContext boss;
    private final List<ProjectileContext> projectiles = new ArrayList<>();
    private final List<PlatformContext> platforms = new ArrayList<>();
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public GameContext() {
        instance = this;

        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        player = new PlayerContext(this);
        boss = new WolfContext(this);

        platforms.add(new PlatformContext(200, 150, 300, 20));
        platforms.add(new PlatformContext(600, 250, 200, 20));
    }

    public void update(float delta) {
        player.update(delta);
        boss.update(delta);

        if (player.getAction() == Action.ATTACK) {
            List<ProjectileContext> fired = ((PlayerLogic) player.logic).shoot();
            if (fired != null) {
                projectiles.addAll(fired);
            }
        }

        checkCollisions(delta);
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
        Iterator<ProjectileContext> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            ProjectileContext p = (ProjectileContext) iterator.next();
            p.update(delta);

            if (p.isOffScreen()) {
                iterator.remove();
                continue;
            }

            Circle hitbox = p.getHitbox();

            if (p.getOwner() != player
                && Intersector.overlaps(hitbox, player.getPosition())
                && !player.state.isInvincible()) {
                player.updateHp(-p.getPower());
                ((PlayerState) player.state).resetNoHitTime();
                iterator.remove();
                continue;
            }

            if (p.getOwner() != boss && Intersector.overlaps(hitbox, boss.getPosition())) {
                boss.updateHp(-p.getPower());
                iterator.remove();
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


