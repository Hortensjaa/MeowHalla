package io.github.meowhalla.structure;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface DynamicObject {
    void update(float delta);
    void render(SpriteBatch batch);
    void dispose();

    float getX();
    float getY();
    float getWidth();
    float getHeight();

    void setX(float v);
    void setY(float v);
    void setWidth(float v);
    void setHeight(float v);

    default Vector2 getPosition() {
        return new Vector2(getX(), getY());
    }

    default Vector2 getMeasurements() {
        return new Vector2(getWidth(), getHeight());
    }

    default float leftBorder() {
        return getX();
    }

    default float rightBorder() {
        return getX() + getWidth();
    }

    default float getTop() {
        return getY() + getHeight();
    }

    default float getBottom() {
        return getY();
    }

    default float getCenterX() {
        return getX() + getWidth() / 2;
    }

    default float getCenterY() {
        return getY() + getHeight() / 2;
    }

    default Vector2 getCenter() {
        return new Vector2(getCenterX(), getCenterY());
    }
}
