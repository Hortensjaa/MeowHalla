package io.github.meowhalla.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.structure.graphics.AnimationSet;
import io.github.meowhalla.structure.graphics.CharacterGraphics;
import io.github.meowhalla.structure.states.Action;

public class PlayerGraphics extends CharacterGraphics {
    PlayerState state;

    public PlayerGraphics(CharacterContext ctx, String fileName, float scaling, int rowsNum, int colsNum) {
        super(ctx, fileName, scaling, rowsNum, colsNum);
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
