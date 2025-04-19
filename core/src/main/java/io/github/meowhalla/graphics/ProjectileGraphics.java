package io.github.meowhalla.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import io.github.meowhalla.classes.projectiles.ProjectileContext;

public class ProjectileGraphics implements Graphics {
    private final ProjectileContext ctx;
    private final Texture sheet;
    private final Animation<TextureRegion> animation;
    private float stateTime = 0f;

    public ProjectileGraphics(ProjectileContext ctx, String fileName) {
        this.ctx = ctx;
        sheet = new Texture(Gdx.files.internal(fileName));
        TextureRegion[][] frames = TextureRegion.split(sheet, 16, 16);
        TextureRegion[] f = frames[0];

        animation = new Animation<>(0.2f, f[0], f[1], f[2], f[3], f[4], f[5]);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void render(SpriteBatch batch) {
        Circle r = ctx.getHitbox();
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);

        float size = r.radius * 2;
        batch.draw(currentFrame, r.x - r.radius, r.y - r.radius, size, size);
    }

    public void dispose() {
        sheet.dispose();
    }
}
