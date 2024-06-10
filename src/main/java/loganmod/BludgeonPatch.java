package loganmod;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;

import basemod.ReflectionHacks;

public class BludgeonPatch {
    @SpirePatch(clz = Bludgeon.class, method = SpirePatch.CONSTRUCTOR)
    public static class BludgeonConstructor {
        public static void Postfix(Bludgeon self) {
            self.portrait = new AtlasRegion(ImageMaster.loadImage("speakthespiremod/images/cards/bludgeon.png"), 0, 0,
                    500, 380);

        }
    }

    @SpirePatch(clz = LocalizedStrings.class, method = SpirePatch.CONSTRUCTOR)
    public static class BludgeonStrings {
        public static void Postfix(LocalizedStrings self) {
            Map<String, CardStrings> __cards = ReflectionHacks.getPrivateStatic(
                    LocalizedStrings.class, "cards");
            if (Settings.language == Settings.GameLanguage.ENG) {
                ((CardStrings) __cards.get("Bludgeon")).NAME = "Blogan";
            }
        }
    }

}
