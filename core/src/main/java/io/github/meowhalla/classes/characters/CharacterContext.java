package io.github.meowhalla.classes.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.DynamicObject;
import io.github.meowhalla.classes.GameContext;
import io.github.meowhalla.classes.weapons.WeaponContext;
import io.github.meowhalla.graphics.CharacterGraphics;
import io.github.meowhalla.logic.CharacterLogic;
import io.github.meowhalla.physics.CharacterPhysics;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.CharacterState;
import io.github.meowhalla.states.Direction;
import lombok.Getter;

public abstract class CharacterContext implements DynamicObject {
    public CharacterState state;
    public CharacterLogic logic;
    public CharacterPhysics physics;
    public CharacterGraphics graphics;
    @Getter private final GameContext gameContext;
    public WeaponContext weapon;
    public float timeSinceLastShot = 0f;

    public CharacterContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public void update(float delta) {
        logic.update(delta);
        physics.update(delta);
        timeSinceLastShot += delta;
    }

    public void render(SpriteBatch batch) {
        graphics.render(batch);
    }

    public void dispose() {
        graphics.dispose();
    }

    public Rectangle getPosition() {
        return state.getPosition();
    }

    public Action getAction() {
        return state.getAction();
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public Vector2 leftBorder() {
        return new Vector2(state.getPosition().x, state.getPosition().y + state.getPosition().height / 2);
    }

    public Vector2 rightBorder() {
        return new Vector2(state.getPosition().x + state.getPosition().width,
            state.getPosition().y + state.getPosition().height / 2);
    }
}


