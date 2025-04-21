package io.github.meowhalla.data.wolf_boss;

import io.github.meowhalla.contexts.GameContext;
import io.github.meowhalla.logic.Combo;
import io.github.meowhalla.logic.ComboFactory;
import io.github.meowhalla.logic.ComboStepFactory;
import io.github.meowhalla.projectiles.base_transformation.Translation;

import java.util.List;
import java.util.function.Supplier;


public class WolfComboFactory implements ComboFactory {
    static WolfContext ctx;
    static GameContext game;

    public WolfComboFactory(WolfContext ctx) {
        WolfComboFactory.ctx = ctx;
        WolfComboFactory.game = ctx.getGameContext();
    }

    public static Combo makeZigzagCombo() {
        return new Combo(List.of(
            ComboStepFactory.casting(1f, ctx, WolfWeapons.ZIGZAG.data),
            ComboStepFactory.continuousFire(5f, ctx, game, 0.5f),
            ComboStepFactory.casting(1f, ctx, WolfWeapons.REVERSED_ZIGZAG.data),
            ComboStepFactory.continuousFire(5f, ctx, game, 0.5f),
            ComboStepFactory.idle(1f, ctx)
        ));
    }

    public static Combo makeFanOfOrbsCombo() {
        return new Combo(List.of(
            ComboStepFactory.casting(1f, ctx, WolfWeapons.FAN_OF_ORBS.data),
            ComboStepFactory.continuousFire(5f, ctx, game, 0.8f),
            ComboStepFactory.idle(1f, ctx)
        ));
    }

    public static Combo makeVolleyCombo() {
        return new Combo(List.of(
            ComboStepFactory.casting(1f, ctx, WolfWeapons.MAGIC_ORB_VOLLEY.data),
            ComboStepFactory.randomizedFire(10f, ctx, game, 1f, p -> {
                Translation t = new Translation(0, 0);
                double r = Math.random();
                if (r <= 0.33) t.setY(150);
                else if (r <= 0.66) t.setY(-150);
                p.setBaseTransformation(t);
                return p;
            }),
            ComboStepFactory.casting(1f, ctx, WolfWeapons.MAGIC_ORB_VOLLEY.data)
        ));
    }

    private final List<Supplier<Combo>> combos = List.of(
        WolfComboFactory::makeZigzagCombo,
        WolfComboFactory::makeVolleyCombo,
        WolfComboFactory::makeFanOfOrbsCombo
    );
    private int lastIndex = 1;

    @Override
    public Combo getNext() {
        double r = Math.random();
        if (r < 0.45) lastIndex = (lastIndex + 1) % combos.size();
        if (r > 0.55) lastIndex = (lastIndex - 1) < 0 ? combos.size() - 1 : lastIndex - 1;
        return combos.get(lastIndex).get();
    }
}

