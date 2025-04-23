package io.github.meowhalla.physics;

import io.github.meowhalla.contexts.CharacterContext;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnemyPhysics extends CharacterPhysics {
    protected final CharacterContext ctx;

    public EnemyPhysics(CharacterContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void update(float delta) {}
}
