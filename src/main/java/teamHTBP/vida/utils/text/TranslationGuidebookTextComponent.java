package teamHTBP.vida.utils.text;

import net.minecraft.util.text.TextComponent;

public class TranslationGuidebookTextComponent extends TextComponent {
    private static final Object[] EMPTY_ARGS = new Object[0];
    /**原模板字符串*/
    private final String key;
    /**替换字符*/
    private final Object[] formatArgs;


    public TranslationGuidebookTextComponent(String translationKey) {
        this.key = translationKey;
        this.formatArgs = EMPTY_ARGS;
    }



    @Override
    public TextComponent copyRaw() {
        return null;
    }
}
