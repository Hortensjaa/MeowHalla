package io.github.meowhalla.physics;

import com.badlogic.gdx.math.Vector2;
import io.github.meowhalla.classes.characters.CharacterContext;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BossPhysics {
    private final CharacterContext ctx;
    Vector2 velocity = new Vector2();

    public BossPhysics(CharacterContext ctx) {
        this.ctx = ctx;
    }
}
