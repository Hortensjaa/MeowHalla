package io.github.meowhalla.structure.character;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.github.meowhalla.game.GameContext;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.structure.DynamicObject;
import io.github.meowhalla.structure.graphics.CharacterGraphics;
import io.github.meowhalla.structure.physics.CharacterPhysics;
import io.github.meowhalla.structure.states.Action;
import io.github.meowhalla.structure.states.CharacterState;
import io.github.meowhalla.structure.states.Direction;
import lombok.Getter;

public abstract class CharacterContext implements DynamicObject {
    public CharacterState state;
    public CharacterLogic logic;
    public CharacterPhysics physics;
    public CharacterGraphics graphics;
    public Weapon activeWeapon;
    @Getter protected String name;
    @Getter private final GameContext gameContext;

    public CharacterContext() {
        this.gameContext = GameContext.getInstance();
    }

    @Override
    public void update(float delta) {
        logic.update(delta);
        physics.update(delta);
    }

    @Override
    public void render(SpriteBatch batch) {
        graphics.render(batch);
    }

    @Override
    public void dispose() {
        graphics.dispose();
    }

    @Override
    public float getX() {
        return state.getPosition().x;
    }

    @Override
    public float getY() {
        return state.getPosition().y;
    }

    @Override
    public float getWidth() {
        return state.getPosition().width;
    }

    @Override
    public float getHeight() {
        return state.getPosition().height;
    }

    @Override
    public void setX(float v) {
        state.getPosition().x = v;
    }

    @Override
    public void setY(float v) {
        state.getPosition().y = v;
    }

    @Override
    public void setWidth(float v) {
        state.getPosition().width = v;
    }

    @Override
    public void setHeight(float v) {
        state.getPosition().height = v;
    }

    public Rectangle getRectangle() {
        return state.getPosition();
    }

    public Action getAction() {
        return state.getAction();
    }

    public Direction getDirection() {
        return state.getDirection();
    }

    public void updateHp(float val) {
        logic.setHp(logic.getHp() + val);
    }

    public float getHp() {
        return logic.getHp();
    }

    public float getMax_hp() {
        return logic.getMax_hp();
    }
}


