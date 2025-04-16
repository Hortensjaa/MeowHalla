package io.github.meowhalla.physics;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.BossContext;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BossPhysics {
    private final BossContext ctx;
    Vector2 velocity = new Vector2();

    public BossPhysics(BossContext ctx) {
        this.ctx = ctx;
    }
}
