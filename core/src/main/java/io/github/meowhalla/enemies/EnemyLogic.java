package io.github.meowhalla.enemies;


import io.github.meowhalla.structure.contexts.CharacterContext;
import io.github.meowhalla.structure.contexts.CharacterLogic;
import io.github.meowhalla.structure.logic.Combo;
import io.github.meowhalla.structure.logic.ComboFactory;

public class EnemyLogic extends CharacterLogic {
    private Combo currentCombo = null;
    private final ComboFactory comboFactory;

    public EnemyLogic(CharacterContext ctx, int max_hp, ComboFactory factory) {
        super(ctx, max_hp);
        comboFactory = factory;
    }

    public void update(float delta) {
        if (currentCombo == null || currentCombo.isFinished()) {
            currentCombo = comboFactory.getNext();
        }
        currentCombo.update(delta);
    }
}
