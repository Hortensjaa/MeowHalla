package io.github.meowhalla.projectiles.movement;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.structure.DynamicObject;

public class ChasingMovementStrategy implements MovementStrategy {
    DynamicObject target;
    float speed;

    public ChasingMovementStrategy(DynamicObject target, float speed) {
        this.target = target;
        this.speed = speed;
    }

    @Override
    public Vector2 update(DynamicObject context, float deltaTime) {
        Vector2 position = new Vector2(context.getX(), context.getY());
        Vector2 target_vec = new Vector2(target.getX(), target.getY());
        Vector2 direction = target_vec.sub(position).nor();
        return direction.scl(speed * deltaTime);
    }
}
