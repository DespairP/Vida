package teamHTBP.vida.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;

/**
 * @author DustW
 **/
public class ModBlockTags extends BlockTagsProvider {

    public ModBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, Vida.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockLoader.CORE_ALTAR.get())
                .add(BlockLoader.injectionTable.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(BlockLoader.DEEPSEA_BRICKS_CORNER.get())
                .add(BlockLoader.DEEPSEA_PILLAR_0.get())
                .add(BlockLoader.DEEPSEA_PILLAR_1.get())
                .add(BlockLoader.DEEPSEA_PILLAR_2.get())
                .add(BlockLoader.DEEPSEA_BRICKS_STRAIGHT.get())
                .add(BlockLoader.DIM_BRICKS.get())
                .add(BlockLoader.DIM_BRICKS_DECO_0.get())
                .add(BlockLoader.DIM_BRICKS_DECO_1.get())
                .add(BlockLoader.GEM_STAND.get())
                .add(BlockLoader.DEEPSEA_STONE.get())
                .add(BlockLoader.prismTable.get())
                .add(BlockLoader.CORE_ALTAR.get())
                .add(BlockLoader.injectionTable.get());

        tag(BlockTags.FENCES)
                .add(BlockLoader.PLANK_VIDA_FENCE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(BlockLoader.PLANK_VIDA_FENCE.get());
    }

    @Override
    public String getName() {
        return Vida.MOD_ID + " Tags";
    }
}