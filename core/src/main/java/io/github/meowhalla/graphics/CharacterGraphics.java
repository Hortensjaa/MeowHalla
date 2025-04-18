package io.github.meowhalla.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import io.github.meowhalla.classes.characters.CharacterContext;
import io.github.meowhalla.states.Direction;

public abstract class CharacterGraphics implements Graphics {
    protected CharacterContext ctx;
    protected Texture sheet;
    protected AnimationSet animationSet;
    protected float stateTime;
    protected TextureRegion[][] frames;

    public CharacterGraphics(CharacterContext ctx, String fileName, float scaling, int rowsNum, int colsNum) {
        this.ctx = ctx;
        sheet = new Texture(Gdx.files.internal(fileName));
        int tileWidth = sheet.getWidth()/colsNum;
        int tileHeight = sheet.getHeight()/rowsNum;
        frames = TextureRegion.split(sheet, tileWidth, tileHeight);
        ctx.getPosition().setWidth(tileWidth * scaling);
        ctx.getPosition().setHeight(tileHeight * scaling);
    }

    public void render(SpriteBatch batch) {
        Rectangle r = ctx.getPosition();

        TextureRegion currentFrame;
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animationSet.get(ctx.getAction()).getKeyFrame(stateTime);

        Direction dir = ctx.getDirection();
        if (dir == Direction.RIGHT && !currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }
        if (dir == Direction.LEFT && currentFrame.isFlipX()) {
            currentFrame.flip(true, false);
        }

        batch.draw(currentFrame, r.x, r.y, r.width, r.height);
    }

    public void dispose() {
        sheet.dispose();
    }
}
