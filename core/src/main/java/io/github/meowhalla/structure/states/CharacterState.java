package io.github.meowhalla.structure.states;

import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CharacterState {
    Rectangle position = new Rectangle();
    Action action = Action.IDLE;
    Direction direction = Direction.LEFT;

    public CharacterState(float x, float y, float width, float height) {
        position.setX(x);
        position.setY(y);
        position.setWidth(width);
        position.setHeight(height);
    }

    public boolean isInvincible() {
        return false;
    }
}
