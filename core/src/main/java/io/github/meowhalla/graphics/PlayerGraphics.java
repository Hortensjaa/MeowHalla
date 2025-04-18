package io.github.meowhalla.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.meowhalla.classes.characters.PlayerContext;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.PlayerState;

public class PlayerGraphics extends CharacterGraphics {
    PlayerState state;

    public PlayerGraphics(PlayerContext ctx, String fileName, float scaling) {
        super(ctx, fileName);
        TextureRegion[][] frames = TextureRegion.split(sheet, 290, 261);
        ctx.getPosition().setWidth(290 * scaling);
        ctx.getPosition().setHeight(261 * scaling);
        animationSet = new AnimationSet(frames[0][0]);
        animationSet.put(Action.RUN, frames[0], 0.3f, 0, 2);
        state = (PlayerState) ctx.state;
    }

    @Override
    public void render(SpriteBatch batch) {
        boolean visible = true;
        if (state.isInvincible()) {
            float t = state.getTimeSinceLastHit();
            visible = ((int) (t * 7)) % 2 != 0;
        }

        if (visible) {
            super.render(batch);
        }
    }
}
