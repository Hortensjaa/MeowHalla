package io.github.meowhalla.structure.graphics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.meowhalla.structure.states.Action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnimationSet {
    private final Animation<TextureRegion> defaultAnimation;
    private final Map<Action, Animation<TextureRegion>> animations = new HashMap<>();

    public AnimationSet(TextureRegion defaultFrame) {
        this.defaultAnimation = new Animation<>(1f, defaultFrame);
        defaultAnimation.setPlayMode(Animation.PlayMode.LOOP);
        for (Action action : Action.values()) {
            animations.put(action, defaultAnimation);
        }
    }

    public void put(Action action, TextureRegion[] frames, float frameDuration, int rangeFrom, int rangeTo) {
        Animation<TextureRegion> anim = new Animation<>(frameDuration, Arrays.copyOfRange(frames, rangeFrom, rangeTo));
        anim.setPlayMode(Animation.PlayMode.LOOP);
        animations.put(action, anim);
    }

    public void put(Action action, TextureRegion[] frames, float frameDuration) {
        Animation<TextureRegion> anim = new Animation<>(frameDuration, frames);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        animations.put(action, anim);
    }

    public Animation<TextureRegion> get(Action action) {
        return animations.get(action);
    }
}

