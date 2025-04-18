package io.github.meowhalla.states;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CharacterState {
    Rectangle position = new Rectangle();
    Action action = Action.IDLE;
    Direction direction = Direction.LEFT;

    public CharacterState(int x, int y, int width, int height) {
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
    }

    public boolean isInvincible() {
        return false;
    }
}
