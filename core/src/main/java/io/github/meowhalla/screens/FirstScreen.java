package io.github.meowhalla.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.meowhalla.contexts.PlayerContext;

import java.awt.*;


public class FirstScreen implements Screen {

    private PlayerContext player;
    private SpriteBatch batch;

    @Override
    public void show() {
        batch = new SpriteBatch();
        player = new PlayerContext();
    }

    @Override
    public void render(float delta) {
        player.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override public void resize(int width, int height) {}

    @Override public void pause() {}

    @Override public void resume() {}

    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}

