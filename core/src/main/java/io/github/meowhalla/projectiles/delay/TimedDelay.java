package io.github.meowhalla.projectiles.delay;


public class TimedDelay implements DelayStrategy {
    private final float delay;
    private final String fileName;

    public TimedDelay(float delay, String fileName) {
        this.delay = delay;
        this.fileName = fileName;
    }

    @Override
    public String waitingFramesFilename() {
        return fileName;
    }

    public boolean isReady(float t) {
        return t >= delay;
    }
}

