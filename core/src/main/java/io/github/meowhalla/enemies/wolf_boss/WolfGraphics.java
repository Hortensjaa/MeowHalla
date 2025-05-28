package io.github.meowhalla.enemies.wolf_boss;

import io.github.meowhalla.structure.contexts.CharacterContext;
import io.github.meowhalla.structure.graphics.AnimationSet;
import io.github.meowhalla.structure.graphics.CharacterGraphics;
import io.github.meowhalla.structure.states.Action;

public class WolfGraphics extends CharacterGraphics {

    public WolfGraphics(CharacterContext ctx, String fileName, float scaling, int rowsNum, int colsNum) {
        super(ctx, fileName, scaling, rowsNum, colsNum);
        animationSet = new AnimationSet(frames[0][0]);
        animationSet.put(Action.IDLE, frames[0], 0.2f, 0, 6);
        animationSet.put(Action.CASTING, frames[0], 0.2f, 0, 6);
        animationSet.put(Action.ATTACK, frames[1], 0.2f, 1, 4);
        animationSet.put(Action.JUMP, frames[1], 0.3f, 0, 6);
        animationSet.put(Action.RUN, frames[1], 0.3f, 0, 6);
        animationSet.put(Action.HIT, frames[2], 0.3f, 0, 5);
        animationSet.put(Action.DEAD, frames[3], 0.3f);
    }


}
