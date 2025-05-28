package io.github.meowhalla.structure.logic;

import lombok.Getter;

import java.util.function.Consumer;

public class ComboStep {
    @Getter
    private final float duration;
    private final Runnable onEnter;
    private final Consumer<Float> onUpdate;

    @Getter
    private boolean entered = false;
    private float time = 0f;

    public ComboStep(float duration, Runnable onEnter, Consumer<Float> onUpdate) {
        this.duration = duration;
        this.onEnter = onEnter;
        this.onUpdate = onUpdate;
    }

    public void update(float delta) {
        if (!entered) {
            onEnter.run();
            entered = true;
        }

        time += delta;
        if (onUpdate != null) {
            onUpdate.accept(delta);
        }
    }

    public boolean isFinished() {
        return time >= duration;
    }
}


