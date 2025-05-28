package io.github.meowhalla.enemies.storm;

import io.github.meowhalla.structure.contexts.CharacterContext;
import io.github.meowhalla.structure.graphics.AnimationSet;
import io.github.meowhalla.structure.graphics.CharacterGraphics;
import io.github.meowhalla.structure.states.Action;

public class StormGraphics extends CharacterGraphics {

    public StormGraphics(CharacterContext ctx, String fileName, float scaling, int rowsNum, int colsNum) {
        super(ctx, fileName, scaling, rowsNum, colsNum);
        animationSet = new AnimationSet(frames[2][0]);
        animationSet.put(Action.IDLE, frames[2], 0.5f, 0, 1);
        animationSet.put(Action.CASTING, frames[1], 0.5f, 0, 1);
        animationSet.put(Action.ATTACK, frames[0], 0.5f, 0, 1);
    }

}
