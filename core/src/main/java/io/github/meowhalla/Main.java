package io.github.meowhalla;

import com.badlogic.gdx.Game;
import io.github.meowhalla.game.screens.MenuScreen;
import lombok.Getter;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Getter
    private static Main instance;

    @Override
    public void create() {
        instance = this;
        setScreen(new MenuScreen());
    }
}
