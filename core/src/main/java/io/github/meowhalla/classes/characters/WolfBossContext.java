package io.github.meowhalla.classes.characters;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.GameContext;
import io.github.meowhalla.data.WeaponType;
import io.github.meowhalla.graphics.WolfBossGraphics;
import io.github.meowhalla.logic.WolfBossLogic;
import io.github.meowhalla.physics.BossPhysics;
import io.github.meowhalla.states.CharacterState;

public class WolfBossContext extends CharacterContext {

    public WolfBossContext(GameContext gameContext) {
        super(gameContext);
        float w = getGameContext().getViewport().getWorldWidth();
        state = new CharacterState((int) (w - 380), 0, 0, 0, 1000);
        logic = new WolfBossLogic(this);
        physics = new BossPhysics(this);
        graphics = new WolfBossGraphics(this, "bosses/wolf_of_death.png", 6f);
        weapon = WeaponType.MAGIC_CRUSHER.data;
    }

    @Override
    public Vector2 leftBorder() {
        return new Vector2(state.getPosition().x, state.getPosition().y + state.getPosition().height / 6);
    }

    @Override
    public Vector2 rightBorder() {
        return new Vector2(state.getPosition().x + state.getPosition().width,
            state.getPosition().y + state.getPosition().height / 6);
    }
}
