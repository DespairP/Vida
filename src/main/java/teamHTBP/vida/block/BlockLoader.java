package teamHTBP.vida.block;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Item.ItemLoader;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.crop.BlockElementCropParticle;
import teamHTBP.vida.block.crop.BlockSaplingVida;
import teamHTBP.vida.block.decoration.*;
import teamHTBP.vida.block.function.*;
import teamHTBP.vida.helper.ColorHelper;
import teamHTBP.vida.helper.RegisterItemBlock;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 注册方块类
 *
 * **/
public class BlockLoader {
    public static BlockDecoFactory basicFactory = BlockDecoFactory.createBuilder().build();
    public final static DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Vida.modId);


    //深潜石方块
    @RegisterItemBlock
    public final static RegistryObject<Block> DEEPSEA_STONE = BLOCKS.register("deepsea_stone", BlockDeepStone::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DEEPSEA_BRICKS_CORNER = BLOCKS.register("deepsea_bricks_corner", BlockDeepStoneBrickCorner::new);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_STRAIGHT = BLOCKS.register("deepsea_bricks_straight", BlockDeepStoneBrickStraight::new);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_CORE = BLOCKS.register("deepsea_bricks_core",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_0 = BLOCKS.register("deepsea_pillar_0",() -> new BlockDeepStoneBrickPillar(0));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_1 = BLOCKS.register("deepsea_pillar_1",() -> new BlockDeepStoneBrickPillar(1));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_2 = BLOCKS.register("deepsea_pillar_2",() -> new BlockDeepStoneBrickPillar(2));

    //朽木方块
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PLANKS_DECO = BLOCKS.register("lush_planks_deco",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_0 = BLOCKS.register("lush_pillar_0",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_1 = BLOCKS.register("lush_pillar_1",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_2 = BLOCKS.register("lush_pillar_2",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_3 = BLOCKS.register("lush_pillar_3",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_4 = BLOCKS.register("lush_pillar_4",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    public final static RegistryObject<Block> LUSH_PLANKS = BLOCKS.register("lush_planks",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));

    //黯淡石砖
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS = BLOCKS.register("dim_bricks", BlockDimRockBrick::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS_DECO_0 = BLOCKS.register("dim_bricks_deco_0", BlockDimRockBrickDeco::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS_DECO_1 = BLOCKS.register("dim_bricks_deco_1", BlockDimRockBrickDeco::new);

    //沃土砖
    public static RegistryObject<Block> fertileSoilBrick =       BLOCKS.register("fertilesoilbrick",     () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> fertileSoilBrickdeco_0 = BLOCKS.register("fertilesoilbrickdeco0",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> fertileSoilBrickdeco_1 = BLOCKS.register("fertilesoilbrickdeco1",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> fertileSoilBrickdeco_2 = BLOCKS.register("fertilesoilbrickdeco2",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> fertileSoilBrickdeco_3 = BLOCKS.register("fertilesoilbrickdeco3",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> fertileSoilBrickdeco_4 = BLOCKS.register("fertilesoilbrickdeco4",() -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    //炎狱砖
    public static RegistryObject<Block> netherFireBrick =        BLOCKS.register("netherfirebrick",      () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> netherFireBrickdeco_0 =  BLOCKS.register("netherfirebrickdeco0",      () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> netherFireBrickdeco_1 =  BLOCKS.register("netherfirebrickdeco1", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> netherFireBrickdeco_2 =  BLOCKS.register("netherfirebrickdeco2", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> netherFireBrickdeco_3 =  BLOCKS.register("netherfirebrickdeco3", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> netherFireBrickdeco_4 =  BLOCKS.register("netherfirebrickdeco4", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    //白金砖
    public static RegistryObject<Block> platinumBrick =          BLOCKS.register("platinumbrick",        () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> platinumBrickdeco_0 =    BLOCKS.register("platinumbrickdeco0",   () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> platinumBrickdeco_1 =    BLOCKS.register("platinumbrickdeco1",   () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> platinumBrickdeco_2 =    BLOCKS.register("platinumbrickdeco2",   () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> platinumBrickdeco_3 =    BLOCKS.register("platinumbrickdeco3",   () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> platinumBrickdeco_4 =    BLOCKS.register("platinumbrickdeco4",   () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    //砖
    public static RegistryObject<Block> verdantBrick =           BLOCKS.register("verdantbrick",         () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> verdantBrickDeco_0   =   BLOCKS.register("verdantbrickdeco0",    () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> verdantBrickDeco_1   =   BLOCKS.register("verdantbrickdeco1",    () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> verdantBrickDeco_2   =   BLOCKS.register("verdantbrickdeco2",    () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> verdantBrickDeco_3   =   BLOCKS.register("verdantbrickdeco3",    () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    public static RegistryObject<Block> verdantBrickDeco_4   =   BLOCKS.register("verdantbrickdeco4",    () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));

    //水晶
    public static RegistryObject<Block> elementCrystalGold =       BLOCKS.register("goldelementcrystal", BlockElementCrystalGold::new);
    public static RegistryObject<Block> elementCrystalWood =       BLOCKS.register("woodelementcrystal", BlockElementCrystalWood::new);
    public static RegistryObject<Block> elementCrystalAqua =       BLOCKS.register("aquaelementcrystal", BlockElementCrystalAqua::new);
    public static RegistryObject<Block> elementCrystalFire =       BLOCKS.register("fireelementcrystal", BlockElementCrystalFire::new);
    public static RegistryObject<Block> elementCrystalEarth =      BLOCKS.register("earthelementcrystal", BlockElementCrystalEarth::new);

    //生命原木
    public static RegistryObject<Block> logVida = BLOCKS.register("logvida",() -> new BlockLogVida());
    public static RegistryObject<Block> leavesVida = BLOCKS.register("leavesvida",() -> new BlockLeavesVida());
    public static RegistryObject<Block> saplingVida = BLOCKS.register("saplingvida",() -> new BlockSaplingVida());
    public static RegistryObject<Block> purfiedCauldron = BLOCKS.register("purfiedcauldron",() -> new BlockPurfiedCauldron());
    public static RegistryObject<Block> plankVida_0 = BLOCKS.register("plankvida0",() -> new BlockVidaPlank(0));
    public static RegistryObject<Block> plankVida_1 = BLOCKS.register("plankvida1",() -> new BlockVidaPlank(1));
    public static RegistryObject<Block> plankVida_2 = BLOCKS.register("plankvida2",() -> new BlockVidaPlank(2));

    //矿石
    public static RegistryObject<Block> goldElementOre = BLOCKS.register("goldelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> woodElementOre = BLOCKS.register("woodelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> aquaElementOre = BLOCKS.register("aquaelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> fireElementOre = BLOCKS.register("fireelementore",() -> new BlockOreElement());
    public static RegistryObject<Block> earthElementOre = BLOCKS.register("earthelementore",() -> new BlockOreElement());

    //功能性方块
    public static RegistryObject<Block> gemShower = BLOCKS.register("gemshower", BlockGemShower::new);
    public static RegistryObject<Block> elementcoreAltar = BLOCKS.register("elementcorealtar", BlockElementCoreAltar::new);
    public static RegistryObject<Block> prismTable = BLOCKS.register("prismtable", BlockPrismTable::new);
    public static RegistryObject<Block> oreReactionMachine = BLOCKS.register("orereactionmachine", BlockOreReactionMachine::new);
    public static RegistryObject<Block> collector = BLOCKS.register("collector", BlockCollecter::new);
    public static RegistryObject<Block> injectionTable = BLOCKS.register("injecttable", BlockInjectTable::new);
    public static RegistryObject<Block> altarcubeMaker = BLOCKS.register("altarcubemaker", BlockAltarCubeMaker::new);
    public static RegistryObject<Block> steleLife      = BLOCKS.register("lifestele", BlockSteleLife::new);
    public static RegistryObject<Block> blueprintDesigner      = BLOCKS.register("blueprintdesigner", BlockBlueprintDesigner::new);

    //植物
    public static RegistryObject<Block> crimsonCrest = BLOCKS.register("crop_crimsoncrest",() -> new BlockElementCropParticle(5, 4, () -> {return ItemLoader.crimsonCrest.get();}, ColorHelper.DARK_RED));
    public static RegistryObject<Block> heartOfWal = BLOCKS.register("crop_heartofwal",() -> new BlockElementCropParticle(5, 2,()->{return ItemLoader.heartOfWal.get();},ColorHelper.LIGHT_BROWN));
    public static RegistryObject<Block> nitriteThorns = BLOCKS.register("crop_nitritethorns",() -> new BlockElementCropParticle(5, 1,()->{return ItemLoader.nitriteThorns.get();},ColorHelper.DARK_BROWN));
    public static RegistryObject<Block> plamStem = BLOCKS.register("crop_plamstem",() -> new BlockElementCropParticle(5, 3,()->{return ItemLoader.plamStem.get();},ColorHelper.DARK_BLUE));
    public static RegistryObject<Block> sullenHydrangea = BLOCKS.register("crop_sullenhydrangea",() -> new BlockElementCropParticle(5, 3,()->{return ItemLoader.sullenHydrangea.get();},ColorHelper.PURPLR));
    public static RegistryObject<Block> sweetCyanReed = BLOCKS.register("crop_sweetcyanreed",() -> new BlockElementCropParticle(5, 2, ()->{return ItemLoader.sweetCyanReed.get();},ColorHelper.CYAN_GREEN));


    public static final List<RegistryObject<Block>> REGISTRY_OBJECT_LIST = new LinkedList<>();

    //获取所有可以被注册的字段
    public static void init(){
        Arrays.stream(BlockLoader.class.getDeclaredFields()).forEach(registeredBlock->{
            if(registeredBlock.getType() == RegistryObject.class && registeredBlock.isAnnotationPresent(RegisterItemBlock.class)){
                registeredBlock.setAccessible(true);
                try {
                    REGISTRY_OBJECT_LIST.add((RegistryObject<Block>) registeredBlock.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
