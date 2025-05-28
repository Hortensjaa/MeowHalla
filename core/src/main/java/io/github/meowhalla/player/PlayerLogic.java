package io.github.meowhalla.player;


import com.badlogic.gdx.Gdx;
import io.github.meowhalla.structure.contexts.CharacterLogic;
import io.github.meowhalla.game.settings.KeyBindings;
import io.github.meowhalla.projectiles.ProjectileContext;
import io.github.meowhalla.projectiles.Weapon;
import io.github.meowhalla.projectiles.transformation.Rotation;
import io.github.meowhalla.structure.states.Action;
import io.github.meowhalla.structure.states.Direction;

import java.util.List;

public class PlayerLogic extends CharacterLogic {
    Weapon weaponCopy;
    float curRotation = 0f;

    public PlayerLogic(PlayerContext ctx, int max_hp) {
        super(ctx, max_hp);
        weaponCopy = ctx.activeWeapon.copy();
    }

    public void update(float delta) {
        boolean left = Gdx.input.isKeyPressed(KeyBindings.LEFT.getKeyCode());
        boolean right = Gdx.input.isKeyPressed(KeyBindings.RIGHT.getKeyCode());

        if (Gdx.input.isKeyJustPressed(KeyBindings.ATTACK_DOWN.getKeyCode())) {
            curRotation = Math.max(curRotation - 45, -90);;
        } if (Gdx.input.isKeyJustPressed(KeyBindings.ATTACK_UP.getKeyCode())) {
            curRotation = Math.min(curRotation + 45, 90);
        } if (Gdx.input.isKeyPressed(KeyBindings.ATTACK.getKeyCode())) {
            ctx.state.setAction(Action.ATTACK);
            if (curRotation == 0f) {
                ctx.activeWeapon = weaponCopy;
            } else {
                ctx.activeWeapon = ctx.activeWeapon.transform(() -> new Rotation(curRotation));
            }
        }
        else if (Gdx.input.isKeyPressed(KeyBindings.JUMP.getKeyCode())) {
            ctx.state.setAction(Action.JUMP);
            ctx.physics.jump();
        } else if (left && !right) {
            if (ctx.physics.isGrounded()) {
                ctx.state.setAction(Action.RUN);
            }
            ctx.state.setDirection(Direction.LEFT);
            ctx.physics.moveLeft();
        } else if (ctx.state.getAction() != Action.JUMP && right && !left) {
            if (ctx.physics.isGrounded()) {
                ctx.state.setAction(Action.RUN);
            }
            ctx.state.setDirection(Direction.RIGHT);
            ctx.physics.moveRight();
        } else if (ctx.physics.isGrounded()) {
            ctx.state.setAction(Action.IDLE);
        } else {
            ctx.state.setAction(Action.FALL);
        }
        timeSinceLastShot += delta;
        ((PlayerState) ctx.state).increaseTime(delta);
    }

    public List<ProjectileContext> shoot() {
        if (timeSinceLastShot > ctx.activeWeapon.getWeaponContext().cooldown()) {
            timeSinceLastShot = 0f;
            return ctx.activeWeapon.generateProjectiles(ctx);
        }
        return null;
    }
}

