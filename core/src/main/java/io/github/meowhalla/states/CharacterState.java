package io.github.meowhalla.states;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CharacterState {
    Rectangle position = new Rectangle();
    ActionState actionState = new ActionState(Action.IDLE, Direction.LEFT);
    int hp;
    int max_hp;

    public CharacterState(int x, int y, int width, int height, int hp) {
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
        max_hp = hp;
        this.hp = max_hp;
    }

    public void updateHp(int value) {
        hp += value;
    }

    public void fullHealth() {
        hp = max_hp;
    }

    public void setAction(Action action) {
        this.actionState.setAction(action);
    }

    public void setDirection(Direction direction) {
        this.actionState.setDirection(direction);
    }

    public Action getAction() {
        return this.actionState.getAction();
    }

    public Direction getDirection() {
        return this.actionState.getDirection();
    }
}
