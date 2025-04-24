package io.github.meowhalla.data;

import com.badlogic.gdx.Input;
import lombok.Getter;

@Getter
public enum KeyBindings {
    RIGHT(Input.Keys.RIGHT),
    LEFT(Input.Keys.LEFT),
    JUMP(Input.Keys.UP),
    ATTACK(Input.Keys.X),
    GUARD(Input.Keys.Z),
    ATTACK_DOWN(Input.Keys.S),
    ATTACK_UP(Input.Keys.W);

    private int keyCode;

    KeyBindings(int keyCode) {
        this.keyCode = keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}

