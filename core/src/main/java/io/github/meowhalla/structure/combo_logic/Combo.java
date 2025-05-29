package io.github.meowhalla.structure.combo_logic;

import lombok.Getter;

import java.util.List;

public class Combo {
    private final List<ComboStep> steps;
    private int currentStep = 0;
    @Getter private boolean finished = false;

    public Combo(List<ComboStep> steps) {
        this.steps = steps;
    }

    public void update(float delta) {
        if (finished) return;

        ComboStep step = steps.get(currentStep);
        step.update(delta);

        if (step.isFinished()) {
            currentStep++;
            if (currentStep >= steps.size()) {
                finished = true;
            }
        }
    }
}


