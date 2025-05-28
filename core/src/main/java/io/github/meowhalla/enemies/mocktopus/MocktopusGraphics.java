package io.github.meowhalla.enemies.mocktopus;

import io.github.meowhalla.structure.contexts.CharacterContext;
import io.github.meowhalla.structure.graphics.AnimationSet;
import io.github.meowhalla.structure.graphics.CharacterGraphics;
import io.github.meowhalla.structure.states.Action;

public class MocktopusGraphics extends CharacterGraphics {

    public MocktopusGraphics(CharacterContext ctx, String fileName, float scaling, int rowsNum, int colsNum) {
        super(ctx, fileName, scaling, rowsNum, colsNum);
        animationSet = new AnimationSet(frames[0][0]);
        animationSet.put(Action.IDLE, frames[0], 0.2f, 0, 3);
        animationSet.put(Action.CASTING, frames[0], 0.2f, 0, 3);
        animationSet.put(Action.ATTACK, frames[0], 0.2f, 0, 3);
        animationSet.put(Action.JUMP, frames[0], 0.2f, 0, 3);
        animationSet.put(Action.RUN, frames[0], 0.2f, 0, 3);
        animationSet.put(Action.HIT, frames[0], 0.2f, 0, 3);
        animationSet.put(Action.DEAD, frames[0], 0.2f, 0, 3);
    }


}
