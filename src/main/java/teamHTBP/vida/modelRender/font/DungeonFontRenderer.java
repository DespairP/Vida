package teamHTBP.vida.modelRender.font;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.fonts.Font;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.CharacterManager;

import java.util.function.Function;

public class DungeonFontRenderer extends FontRenderer {
    private final CharacterManager characterManager;
    private final Function<ResourceLocation, Font> font;
    public final ResourceLocation dungeonFont = new ResourceLocation("vida","font/dungeonfont");

    public DungeonFontRenderer(Function<ResourceLocation, Font> font) {
        super(font);
        this.font = font;
        this.characterManager = new CharacterManager((charID, style) -> {
            return this.getFont(dungeonFont).func_238557_a_(charID).getAdvance();
        });
    }

    private Font getFont(ResourceLocation fontLocation) {
        return this.font.apply(fontLocation);
    }

    @Override
    public CharacterManager getCharacterManager() {
        return characterManager;
    }


}
