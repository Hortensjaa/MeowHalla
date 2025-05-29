package io.github.meowhalla.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.meowhalla.structure.DynamicObject;
import lombok.Getter;

@Getter
public class PlatformContext implements DynamicObject {
    private final Rectangle bounds;
    private final PlatformGraphics graphics;

    public PlatformContext(float x, float y, float width, float height) {
        this.bounds = new Rectangle(x, y, width, height);
        graphics = new PlatformGraphics(this, "platforms/wood_moss_alt_tileset_1.png");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        graphics.render(batch);
    }

    @Override
    public void dispose() {
        graphics.dispose();
    }

    @Override
    public float getX() {
        return bounds.x;
    }

    @Override
    public float getY() {
        return bounds.y;
    }

    @Override
    public float getWidth() {
        return bounds.width;
    }

    @Override
    public float getHeight() {
        return bounds.height;
    }

    @Override
    public void setX(float v) {
        bounds.x = v;
    }

    @Override
    public void setY(float v) {
        bounds.y = v;
    }

    @Override
    public void setWidth(float v) {
        bounds.width = v;
    }

    @Override
    public void setHeight(float v) {
        bounds.height = v;
    }
}

