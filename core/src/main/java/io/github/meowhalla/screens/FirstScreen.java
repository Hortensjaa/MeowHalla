package io.github.meowhalla.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.meowhalla.contexts.GameContext;


public class FirstScreen implements Screen {
    private OrthographicCamera camera;
    private Viewport viewport;
    private HUDRenderer hudRenderer;

    private GameContext ctx;
    private SpriteBatch batch;

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        ctx = new GameContext();
        hudRenderer = new HUDRenderer(ctx);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(ctx.getCamera().combined);
        batch.begin();
        ctx.update(delta);
        ctx.render(batch);
        batch.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        hudRenderer.renderPlayer();
        hudRenderer.renderBoss();
    }

    @Override
    public void resize(int width, int height) {
        ctx.resize(width, height);
    }

    @Override public void pause() {}

    @Override public void resume() {}

    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        ctx.dispose();
    }
}

