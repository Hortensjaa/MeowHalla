package io.github.meowhalla.contexts;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.meowhalla.graphics.PlayerGraphics;
import io.github.meowhalla.logic.PlayerLogic;
import io.github.meowhalla.physics.PlayerPhysics;
import io.github.meowhalla.states.PlayerState;

public class PlayerContext {
    public PlayerState state = new PlayerState(100, 100, 64, 64);
    public PlayerLogic logic = new PlayerLogic(this);
    public PlayerPhysics physics = new PlayerPhysics(this);
    public PlayerGraphics graphics = new PlayerGraphics(this);

    public void update(float delta) {
        logic.update();
        physics.update(delta);
    }

    public void render(SpriteBatch batch) {
        graphics.render(batch);
    }

    public void dispose() {
        graphics.dispose();
    }
}


