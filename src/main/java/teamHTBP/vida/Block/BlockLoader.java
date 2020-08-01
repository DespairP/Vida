package teamHTBP.vida.Block;

import net.minecraft.block.Block;
import net.minecraft.block.trees.Tree;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.crop.BlockSaplingVida;
import teamHTBP.vida.Block.decoration.*;
import teamHTBP.vida.Block.function.BlockElementCoreAltar;
import teamHTBP.vida.Block.function.BlockPurfiedCauldron;
import teamHTBP.vida.Vida;
import teamHTBP.vida.worldGen.VidaTree;

/**
 * 注册方块类
 *
 * **/
public class BlockLoader {
    public final static DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Vida.modId);
    public static RegistryObject<Block> deepStone = BLOCKS.register("deepstone",() -> new BlockDeepStone());
    public static RegistryObject<Block> deepStoneBrick_Corner = BLOCKS.register("deepstonebrickcorner",() -> new BlockDeepStoneBrickCorner());
    public static RegistryObject<Block> deepStoneBrick_Straight = BLOCKS.register("deepstonebrickstraight",() -> new BlockDeepStoneBrickStraight());
    public static RegistryObject<Block> deepStoneBrick_Core = BLOCKS.register("deepstonebrickcore",() -> new BlockDeepStoneBrickCore());
    public static RegistryObject<Block> deepStoneBrick_Pillar_0 = BLOCKS.register("deepstonebrickpillar0",() -> new BlockDeepStoneBrickPillar(0));
    public static RegistryObject<Block> deepStoneBrick_Pillar_1 = BLOCKS.register("deepstonebrickpillar1",() -> new BlockDeepStoneBrickPillar(1));
    public static RegistryObject<Block> deepStoneBrick_Pillar_2 = BLOCKS.register("deepstonebrickpillar2",() -> new BlockDeepStoneBrickPillar(2));
    public static RegistryObject<Block> logVida = BLOCKS.register("logvida",() -> new BlockLogVida());
    public static RegistryObject<Block> leavesVida = BLOCKS.register("leavesvida",() -> new BlockLeavesVida());
    public static RegistryObject<Block> saplingVida = BLOCKS.register("saplingvida",() -> new BlockSaplingVida());
    public static RegistryObject<Block> purfiedCauldron = BLOCKS.register("purfiedcauldron",() -> new BlockPurfiedCauldron());
    public static RegistryObject<Block> plankVida_0 = BLOCKS.register("plankvida0",() -> new BlockVidaPlank(0));
    public static RegistryObject<Block> plankVida_1 = BLOCKS.register("plankvida1",() -> new BlockVidaPlank(1));
    public static RegistryObject<Block> plankVida_2 = BLOCKS.register("plankvida2",() -> new BlockVidaPlank(2));


    public static RegistryObject<Block> goldElementOre = BLOCKS.register("goldelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> woodElementOre = BLOCKS.register("woodelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> aquaElementOre = BLOCKS.register("aquaelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> fireElementOre = BLOCKS.register("fireelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> earthElementOre = BLOCKS.register("earthelementore",() -> new BlockOreElement());

    public static RegistryObject<Block> dimRockBrick = BLOCKS.register("dimrockbrick",() -> new BlockDimRockBrick());
    public static RegistryObject<Block> dimRockBrickdeco_0 = BLOCKS.register("dimrockbrickdeco0",() -> new BlockDimRockBrickDeco());
    public static RegistryObject<Block> dimRockBrickdeco_1 = BLOCKS.register("dimrockbrickdeco1",() -> new BlockDimRockBrickDeco());

    public static RegistryObject<Block> fertileSoilBrick =       BLOCKS.register("fertilesoilbrick",     () -> new BlockFertileSoilBrick());
    public static RegistryObject<Block> fertileSoilBrickdeco_0 = BLOCKS.register("fertilesoilbrickdeco0",() -> new BlockFertileSoilBrickDeco());
    public static RegistryObject<Block> fertileSoilBrickdeco_1 = BLOCKS.register("fertilesoilbrickdeco1",() -> new BlockFertileSoilBrickDeco());
    public static RegistryObject<Block> netherFireBrick =        BLOCKS.register("netherfirebrick",      () -> new BlockDimRockBrick());
    public static RegistryObject<Block> netherFireBrickdeco_0 =  BLOCKS.register("netherfirebrickdeco0",      () -> new BlockNetherfireBrick());
    public static RegistryObject<Block> netherFireBrickdeco_1 =  BLOCKS.register("netherfirebrickdeco1", () -> new BlockNetherfireBrickDeco());
    public static RegistryObject<Block> platinumBrick =          BLOCKS.register("platinumbrick",        () -> new BlockPlatinumBrick());
    public static RegistryObject<Block> platinumBrickdeco_0 =    BLOCKS.register("platinumbrickdeco0",   () -> new BlockPlatinumBrickDeco());
    public static RegistryObject<Block> platinumBrickdeco_1 =    BLOCKS.register("platinumbrickdeco1",   () -> new BlockPlatinumBrickDeco());
    public static RegistryObject<Block> verdantBrick =           BLOCKS.register("verdantbrick",         () -> new BlockVerdantBrick());
    public static RegistryObject<Block> verdantBrickDeco_0   =   BLOCKS.register("verdantbrickdeco0",    () -> new BlockVerdantBrickDeco());
    public static RegistryObject<Block> verdantBrickDeco_1   =   BLOCKS.register("verdantbrickdeco1",    () -> new BlockVerdantBrickDeco());


    public static RegistryObject<Block> gemShower = BLOCKS.register("gemshower",() -> new BlockGemShower());
    public static RegistryObject<Block> elementcoreAltar = BLOCKS.register("elementcorealtar",() -> new BlockElementCoreAltar());

}
