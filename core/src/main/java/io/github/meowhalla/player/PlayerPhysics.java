package io.github.meowhalla.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import io.github.meowhalla.items.PlatformContext;
import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.structure.physics.CharacterPhysics;
import io.github.meowhalla.game.settings.KeyBindings;


public class PlayerPhysics extends CharacterPhysics {

    public PlayerPhysics(CharacterContext ctx) {
        this.ctx = ctx;
    }

    public void update(float delta) {
        float worldWidth = ctx.getGameContext().getViewport().getWorldWidth();
        Rectangle rect = ctx.getRectangle();

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

        float previousY = rect.y - velocity.y * delta;

        for (PlatformContext platform : ctx.getGameContext().getPlatforms()) {
            Rectangle platformBounds = platform.getBounds();

            boolean falling = velocity.y <= 0;
            boolean wasAbove = previousY >= platformBounds.y + platformBounds.height;
            boolean isNowBelowTop = rect.y <= platformBounds.y + platformBounds.height;

            boolean horizontallyAligned =
                rect.x + rect.width > platformBounds.x &&
                    rect.x < platformBounds.x + platformBounds.width;

            if (falling && wasAbove && isNowBelowTop && horizontallyAligned) {
                rect.y = platformBounds.y + platformBounds.height;
                velocity.y = 0;
                isGrounded = true;
                break;
            }
        }

        if (rect.x < 0) rect.x = 0;
        if (rect.x + rect.width > worldWidth) {
            rect.x = worldWidth - rect.width;
        }
    }
}


