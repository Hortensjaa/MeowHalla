package io.github.meowhalla.logic;

import io.github.meowhalla.classes.characters.CharacterContext;
import lombok.Getter;
import lombok.Setter;

public abstract class CharacterLogic {
    CharacterContext ctx;
    @Getter int max_hp;
    @Setter @Getter int hp;
    public float timeSinceLastShot = 0f;

    public CharacterLogic(CharacterContext ctx, int max_hp) {
        this.ctx = ctx;
        this.max_hp = max_hp;
        this.hp = max_hp;
    }

    public abstract void update(float delta);
}
