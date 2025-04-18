package io.github.meowhalla.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.data.KeyBindings;


public class PlayerPhysics extends CharacterPhysics {

    public PlayerPhysics(CharacterContext ctx) {
        this.ctx = ctx;
    }

    public void update(float delta) {
        float worldWidth = ctx.getGameContext().getViewport().getWorldWidth();
        Rectangle rect = ctx.state.getPosition();

        if (!Gdx.input.isKeyPressed(KeyBindings.LEFT.getKeyCode()) &&
            !Gdx.input.isKeyPressed(KeyBindings.RIGHT.getKeyCode())) {
            velocity.x = 0;
        }

        velocity.y -= gravity * delta;

        rect.y += velocity.y * delta;
        rect.x += velocity.x * delta;

        if (rect.y <= 0) {
            rect.y = 0;
            velocity.y = 0;
            isGrounded = true;
        } else {
            isGrounded = false;
        }

        if (rect.x < 0) rect.x = 0;
        if (rect.x + rect.width > worldWidth) {
            rect.x = worldWidth - rect.width;
        }
    }
}


