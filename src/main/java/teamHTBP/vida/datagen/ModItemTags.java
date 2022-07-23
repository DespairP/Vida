package teamHTBP.vida.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamHTBP.vida.Vida;

/**
 * @author DustW
 **/
public class ModItemTags extends ItemTagsProvider {

    public ModItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, Vida.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
    }

    @Override
    public String getName() {
        return "Tutorial Tags";
    }
}
