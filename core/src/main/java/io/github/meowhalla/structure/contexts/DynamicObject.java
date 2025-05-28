package io.github.meowhalla.structure.contexts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface DynamicObject {
    void update(float delta);
    void render(SpriteBatch batch);
    void dispose();

    float getX();
    float getY();
    float getWidth();
    float getHeight();
}
