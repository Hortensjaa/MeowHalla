package io.github.meowhalla.game;

public class ViewportUtils {
    public static float left() {
        var g = GameContext.getInstance();
        return g.getViewport().getCamera().position.x - g.getViewport().getWorldWidth() / 2f;
    }

    public static float right() {
        var g = GameContext.getInstance();
        return g.getViewport().getCamera().position.x + g.getViewport().getWorldWidth() / 2f;
    }

    public static float top() {
        var g = GameContext.getInstance();
        return g.getViewport().getCamera().position.y + g.getViewport().getWorldHeight() / 2f;
    }

    public static float bottom() {
        var g = GameContext.getInstance();
        return g.getViewport().getCamera().position.y - g.getViewport().getWorldHeight() / 2f;
    }
}

