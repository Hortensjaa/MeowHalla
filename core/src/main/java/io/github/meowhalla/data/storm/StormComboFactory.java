package io.github.meowhalla.data.storm;

import io.github.meowhalla.game.GameContext;
import io.github.meowhalla.logic.Combo;
import io.github.meowhalla.logic.ComboFactory;
import io.github.meowhalla.logic.ComboStepFactory;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.base_transformation.Translation;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;


public class StormComboFactory implements ComboFactory {
    static StormContext ctx;
    static GameContext game;

    public StormComboFactory(StormContext ctx) {
        StormComboFactory.ctx = ctx;
        StormComboFactory.game = ctx.getGameContext();
    }

    public static Combo rainCombo() {
        Function<ProjectileContext, ProjectileContext> randomizedRainHelper = (ProjectileContext p) -> {
            Translation t = new Translation(0, 0);
            double r = Math.random();
            t.setX(10f + (float) (r * (ctx.getPosition().width - 20f)));
            p.setBaseTransformation(t);
            return p;
        };

        return new Combo(List.of(
            ComboStepFactory.casting(2f, ctx, StormWeapons.HEAVY_RAIN.data),
            ComboStepFactory.randomizedFire(5f, ctx, game, 0.1f, randomizedRainHelper),
            ComboStepFactory.idle(10f, ctx)
        ));
    }

    private final List<Supplier<Combo>> combos = List.of(
        io.github.meowhalla.data.storm.StormComboFactory::rainCombo
    );

    @Override
    public Combo getNext() {
        return combos.get(0).get();
    }

}


