package io.github.meowhalla.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Action;

public class WolfBossGraphics extends CharacterGraphics {

    public WolfBossGraphics(CharacterContext ctx, String fileName, float scaling) {
        super(ctx, fileName);
        int tileWidth = 448/7;
        int tileHeight = 256/4;
        TextureRegion[][] frames = TextureRegion.split(sheet, tileWidth, tileHeight);
        ctx.getPosition().setWidth(tileWidth * scaling);
        ctx.getPosition().setHeight(tileHeight * scaling);
        animationSet = new AnimationSet(frames[0][0]);
        animationSet.put(Action.IDLE, frames[0], 0.2f, 0, 6);
        animationSet.put(Action.JUMP, frames[1], 0.3f, 0, 6);
        animationSet.put(Action.RUN, frames[1], 0.3f, 0, 6);
        animationSet.put(Action.HIT, frames[2], 0.3f, 0, 5);
        animationSet.put(Action.DEAD, frames[3], 0.3f);
    }


}
