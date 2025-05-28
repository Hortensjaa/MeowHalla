package io.github.meowhalla.projectiles.state;

public class DestroyAfterState extends ProjectileState {

    public DestroyAfterState(float destroyAfter, String destroyFrames) {
        super(-0.1f, destroyAfter, "weapons/Black And White Sparks.png", destroyFrames);
    }

}
