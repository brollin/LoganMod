package loganmod;

import java.util.Map;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.DigOption;

import basemod.ReflectionHacks;

public class EventPatches {
    @SpirePatch(clz = DigOption.class, method = SpirePatch.CONSTRUCTOR)
    public static class DigOptionConstructor {
        public static void Postfix(DigOption self) {
            ReflectionHacks.setPrivate(self, AbstractCampfireOption.class, "img",
                    ImageMaster.loadImage("loganmod/images/events/logandig.png"));

        }
    }

    @SpirePatch(clz = Vampires.class, method = SpirePatch.CONSTRUCTOR)
    public static class VampiresConstructor {
        public static void Postfix(Vampires self) {
            self.imageEventText.loadImage("loganmod/images/events/vampirelogan.png");
        }
    }

    @SpirePatch(clz = LocalizedStrings.class, method = SpirePatch.CONSTRUCTOR)
    public static class StringPatches {
        public static void Postfix(LocalizedStrings self) {
            if (Settings.language == Settings.GameLanguage.ENG) {
                Map<String, UIStrings> uiStrings = ReflectionHacks.getPrivateStatic(
                        LocalizedStrings.class, "ui");
                ((UIStrings) uiStrings.get("Dig Option")).TEXT[1] = "Have Logan dig up a random relic.";
            }
        }
    }
}
