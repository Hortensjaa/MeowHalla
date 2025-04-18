package io.github.meowhalla.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Action;

public class PlayerGraphics extends CharacterGraphics {

    public PlayerGraphics(CharacterContext ctx, String fileName, float scaling) {
        super(ctx, fileName);
        TextureRegion[][] frames = TextureRegion.split(sheet, 290, 261);
        ctx.getPosition().setWidth(290 * scaling);
        ctx.getPosition().setHeight(261 * scaling);
        animationSet = new AnimationSet(frames[0][0]);
        animationSet.put(Action.RUN, frames[0], 0.3f, 0, 2);
    }
}
