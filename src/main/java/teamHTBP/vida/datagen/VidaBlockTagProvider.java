package teamHTBP.vida.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.VidaBlockRegistry;

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
                .add(VidaBlockRegistry.CORE_ALTAR.get())
                .add(VidaBlockRegistry.injectionTable.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(VidaBlockRegistry.DEEPSEA_BRICKS_CORNER.get())
                .add(VidaBlockRegistry.DEEPSEA_PILLAR_0.get())
                .add(VidaBlockRegistry.DEEPSEA_PILLAR_1.get())
                .add(VidaBlockRegistry.DEEPSEA_PILLAR_2.get())
                .add(VidaBlockRegistry.DEEPSEA_BRICKS_STRAIGHT.get())
                .add(VidaBlockRegistry.DIM_BRICKS.get())
                .add(VidaBlockRegistry.DIM_BRICKS_DECO_0.get())
                .add(VidaBlockRegistry.DIM_BRICKS_DECO_1.get())
                .add(VidaBlockRegistry.GEM_STAND.get())
                .add(VidaBlockRegistry.DEEPSEA_STONE.get())
                .add(VidaBlockRegistry.prismTable.get())
                .add(VidaBlockRegistry.CORE_ALTAR.get())
                .add(VidaBlockRegistry.injectionTable.get());

        tag(BlockTags.FENCES)
                .add(VidaBlockRegistry.PLANK_VIDA_FENCE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(VidaBlockRegistry.PLANK_VIDA_FENCE.get());
    }

    @Override
    public String getName() {
        return Vida.MOD_ID + " Tags";
    }
}