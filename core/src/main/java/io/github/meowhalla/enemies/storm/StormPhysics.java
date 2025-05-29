package io.github.meowhalla.enemies.storm;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.game.ViewportUtils;
import io.github.meowhalla.enemies.EnemyPhysics;

public class StormPhysics extends EnemyPhysics {
    Vector2 velocity = new Vector2(1, 0);

    public StormPhysics(CharacterContext ctx) {
        super(ctx);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (ctx.leftBorder() <= ViewportUtils.left() || ctx.rightBorder() >= ViewportUtils.right()) {
            velocity.x = -velocity.x;
        }
        ctx.state.getPosition().x += velocity.x;
    }
}
