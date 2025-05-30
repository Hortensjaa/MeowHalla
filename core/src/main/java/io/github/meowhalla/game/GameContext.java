package io.github.meowhalla.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.meowhalla.Main;
import io.github.meowhalla.enemies.EnemyContext;
import io.github.meowhalla.enemies.storm.StormContext;
import io.github.meowhalla.enemies.wolf_boss.WolfContext;
import io.github.meowhalla.game.screens.VictoryScreen;
import io.github.meowhalla.game.screens.YouDiedScreen;
import io.github.meowhalla.items.PlatformContext;
import io.github.meowhalla.player.PlayerContext;
import io.github.meowhalla.player.PlayerLogic;
import io.github.meowhalla.player.PlayerState;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.structure.DynamicObject;
import io.github.meowhalla.structure.states.Action;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
public class GameContext {
    @Getter
    private static GameContext instance;

    private final PlayerContext player;
    private final EnemyContext boss;
    private final List<EnemyContext> enemies = new ArrayList<>();
    private final List<ProjectileContext> projectiles = new ArrayList<>();
    private final List<PlatformContext> platforms = new ArrayList<>();
    private final OrthographicCamera camera;
    private final Viewport viewport;

    public GameContext(Weapon playerWeapon) {
        instance = this;

        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        player = new PlayerContext(playerWeapon);
        boss = new WolfContext();

        enemies.add(new StormContext());

        platforms.add(new PlatformContext(200, 150, 300, 20));
        platforms.add(new PlatformContext(600, 250, 200, 20));
    }

    public void update(float delta) {
        player.update(delta);
        boss.update(delta);
        for (EnemyContext e : enemies) e.update(delta);

        if (player.logic.getHp() <= 0f) {
            Main.getInstance().setScreen(new YouDiedScreen());
        } else if (boss.logic.getHp() <= 0f) {
            Main.getInstance().setScreen(new VictoryScreen());
        }

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
        for (ProjectileContext p : projectiles) p.render(batch);
        for (PlatformContext p : platforms) p.render(batch);
        for (EnemyContext e : enemies) e.render(batch);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
    }

    public void checkCollisions(float delta) {
        Iterator<ProjectileContext> iterator = projectiles.iterator();
        while (iterator.hasNext()) {
            ProjectileContext p = iterator.next();
            p.update(delta);

            if (p.isOffScreen() || p.timeToDestroy()) {
                iterator.remove();
                continue;
            }

            Circle hitbox = p.getHitbox();

            if (!p.isPlayers_projectile()
                && Intersector.overlaps(hitbox, player.getRectangle())
                && !player.state.isInvincible()) {
                player.updateHp(-p.getPower());
                ((PlayerState) player.state).resetNoHitTime();
                iterator.remove();
                continue;
            }

            if (p.isPlayers_projectile() && Intersector.overlaps(hitbox, boss.getRectangle())) {
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


