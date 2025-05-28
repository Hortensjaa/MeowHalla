package io.github.meowhalla.enemies.mocktopus;

import io.github.meowhalla.game.GameContext;
import io.github.meowhalla.structure.logic.Combo;
import io.github.meowhalla.structure.logic.ComboFactory;
import io.github.meowhalla.structure.logic.ComboStepFactory;

import java.util.List;
import java.util.function.Supplier;

public class MocktopusComboFactory implements ComboFactory {
    static MocktopusContext ctx;
    static GameContext game;
    private int lastIndex = 0;

    public MocktopusComboFactory(MocktopusContext ctx) {
        MocktopusComboFactory.ctx = ctx;
        MocktopusComboFactory.game = ctx.getGameContext();
    }

    public static Combo chasingCombo() {
        return new Combo(List.of(
            ComboStepFactory.casting(1f, ctx, MocktopusWeapons.CHASING_THE_SUN.data),
            ComboStepFactory.continuousFire(15f, ctx, game, 5),
            ComboStepFactory.idle(1f, ctx)
        ));
    }

    private final List<Supplier<Combo>> combos = List.of(
        MocktopusComboFactory::chasingCombo
    );

    @Override
    public Combo getNext() {
        return combos.get(lastIndex).get();
    }

}
