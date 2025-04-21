package io.github.meowhalla.contexts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface DynamicObject {
    void update(float delta);
    void render(SpriteBatch batch);
    void dispose();
}
