package io.github.meowhalla.classes.projectiles;


public class TimedDelay implements DelayStrategy {
    private final float delay;
//    private ItemGraphics delayGraphics;

    public TimedDelay(float delay, String fileName) {
        this.delay = delay;
//        delayGraphics = new ItemGraphics( fileName);
    }

    public boolean isReady(float t) {
        return t >= delay;
    }
}

