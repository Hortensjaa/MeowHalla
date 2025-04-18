package io.github.meowhalla.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.meowhalla.classes.GameContext;

public class HUDRenderer {
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final BitmapFont font = new BitmapFont();
    private final GlyphLayout layout = new GlyphLayout();
    private final SpriteBatch fontBatch = new SpriteBatch();
    private final GameContext ctx;


    public HUDRenderer(GameContext ctx) {
        this.ctx = ctx;
    }

    public void renderPlayer() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        float maxWidth = 200;
        float barHeight = 20;
        float x = 20;
        float y = Gdx.graphics.getHeight() - 40;

        float percent = (float) ctx.getPlayer().state.getHp() / ctx.getPlayer().state.getMax_hp();
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, maxWidth, barHeight);
        if (ctx.getPlayer().state.getHp() >= 0) {
            shapeRenderer.setColor(Color.LIME);
            shapeRenderer.rect(x, y, maxWidth * percent, barHeight);
        }

        shapeRenderer.end();
    }

    public void renderBoss() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float maxWidth = 600;
        float barHeight = 20;
        float x = (Gdx.graphics.getWidth() - maxWidth) / 2f;
        float y = Gdx.graphics.getHeight() - 70;

        float percent = (float) ctx.getBoss().state.getHp() / ctx.getBoss().state.getMax_hp();

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x, y, maxWidth, barHeight);

        if (ctx.getBoss().state.getHp() >= 0) {
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(x, y, maxWidth * percent, barHeight);
        }

        shapeRenderer.end();

        SpriteBatch textBatch = new SpriteBatch();
        textBatch.setProjectionMatrix(new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()).combined);
        textBatch.begin();

        String bossName = ctx.getBoss().getName();
        float textY = y + barHeight + layout.height + 20;

        fontBatch.begin();
        font.draw(fontBatch, bossName, x, textY);
        fontBatch.end();
    }



    public void dispose() {
        shapeRenderer.dispose();
    }
}

