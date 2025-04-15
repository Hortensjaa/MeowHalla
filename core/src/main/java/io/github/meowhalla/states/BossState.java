package io.github.meowhalla.states;


import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BossState {
    Rectangle position = new Rectangle();
    ActionState action = new ActionState(Action.WAIT, Direction.FRONT);
    int hp = 1000;

    public BossState(int x, int y, int width, int height) {
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
    }

    public void updateHp(int value) {
        hp += value;
    }
}
