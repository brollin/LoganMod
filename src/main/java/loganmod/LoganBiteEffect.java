package loganmod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class LoganBiteEffect extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion top;
    private static TextureAtlas.AtlasRegion bot;
    private float x;
    private float y;
    private float sY;
    private float dY;
    private float y2;
    private float sY2;
    private float dY2;
    private boolean playedSfx;

    public LoganBiteEffect(float x, float y, Color c) {
        this.playedSfx = false;
        if (top == null) {
            top = ImageMaster.vfxAtlas.findRegion("combat/biteTop");
            bot = ImageMaster.vfxAtlas.findRegion("combat/biteBot");
        }

        this.x = x - (float) top.packedWidth / 2.0F;
        this.sY = y - (float) top.packedHeight / 2.0F + 150.0F * Settings.scale;
        this.dY = y - 0.0F * Settings.scale;
        this.y = this.sY;
        this.sY2 = y - (float) (top.packedHeight / 2) - 100.0F * Settings.scale;
        this.dY2 = y - 90.0F * Settings.scale;
        this.y2 = this.sY2;
        this.duration = 1.0F;
        this.startingDuration = 1.0F;
        this.color = c;
        this.scale = 1.0F * Settings.scale;
    }

    public LoganBiteEffect(float x, float y) {
        this(x, y, new Color(0.7F, 0.9F, 1.0F, 0.0F));
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < this.startingDuration - 0.3F && !this.playedSfx) {
            this.playedSfx = true;
            CardCrawlGame.sound.play("LOGAN_BITE", 0.05F);
        }

        if (this.duration > this.startingDuration / 2.0F) {
            this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 0.5F) * 2.0F);
            this.y = Interpolation.bounceIn.apply(this.dY, this.sY, (this.duration - 0.5F) * 2.0F);
            this.y2 = Interpolation.bounceIn.apply(this.dY2, this.sY2, (this.duration - 0.5F) * 2.0F);
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
            this.y = Interpolation.fade.apply(this.sY, this.dY, this.duration * 2.0F);
            this.y2 = Interpolation.fade.apply(this.sY2, this.dY2, this.duration * 2.0F);
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(top, this.x, this.y, (float) top.packedWidth / 2.0F, (float) top.packedHeight / 2.0F,
                (float) top.packedWidth, (float) top.packedHeight, this.scale + MathUtils.random(-0.05F, 0.05F),
                this.scale + MathUtils.random(-0.05F, 0.05F), 0.0F);
        sb.draw(bot, this.x, this.y2, (float) top.packedWidth / 2.0F, (float) top.packedHeight / 2.0F,
                (float) top.packedWidth, (float) top.packedHeight, this.scale + MathUtils.random(-0.05F, 0.05F),
                this.scale + MathUtils.random(-0.05F, 0.05F), 0.0F);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
