package teamHTBP.vida.Block;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

/**
 * 注册方块类
 *
 * **/
public class BlockLoader {
    public final static DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Vida.modId);
    public static RegistryObject<Block> logVida = BLOCKS.register("logvida",() -> new BlockLogVida());
    public static RegistryObject<Block> leavesVida = BLOCKS.register("leavesvida",() -> new BlockLeavesVida());
}
