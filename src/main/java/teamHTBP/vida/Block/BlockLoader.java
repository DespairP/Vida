package teamHTBP.vida.Block;

import net.minecraft.block.Block;
import net.minecraft.block.trees.Tree;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.crop.BlockSaplingVida;
import teamHTBP.vida.Block.decoration.BlockVidaPlank;
import teamHTBP.vida.Block.function.BlockPurfiedCauldron;
import teamHTBP.vida.Vida;
import teamHTBP.vida.worldGen.VidaTree;

/**
 * 注册方块类
 *
 * **/
public class BlockLoader {
    public final static DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Vida.modId);
    public static RegistryObject<Block> logVida = BLOCKS.register("logvida",() -> new BlockLogVida());
    public static RegistryObject<Block> leavesVida = BLOCKS.register("leavesvida",() -> new BlockLeavesVida());
    public static RegistryObject<Block> saplingVida = BLOCKS.register("saplingvida",() -> new BlockSaplingVida());
    public static RegistryObject<Block> purfiedCauldron = BLOCKS.register("purfiedcauldron",() -> new BlockPurfiedCauldron());
    public static RegistryObject<Block> plankVida = BLOCKS.register("plankvida",() -> new BlockVidaPlank());


}
