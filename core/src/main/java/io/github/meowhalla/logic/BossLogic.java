package io.github.meowhalla.logic;


import io.github.meowhalla.classes.characters.CharacterContext;

public class BossLogic extends CharacterLogic {
    private Combo currentCombo = null;
    private final ComboFactory comboFactory;

    public BossLogic(CharacterContext ctx, int max_hp, ComboFactory factory) {
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
