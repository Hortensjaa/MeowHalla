package io.github.meowhalla.enemies.mocktopus;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.enemies.EnemyPhysics;
import io.github.meowhalla.game.ViewportUtils;
import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.structure.states.Direction;

import java.util.Random;

public class MocktopusPhysics extends EnemyPhysics {
    private Vector2 velocity = new Vector2(100, 75);
    private float time = 0f;
    private Random random = new Random();
//    private ChasingMovementStrategy movement = new ChasingMovementStrategy(GameContext.getInstance().getPlayer(), 50);

    public MocktopusPhysics(CharacterContext ctx) {
        super(ctx);
    }

    @Override
    public void update(float delta) {
        time += delta;
//        Vector2 v = movement.update(ctx, delta);
//        ctx.getPosition().add(v);

        float r = random.nextFloat();
        float dx = (float) Math.cos(time * 0.7f) * velocity.x * delta;
        float dy = (float) Math.sin(time * 1.3f) * velocity.y * delta;
        if (dx > 0) {
            ctx.state.setDirection(Direction.RIGHT);
        } else {
            ctx.state.setDirection(Direction.LEFT);
        }

        Rectangle pos = ctx.state.getPosition();
        pos.x += (1 + r) * dx;
        pos.y += (1 + r) * dy;

        pos.x = MathUtils.clamp(pos.x, ViewportUtils.left(), ViewportUtils.right() - pos.width);
        pos.y = MathUtils.clamp(pos.y, ViewportUtils.bottom(), ViewportUtils.top() - pos.height);
    }
}
