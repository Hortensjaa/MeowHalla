package io.github.meowhalla.enemies.wolf_boss;

import io.github.meowhalla.game.GameContext;
import io.github.meowhalla.logic.Combo;
import io.github.meowhalla.logic.ComboFactory;
import io.github.meowhalla.logic.ComboStepFactory;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.base_transformation.Translation;
import io.github.meowhalla.projectiles.movement.StraightMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;


public class WolfComboFactory implements ComboFactory {
    static WolfContext ctx;
    static GameContext game;

    public WolfComboFactory(WolfContext ctx) {
        WolfComboFactory.ctx = ctx;
        WolfComboFactory.game = ctx.getGameContext();
    }

    public static Combo zigzagCombo() {
        return new Combo(List.of(
            ComboStepFactory.casting(1f, ctx, WolfWeapons.ZIGZAG.data),
            ComboStepFactory.continuousFire(5f, ctx, game, 0.5f),
            ComboStepFactory.casting(1f, ctx, WolfWeapons.REVERSED_ZIGZAG.data),
            ComboStepFactory.continuousFire(5f, ctx, game, 0.5f),
            ComboStepFactory.idle(1f, ctx)
        ));
    }

    public static Combo fanOfOrbsCombo() {
        return new Combo(List.of(
            ComboStepFactory.casting(1f, ctx, WolfWeapons.FAN_OF_ORBS.data),
            ComboStepFactory.continuousFire(5f, ctx, game, 1f),
            ComboStepFactory.idle(3f, ctx)
        ));
    }

    public static Combo volleyCombo() {
        Function<ProjectileContext, ProjectileContext> randomizedFireHelper = (ProjectileContext p) -> {
            Translation t = new Translation(0, 0);
            double r = Math.random();
            if (r <= 0.33) t.setY(150);
            else if (r <= 0.66) t.setY(-150);
            p.setBaseTransformation(t);
            return p;
        };

        return new Combo(List.of(
            ComboStepFactory.casting(1f, ctx, WolfWeapons.MAGIC_ORB_VOLLEY.data),
            ComboStepFactory.randomizedFire(5f, ctx, game, 1f, randomizedFireHelper),
            ComboStepFactory.casting(1f, ctx, WolfWeapons.REVERSED_VOLLEY.data),
            ComboStepFactory.randomizedFire(5f, ctx, game, 0.75f, randomizedFireHelper),
            ComboStepFactory.casting(1f, ctx,
                WolfWeapons.MAGIC_ORB_VOLLEY.data.transform(() -> new StraightMovement(1200, 0))),
            ComboStepFactory.randomizedFire(5f, ctx, game, 0.75f, randomizedFireHelper),
            ComboStepFactory.casting(1f, ctx,
                WolfWeapons.REVERSED_VOLLEY.data.transform(() -> new StraightMovement(-1200, 0))),
            ComboStepFactory.randomizedFire(5f, ctx, game, 0.75f, randomizedFireHelper),
            ComboStepFactory.idle(2f, ctx)
        ));
    }

    private final List<Supplier<Combo>> combos = List.of(
        WolfComboFactory::zigzagCombo,
        WolfComboFactory::volleyCombo,
        WolfComboFactory::fanOfOrbsCombo
    );
    private int lastIndex = 0;

    @Override
    public Combo getNext() {
        int size = combos.size();
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i != lastIndex) {
                indices.add(i);
            }
        }
        lastIndex = indices.get((int)(Math.random() * indices.size()));
        return combos.get(lastIndex).get();
    }

}

