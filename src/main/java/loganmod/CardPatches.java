package loganmod;

import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.blue.BiasedCognition;
import com.megacrit.cardcrawl.cards.blue.Coolheaded;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.cards.colorless.DramaticEntrance;
import com.megacrit.cardcrawl.cards.colorless.Purity;
import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
import com.megacrit.cardcrawl.cards.green.PiercingWail;
import com.megacrit.cardcrawl.cards.green.Nightmare;
import com.megacrit.cardcrawl.cards.green.Footwork;
import com.megacrit.cardcrawl.cards.red.Bludgeon;
import com.megacrit.cardcrawl.cards.red.DarkEmbrace;
import com.megacrit.cardcrawl.cards.red.Feed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import basemod.ReflectionHacks;

public class CardPatches {
    // helper function for creating portraits
    public static AtlasRegion createPortrait(String path) {
        return new AtlasRegion(ImageMaster.loadImage(path), 0, 0, 250, 190);
    }

    // COLORLESS CARDS
    @SpirePatch(clz = Bite.class, method = SpirePatch.CONSTRUCTOR)
    public static class BiteConstructor {
        public static void Postfix(Bite self) {
            self.portrait = createPortrait("loganmod/images/cards/logan-bite.png");
        }
    }

    @SpirePatch(clz = DramaticEntrance.class, method = SpirePatch.CONSTRUCTOR)
    public static class DramaticEntranceConstructor {
        public static void Postfix(DramaticEntrance self) {
            self.portrait = createPortrait("loganmod/images/cards/dramatic-logan.png");
        }
    }

    @SpirePatch(clz = Purity.class, method = SpirePatch.CONSTRUCTOR)
    public static class PurityConstructor {
        public static void Postfix(Purity self) {
            self.portrait = createPortrait("loganmod/images/cards/pure-logan.png");
        }
    }

    // IRONCLAD CARDS
    @SpirePatch(clz = DarkEmbrace.class, method = SpirePatch.CONSTRUCTOR)
    public static class DarkEmbraceConstructor {
        public static void Postfix(DarkEmbrace self) {
            self.portrait = createPortrait("loganmod/images/cards/borkembrace.png");
        }
    }

    @SpirePatch(clz = Bludgeon.class, method = SpirePatch.CONSTRUCTOR)
    public static class BludgeonConstructor {
        public static void Postfix(Bludgeon self) {
            self.portrait = createPortrait("loganmod/images/cards/blogan.png");
        }
    }

    @SpirePatch(clz = Bludgeon.class, method = "use")
    public static class BludgeonUse {
        public static void Prefix(Bludgeon self, AbstractPlayer p, AbstractMonster m) {
            if (m != null) {
                CardCrawlGame.sound.play("LOGAN_BARK");
            }
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
            AbstractDungeon.actionManager.addToBottom(
                    new VFXAction(new LoganBiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()),
                            0.3F));
        }

