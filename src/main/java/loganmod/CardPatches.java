package loganmod;

import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.BiasedCognition;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.Omamori;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import basemod.ReflectionHacks;

public class CardPatches {
    @SpirePatch(clz = Bludgeon.class, method = SpirePatch.CONSTRUCTOR)
    public static class BludgeonConstructor {
        public static void Postfix(Bludgeon self) {
            self.portrait = new AtlasRegion(ImageMaster.loadImage("loganmod/images/cards/blogan.png"), 0,
                    0,
                    250, 190);
        }
    }

    @SpirePatch(clz = Bludgeon.class, method = "use")
    public static class BludgeonUse {
        public static void Prefix(Bludgeon self, AbstractPlayer p, AbstractMonster m) {
            if (m != null) {
                CardCrawlGame.sound.play("LOGAN_BARK");
            }
        }
    }

    @SpirePatch(clz = PiercingWail.class, method = SpirePatch.CONSTRUCTOR)
    public static class PiercingWailConstructor {
        public static void Postfix(PiercingWail self) {
            self.portrait = new AtlasRegion(ImageMaster.loadImage("loganmod/images/cards/loganwail.png"),
                    0, 0,
                    250, 190);
        }
    }

    @SpirePatch(clz = BiasedCognition.class, method = SpirePatch.CONSTRUCTOR)
    public static class BiasedCognitionConstructor {
        public static void Postfix(BiasedCognition self) {
            self.portrait = new AtlasRegion(ImageMaster.loadImage("loganmod/images/cards/biaseddog.png"),
                    0, 0,
                    250, 190);
        }
    }

    @SpirePatch(clz = BiasedCognition.class, method = "use")
    public static class BiasedCognitionUse {
        public static void Postfix(BiasedCognition self, AbstractPlayer p, AbstractMonster m) {
            CardCrawlGame.sound.play("LOGAN_WAIL");
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "loadPortraitImg")
    public static class SingleCardViewPopupPatch {
        public static void Postfix(SingleCardViewPopup self) {
            AbstractCard card = ReflectionHacks.getPrivate(self, SingleCardViewPopup.class, "card");
            if (card.cardID.equals("Biased Cognition")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/biaseddog_p.png"));
            } else if (card.cardID.equals("PiercingWail")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/loganwail_p.png"));
            } else if (card.cardID.equals("Bludgeon")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/blogan_p.png"));
            }
        }
    }

    @SpirePatch(clz = LocalizedStrings.class, method = SpirePatch.CONSTRUCTOR)
    public static class StringPatches {
        public static void Postfix(LocalizedStrings self) {
            Map<String, CardStrings> __cards = ReflectionHacks.getPrivateStatic(
                    LocalizedStrings.class, "cards");
            if (Settings.language == Settings.GameLanguage.ENG) {
                ((CardStrings) __cards.get("Bludgeon")).NAME = "Blogan";
                ((CardStrings) __cards.get("PiercingWail")).NAME = "Logan Wail";
                ((CardStrings) __cards.get("Biased Cognition")).NAME = "Biased Dognition";
            }
        }
    }

    @SpirePatch(clz = SoundMaster.class, method = SpirePatch.CONSTRUCTOR)
    public static class SoundPatches {
        public static void Postfix(SoundMaster __indent, HashMap<String, Sfx> ___map) {
            ___map.put("LOGAN_BARK", new Sfx("loganmod/sounds/logan-bark.ogg", false));
            ___map.put("LOGAN_WAIL", new Sfx("loganmod/sounds/logan-wail-1.ogg", false));
            ___map.put("ATTACK_PIERCING_WAIL", new Sfx("loganmod/sounds/logan-wail-2.ogg", false));
        }
    }
}
