package io.github.meowhalla.data;

import com.badlogic.gdx.Input;
import lombok.Getter;

@Getter
public enum KeyBindings {
    RIGHT(Input.Keys.RIGHT),
    LEFT(Input.Keys.LEFT),
    JUMP(Input.Keys.UP),
    ATTACK(Input.Keys.X),
    GUARD(Input.Keys.Z);

    private int keyCode;

    KeyBindings(int keyCode) {
        this.keyCode = keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}

