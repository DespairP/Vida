package teamHTBP.vida.common.block;

import net.minecraft.block.Block;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;

public class VidaBlockTagLoader {
    public static final TagRegistry<Block> REGISTRY = TagRegistryManager.create(new ResourceLocation("vida","block"), ITagCollectionSupplier::getBlockTags);


}
