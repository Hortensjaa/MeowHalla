package io.github.meowhalla.graphics;

import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Action;

public class WolfBossGraphics extends CharacterGraphics {

    public WolfBossGraphics(CharacterContext ctx, String fileName, float scaling, int rowsNum, int colsNum) {
        super(ctx, fileName, scaling, rowsNum, colsNum);
        animationSet = new AnimationSet(frames[0][0]);
        animationSet.put(Action.IDLE, frames[0], 0.2f, 0, 6);
        animationSet.put(Action.ATTACK, frames[1], 0.2f, 1, 4);
        animationSet.put(Action.JUMP, frames[1], 0.3f, 0, 6);
        animationSet.put(Action.RUN, frames[1], 0.3f, 0, 6);
        animationSet.put(Action.HIT, frames[2], 0.3f, 0, 5);
        animationSet.put(Action.DEAD, frames[3], 0.3f);
    }


}
