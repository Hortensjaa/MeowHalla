package io.github.meowhalla.states;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PlayerState {
    Rectangle position = new Rectangle();
    ActionState actionState = new ActionState(Action.WAIT, Direction.LEFT);
    int hp = 100;

    public PlayerState(int x, int y, int width, int height) {
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
    }

    public void updateHp(int value) {
        hp += value;
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
