package io.github.meowhalla.structure.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.meowhalla.structure.character.CharacterContext;
import io.github.meowhalla.structure.states.Direction;

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
        ctx.setWidth(tileWidth * scaling);
        ctx.setHeight(tileHeight * scaling);
    }

    public void render(SpriteBatch batch) {
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

        batch.draw(currentFrame, ctx.getX(), ctx.getY(), ctx.getWidth(), ctx.getHeight());
    }

    public void dispose() {
        sheet.dispose();
    }
}