        public static void Postfix(Bludgeon self, AbstractPlayer p, AbstractMonster m) {
            AbstractDungeon.actionManager.actions.removeIf(action -> {
                if (action instanceof VFXAction) {
                    AbstractGameEffect effect = ReflectionHacks.getPrivate(action, VFXAction.class, "effect");
                    return effect instanceof WeightyImpactEffect;
                }
                return false;
            });
        }
    }

    @SpirePatch(clz = Feed.class, method = SpirePatch.CONSTRUCTOR)
    public static class FeedConstructor {
        public static void Postfix(Feed self) {
            self.portrait = createPortrait("loganmod/images/cards/logan-feed.png");
        }
    }

    // SILENT CARDS
    @SpirePatch(clz = PiercingWail.class, method = SpirePatch.CONSTRUCTOR)
    public static class PiercingWailConstructor {
        public static void Postfix(PiercingWail self) {
            self.portrait = createPortrait("loganmod/images/cards/loganwail.png");
        }
    }

    @SpirePatch(clz = DodgeAndRoll.class, method = SpirePatch.CONSTRUCTOR)
    public static class loadImageConstructor {
        public static void Postfix(DodgeAndRoll self) {
            self.portrait = createPortrait("loganmod/images/cards/dogeandroll.png");
        }
    }

    @SpirePatch(clz = Nightmare.class, method = SpirePatch.CONSTRUCTOR)
    public static class NightmareConstructor {
        public static void Postfix(Nightmare self) {
            self.portrait = createPortrait("loganmod/images/cards/nightmarelogan.png");
        }
    }

    @SpirePatch(clz = Footwork.class, method = SpirePatch.CONSTRUCTOR)
    public static class FootworkConstructor {
        public static void Postfix(Footwork self) {
            self.portrait = createPortrait("loganmod/images/cards/pawwork.png");
        }
    }

    // DEFECT CARDS
    @SpirePatch(clz = BiasedCognition.class, method = SpirePatch.CONSTRUCTOR)
    public static class BiasedCognitionConstructor {
        public static void Postfix(BiasedCognition self) {
            self.portrait = createPortrait("loganmod/images/cards/biaseddog.png");
        }
    }

    @SpirePatch(clz = BiasedCognition.class, method = "use")
    public static class BiasedCognitionUse {
        public static void Postfix(BiasedCognition self, AbstractPlayer p, AbstractMonster m) {
            CardCrawlGame.sound.play("LOGAN_WAIL");
        }
    }

    @SpirePatch(clz = Coolheaded.class, method = SpirePatch.CONSTRUCTOR)
    public static class CoolheadedConstructor {
        public static void Postfix(Coolheaded self) {
            self.portrait = createPortrait("loganmod/images/cards/coolheadeddog.png");
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "loadPortraitImg")
    public static class SingleCardViewPopupPatch {
        public static void Postfix(SingleCardViewPopup self) {
            boolean viewBetaArt = ReflectionHacks.getPrivate(self, SingleCardViewPopup.class, "viewBetaArt");
            if (viewBetaArt)
                return;

            AbstractCard card = ReflectionHacks.getPrivate(self, SingleCardViewPopup.class, "card");
            if (card.cardID.equals("Biased Cognition")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/biaseddog_p.png"));
            } else if (card.cardID.equals("Dodge and Roll")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/dogeandroll_p.png"));
            } else if (card.cardID.equals("PiercingWail")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/loganwail_p.png"));
            } else if (card.cardID.equals("Night Terror")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/nightmarelogan_p.png"));
            } else if (card.cardID.equals("Footwork")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/pawwork_p.png"));
            } else if (card.cardID.equals("Bludgeon")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/blogan_p.png"));
            } else if (card.cardID.equals("Feed")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/logan-feed_p.png"));
            } else if (card.cardID.equals("Dark Embrace")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/borkembrace_p.png"));
            } else if (card.cardID.equals("Coolheaded")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/coolheadeddog_p.png"));
            } else if (card.cardID.equals("Dramatic Entrance")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/dramatic-logan_p.png"));
            } else if (card.cardID.equals("Purity")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/pure-logan_p.png"));
            } else if (card.cardID.equals("Bite")) {
                ReflectionHacks.setPrivate(self, SingleCardViewPopup.class, "portraitImg",
                        ImageMaster.loadImage("loganmod/images/cards/logan-bite_p.png"));
            }
        }
    }

    @SpirePatch(clz = LocalizedStrings.class, method = SpirePatch.CONSTRUCTOR)
    public static class StringPatches {
        public static void Postfix(LocalizedStrings self) {
            if (Settings.language == Settings.GameLanguage.ENG) {
                Map<String, CardStrings> __cards = ReflectionHacks.getPrivateStatic(
                        LocalizedStrings.class, "cards");
                ((CardStrings) __cards.get("Dramatic Entrance")).NAME = "Dogmatic Entrance";
                ((CardStrings) __cards.get("Dark Embrace")).NAME = "Bork Embrace";
                ((CardStrings) __cards.get("Bludgeon")).NAME = "Blogan";
                ((CardStrings) __cards.get("Dodge and Roll")).NAME = "Doge and Roll";
                ((CardStrings) __cards.get("PiercingWail")).NAME = "Logan Wail";
                ((CardStrings) __cards.get("Footwork")).NAME = "Pawwork";
                ((CardStrings) __cards.get("Biased Cognition")).NAME = "Biased Dognition";
            }
        }
    }

    @SpirePatch(clz = SoundMaster.class, method = SpirePatch.CONSTRUCTOR)
    public static class SoundPatches {
        public static void Postfix(SoundMaster __indent, HashMap<String, Sfx> ___map) {
            ___map.put("LOGAN_BARK", new Sfx("loganmod/sounds/logan-bark.ogg", false));
            ___map.put("LOGAN_BITE", new Sfx("loganmod/sounds/logan-bite.ogg", false));
            ___map.put("LOGAN_WAIL", new Sfx("loganmod/sounds/logan-wail-1.ogg", false));
            ___map.put("ATTACK_PIERCING_WAIL", new Sfx("loganmod/sounds/logan-wail-2.ogg", false));
        }
    }
}
