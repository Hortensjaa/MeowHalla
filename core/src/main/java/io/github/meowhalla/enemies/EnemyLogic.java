package io.github.meowhalla.enemies;


import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.structure.character.CharacterLogic;
import io.github.meowhalla.structure.combo_logic.Combo;
import io.github.meowhalla.structure.combo_logic.ComboFactory;

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
