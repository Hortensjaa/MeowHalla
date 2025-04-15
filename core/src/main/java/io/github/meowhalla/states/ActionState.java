package io.github.meowhalla.states;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ActionState {

    Action action;
    Direction direction;

    public ActionState(Action action, Direction direction) {
        this.action = action;
        this.direction = direction;
    }

}

