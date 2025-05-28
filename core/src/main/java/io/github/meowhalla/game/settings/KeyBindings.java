package io.github.meowhalla.game.settings;

import com.badlogic.gdx.Input;
import lombok.Getter;

@Getter
public enum KeyBindings {
    RIGHT(Input.Keys.RIGHT),
    LEFT(Input.Keys.LEFT),
    JUMP(Input.Keys.UP),
    ATTACK(Input.Keys.S),
    GUARD(Input.Keys.SPACE),
    ATTACK_DOWN(Input.Keys.X),
    ATTACK_UP(Input.Keys.A);

    private int keyCode;

    KeyBindings(int keyCode) {
        this.keyCode = keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }
}

