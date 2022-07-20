package teamHTBP.vida.block;

import net.minecraft.block.Block;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

public class BlockTagLoader {
    public static final TagRegistry<Block> REGISTRY = TagRegistryManager.create(new ResourceLocation("vida","block"), ITagCollectionSupplier::getBlockTags);


}
