package io.github.meowhalla.enemies;

import io.github.meowhalla.structure.contexts.CharacterContext;
import io.github.meowhalla.structure.physics.CharacterPhysics;
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
