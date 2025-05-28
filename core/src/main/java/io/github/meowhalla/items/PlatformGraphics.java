package io.github.meowhalla.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.meowhalla.structure.graphics.Graphics;

public class PlatformGraphics implements Graphics {
    private final PlatformContext ctx;
    private final Texture sheet;
    private final TextureRegion sheetRegion;

    public PlatformGraphics(PlatformContext ctx, String fileName) {
        this.ctx = ctx;
        sheet = new Texture(Gdx.files.internal(fileName));
        TextureRegion[][] frames = TextureRegion.split(sheet, 112/7, 96/6);
        sheetRegion = frames[0][0];
    }

    public void render(SpriteBatch batch) {
        batch.draw(sheetRegion, ctx.getBounds().x, ctx.getBounds().y, ctx.getBounds().width, ctx.getBounds().height);
    }

    public void dispose() {
        sheet.dispose();
    }
}
