package teamHTBP.vida.client.font;

import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class DungeonFontRenderer extends Font {
    private final StringSplitter characterManager;
    private final Function<ResourceLocation, FontSet> font;
    public final ResourceLocation dungeonFont = new ResourceLocation("vida","font/dungeonfont");

    public DungeonFontRenderer(Function<ResourceLocation, FontSet> font) {
        super(font);
        this.font = font;
        this.characterManager = new StringSplitter((charID, style) -> {
            return font.apply(style.getFont()).getGlyphInfo(charID).getAdvance(style.isBold());
        });
    }

    private FontSet getFont(ResourceLocation fontLocation) {
        return this.font.apply(fontLocation);
    }

    @Override
    public StringSplitter getSplitter() {
        return characterManager;
    }
}
