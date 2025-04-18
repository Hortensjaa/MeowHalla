package io.github.meowhalla.physics;

public interface CharacterPhysics {
    void update(float delta);
    void moveLeft();
    void moveRight();
    void jump();
    boolean isGrounded();
}
