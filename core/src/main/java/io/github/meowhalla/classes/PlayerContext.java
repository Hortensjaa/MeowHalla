package io.github.meowhalla.classes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.graphics.PlayerGraphics;
import io.github.meowhalla.logic.PlayerLogic;
import io.github.meowhalla.physics.PlayerPhysics;
import io.github.meowhalla.states.PlayerState;
import lombok.Getter;

public class PlayerContext implements DynamicObject {
    public PlayerState state = new PlayerState(0, 0, 0, 0);
    public PlayerLogic logic = new PlayerLogic(this);
    public PlayerPhysics physics = new PlayerPhysics(this);
    public PlayerGraphics graphics = new PlayerGraphics(this);
    @Getter private final GameContext gameContext;

    public PlayerContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public void update(float delta) {
        logic.update(delta);
        physics.update(delta);
    }

    public void render(SpriteBatch batch) {
        graphics.render(batch);
    }

    public void dispose() {
        graphics.dispose();
    }

    public Vector2 leftBorder() {
        return new Vector2(state.getPosition().x, state.getPosition().y + state.getPosition().height / 2);
    }

    public Vector2 rightBorder() {
        return new Vector2(state.getPosition().x + state.getPosition().width,
            state.getPosition().y + state.getPosition().height / 2);
    }
}


