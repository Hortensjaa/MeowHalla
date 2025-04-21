package io.github.meowhalla.contexts;

import lombok.Getter;
import lombok.Setter;

public abstract class CharacterLogic {
    protected CharacterContext ctx;
    @Getter float max_hp;
    @Setter @Getter float hp;
    public float timeSinceLastShot = 0f;

    public CharacterLogic(CharacterContext ctx, int max_hp) {
        this.ctx = ctx;
        this.max_hp = max_hp;
        this.hp = max_hp;
    }

    public abstract void update(float delta);
}
