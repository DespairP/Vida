package teamHTBP.vida.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.block.VidaBlockLoader;

/**
 * @author DustW
 **/
public class VidaBlockTagProvider extends BlockTagsProvider {

    public VidaBlockTagProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Vida.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(VidaBlockLoader.CORE_ALTAR.get())
                .add(VidaBlockLoader.injectionTable.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(VidaBlockLoader.DEEPSEA_BRICKS_CORNER.get())
                .add(VidaBlockLoader.DEEPSEA_PILLAR_0.get())
                .add(VidaBlockLoader.DEEPSEA_PILLAR_1.get())
                .add(VidaBlockLoader.DEEPSEA_PILLAR_2.get())
                .add(VidaBlockLoader.DEEPSEA_BRICKS_STRAIGHT.get())
                .add(VidaBlockLoader.DIM_BRICKS.get())
                .add(VidaBlockLoader.DIM_BRICKS_DECO_0.get())
                .add(VidaBlockLoader.DIM_BRICKS_DECO_1.get())
                .add(VidaBlockLoader.GEM_STAND.get())
                .add(VidaBlockLoader.DEEPSEA_STONE.get())
                .add(VidaBlockLoader.prismTable.get())
                .add(VidaBlockLoader.CORE_ALTAR.get())
                .add(VidaBlockLoader.injectionTable.get());

        tag(BlockTags.FENCES)
                .add(VidaBlockLoader.PLANK_VIDA_FENCE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(VidaBlockLoader.PLANK_VIDA_FENCE.get());
    }

    @Override
    public String getName() {
        return Vida.MOD_ID + " Tags";
    }
}