package io.github.meowhalla.data.storm;

import io.github.meowhalla.contexts.CharacterContext;
import io.github.meowhalla.graphics.AnimationSet;
import io.github.meowhalla.graphics.CharacterGraphics;
import io.github.meowhalla.states.Action;

public class StormGraphics extends CharacterGraphics {

    public StormGraphics(CharacterContext ctx, String fileName, float scaling, int rowsNum, int colsNum) {
        super(ctx, fileName, scaling, rowsNum, colsNum);
        animationSet = new AnimationSet(frames[2][0]);
        animationSet.put(Action.IDLE, frames[2], 0.5f, 0, 1);
        animationSet.put(Action.CASTING, frames[1], 0.5f, 0, 1);
        animationSet.put(Action.ATTACK, frames[0], 0.5f, 0, 1);
    }

}
