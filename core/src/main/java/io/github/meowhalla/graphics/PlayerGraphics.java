package io.github.meowhalla.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import io.github.meowhalla.contexts.PlayerContext;
import io.github.meowhalla.states.Action;
import io.github.meowhalla.states.Direction;

public class PlayerGraphics {
    private final PlayerContext ctx;
    private final Texture catSheet;
    private final Animation<TextureRegion> runAnimation;
    private float stateTime = 0f;
    private final TextureRegion idleFrame;

    public PlayerGraphics(PlayerContext ctx) {
        this.ctx = ctx;
        catSheet = new Texture(Gdx.files.internal("player.png"));
        TextureRegion[][] frames = TextureRegion.split(catSheet, 290, 261);
        TextureRegion frame1 = frames[0][0];
        TextureRegion frame2 = frames[0][1];
        idleFrame = new TextureRegion(frame1);

        runAnimation = new Animation<>(0.2f, frame1, frame2);
        runAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void render(SpriteBatch batch) {
        Rectangle r = ctx.state.getPosition();

        TextureRegion currentFrame;

        if (ctx.state.getAction() == Action.RUN) {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = runAnimation.getKeyFrame(stateTime);
        } else {
            currentFrame = idleFrame;
        }

        Direction dir = ctx.state.getDirection();

        if (dir == Direction.RIGHT && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        if (dir == Direction.LEFT && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }

        batch.draw(currentFrame, r.x, r.y, r.width, r.height);
    }

    public void dispose() {
        catSheet.dispose();
    }
}
