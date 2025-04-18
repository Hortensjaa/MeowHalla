package io.github.meowhalla.classes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.meowhalla.graphics.PlatformGraphics;
import lombok.Getter;

@Getter
public class PlatformContext {
    private final Rectangle bounds;
    private final PlatformGraphics graphics;

    public PlatformContext(float x, float y, float width, float height) {
        this.bounds = new Rectangle(x, y, width, height);
        graphics = new PlatformGraphics(this, "platforms/wood_moss_alt_tileset_1.png");
    }

    public void render(SpriteBatch batch) {
        graphics.render(batch);
    }

    public void dispose() {
        graphics.dispose();
    }
}

