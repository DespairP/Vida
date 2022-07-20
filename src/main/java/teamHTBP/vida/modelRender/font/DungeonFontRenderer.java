package teamHTBP.vida.modelRender.font;

import net.minecraft.client.gui.Font;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.CharacterManager;

import java.util.function.Function;

public class DungeonFontRenderer extends Font {
    private final CharacterManager characterManager;
    private final Function<ResourceLocation, Font> font;
    public final ResourceLocation dungeonFont = new ResourceLocation("vida","font/dungeonfont");

    public DungeonFontRenderer(Function<ResourceLocation, Font> font) {
        super(font);
        this.font = font;
        this.characterManager = new CharacterManager((charID, style) -> {
            return this.getFont(dungeonFont).getGlyphInfo(charID).getAdvance();
        });
    }

    private Font getFont(ResourceLocation fontLocation) {
        return this.font.apply(fontLocation);
    }

    @Override
    public CharacterManager getSplitter() {
        return characterManager;
    }


}
