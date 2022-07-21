package teamHTBP.vida.block;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.decoration.*;
import teamHTBP.vida.block.environment.BlockDeepStone;
import teamHTBP.vida.block.environment.BlockLeavesVida;
import teamHTBP.vida.block.environment.BlockLogVida;
import teamHTBP.vida.block.environment.BlockOreElement;
import teamHTBP.vida.block.environment.crop.BlockElementCropParticle;
import teamHTBP.vida.block.environment.crop.BlockSaplingVida;
import teamHTBP.vida.block.function.*;
import teamHTBP.vida.helper.RegisterItemBlock;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.item.ItemBlockLoader;
import teamHTBP.vida.item.ItemLoader;
import teamHTBP.vida.utils.color.ColorHelper;

/**
 * 注册方块类
 *
 * 如果方块带有@RegisterItemBlock注释,无需再注册相关的BlockItem,注册的BlockItem实例可在{@link ItemBlockLoader#registerBlockItems(RegistryEvent.Register)}中注册
 *
 **/
public class BlockLoader {
    public final static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vida.MOD_ID);
    //深潜石方块
    @RegisterItemBlock
    public final static RegistryObject<Block> DEEPSEA_STONE = BLOCKS.register("deepsea_stone", BlockDeepStone::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DEEPSEA_BRICKS_CORNER = BLOCKS.register("deepsea_bricks_corner", BlockDeepStoneBrickCorner::new);
    //黯淡石砖
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS = BLOCKS.register("dim_bricks", BlockDimRockBrick::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS_DECO_0 = BLOCKS.register("dim_bricks_deco_0", BlockDimRockBrickDeco::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS_DECO_1 = BLOCKS.register("dim_bricks_deco_1", BlockDimRockBrickDeco::new);
    //朽木方块
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PLANKS_DECO = BLOCKS.register("lush_planks_deco", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_0 = BLOCKS.register("lush_pillar_0", BlockDecoFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_1 = BLOCKS.register("lush_pillar_1", BlockDecoFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_2 = BLOCKS.register("lush_pillar_2", BlockDecoFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_3 = BLOCKS.register("lush_pillar_3", BlockDecoFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_4 = BLOCKS.register("lush_pillar_4", BlockDecoFactory::yAxis);

    public final static RegistryObject<Block> LUSH_PLANKS = BLOCKS.register("lush_planks", BlockDecoFactory::normal);
    //沃土砖
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS = BLOCKS.register("fertilesoil_bricks", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_0 = BLOCKS.register("fertilesoil_bricks_deco_0", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_1 = BLOCKS.register("fertilesoil_bricks_deco_1", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_2 = BLOCKS.register("fertilesoil_bricks_deco_2", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_3 = BLOCKS.register("fertilesoil_bricks_deco_3", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_4 = BLOCKS.register("fertilesoil_bricks_deco_4", BlockDecoFactory::normal);
    //炎狱砖
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS = BLOCKS.register("netherfire_bricks", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_0 = BLOCKS.register("netherfire_bricks_deco_0", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_1 = BLOCKS.register("netherfire_bricks_deco_1", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_2 = BLOCKS.register("netherfire_bricks_deco_2", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_3 = BLOCKS.register("netherfire_bricks_deco_3", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_4 = BLOCKS.register("netherfire_bricks_deco_4", BlockDecoFactory::normal);
    //白金砖
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS = BLOCKS.register("platinum_bricks", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_0 = BLOCKS.register("platinum_bricks_deco_0", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_1 = BLOCKS.register("platinum_bricks_deco_1", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_2 = BLOCKS.register("platinum_bricks_deco_2", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_3 = BLOCKS.register("platinum_bricks_deco_3", BlockDecoFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_4 = BLOCKS.register("platinum_bricks_deco_4", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_STRAIGHT = BLOCKS.register("deepsea_bricks_straight", BlockDeepStoneBrickStraight::new);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_CORE = BLOCKS.register("deepsea_bricks_core", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_0 = BLOCKS.register("deepsea_pillar_0", () -> new BlockDeepStoneBrickPillar(0));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_1 = BLOCKS.register("deepsea_pillar_1", () -> new BlockDeepStoneBrickPillar(1));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_2 = BLOCKS.register("deepsea_pillar_2", () -> new BlockDeepStoneBrickPillar(2));
    //砖
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS = BLOCKS.register("verdant_bricks", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_0 = BLOCKS.register("verdant_bricks_deco_0", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_1 = BLOCKS.register("verdant_bricks_deco_1", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_2 = BLOCKS.register("verdant_bricks_deco_2", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_3 = BLOCKS.register("verdant_bricks_deco_3", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_4 = BLOCKS.register("verdant_bricks_deco_4", BlockDecoFactory::normal);
    //水晶
    public static RegistryObject<Block> elementCrystalGold = BLOCKS.register("goldelementcrystal", BlockElementCrystalGold::new);
    public static RegistryObject<Block> elementCrystalWood = BLOCKS.register("woodelementcrystal", BlockElementCrystalWood::new);
    public static RegistryObject<Block> elementCrystalAqua = BLOCKS.register("aquaelementcrystal", BlockElementCrystalAqua::new);
    public static RegistryObject<Block> elementCrystalFire = BLOCKS.register("fireelementcrystal", BlockElementCrystalFire::new);
    public static RegistryObject<Block> elementCrystalEarth = BLOCKS.register("earthelementcrystal", BlockElementCrystalEarth::new);
    //生命原木
    public static RegistryObject<Block> logVida = BLOCKS.register("logvida", () -> new BlockLogVida());
    public static RegistryObject<Block> leavesVida = BLOCKS.register("leavesvida", () -> new BlockLeavesVida());
    public static RegistryObject<Block> saplingVida = BLOCKS.register("saplingvida", () -> new BlockSaplingVida());
    public static RegistryObject<Block> purfiedCauldron = BLOCKS.register("purfiedcauldron", () -> new BlockPurfiedCauldron());
    public static RegistryObject<Block> plankVida_0 = BLOCKS.register("plankvida0", () -> new BlockVidaPlank(0));
    public static RegistryObject<Block> plankVida_1 = BLOCKS.register("plankvida1", () -> new BlockVidaPlank(1));
    public static RegistryObject<Block> plankVida_2 = BLOCKS.register("plankvida2", () -> new BlockVidaPlank(2));
    //矿石
    public static RegistryObject<Block> goldElementOre = BLOCKS.register("goldelementore", () -> new BlockOreElement());
    public static RegistryObject<Block> woodElementOre = BLOCKS.register("woodelementore", () -> new BlockOreElement());
    public static RegistryObject<Block> aquaElementOre = BLOCKS.register("aquaelementore", () -> new BlockOreElement());
    public static RegistryObject<Block> fireElementOre = BLOCKS.register("fireelementore", () -> new BlockOreElement());
    public static RegistryObject<Block> earthElementOre = BLOCKS.register("earthelementore", () -> new BlockOreElement());
    //功能性方块
    /**宝石展示架*/
    @RegisterItemBlock
    public static RegistryObject<Block> GEM_STAND = BLOCKS.register("gemshower", BlockGemShower::new);
    /**核心祭坛*/
    @RegisterItemBlock
    public static RegistryObject<Block> CORE_ALTAR = BLOCKS.register("elementcorealtar", BlockElementCoreAltar::new);
    public static RegistryObject<Block> prismTable = BLOCKS.register("prismtable", BlockPrismTable::new);
    public static RegistryObject<Block> oreReactionMachine = BLOCKS.register("orereactionmachine", BlockOreReactionMachine::new);
    public static RegistryObject<Block> collector = BLOCKS.register("collector", BlockCollecter::new);
    public static RegistryObject<Block> injectionTable = BLOCKS.register("injecttable", BlockInjectTable::new);
    public static RegistryObject<Block> altarcubeMaker = BLOCKS.register("altarcubemaker", BlockAltarCubeMaker::new);
    public static RegistryObject<Block> steleLife = BLOCKS.register("lifestele", BlockSteleLife::new);
    public static RegistryObject<Block> blueprintDesigner = BLOCKS.register("blueprintdesigner", BlockBlueprintDesigner::new);

    //植物
    public static RegistryObject<Block> CROP_CRISMCREST = BLOCKS.register("crop_crimsoncrest", () -> new BlockElementCropParticle(5, EnumElements.FIRE, () -> ItemLoader.CROP_CRISMCREST.get(), ColorHelper.DARK_RED));
    public static RegistryObject<Block> CROP_HEARTOFWAL = BLOCKS.register("crop_heartofwal", () -> new BlockElementCropParticle(5, EnumElements.WOOD, () -> ItemLoader.CROP_HEARTOFWAL.get(), ColorHelper.LIGHT_BROWN));
    public static RegistryObject<Block> CROP_NITRITETHORNS = BLOCKS.register("crop_nitritethorns", () -> new BlockElementCropParticle(5, EnumElements.GOLD, () -> ItemLoader.CROP_NITRITETHORNS.get(), ColorHelper.DARK_BROWN));
    public static RegistryObject<Block> CROP_PLAMSTEM = BLOCKS.register("crop_plamstem", () -> new BlockElementCropParticle(5, EnumElements.AQUA, () -> ItemLoader.CROP_PLAMSTEM.get(), ColorHelper.DARK_BLUE));
    public static RegistryObject<Block> CROP_SULLENHYDRANGEA = BLOCKS.register("crop_sullenhydrangea", () -> new BlockElementCropParticle(5, EnumElements.AQUA, () -> ItemLoader.CROP_SULLENHYDRANGEA.get(), ColorHelper.PURPLR));
    public static RegistryObject<Block> CROP_SWEETCYANREED = BLOCKS.register("crop_sweetcyanreed", () -> new BlockElementCropParticle(5, EnumElements.WOOD, () -> ItemLoader.CROP_SWEETCYANREED.get(), ColorHelper.CYAN_GREEN));

    //这里添加新的方块
    //@RegisterItemBlock
    //public static RegistryObject<Block> 方块ID = BLOCKS.register("方块ID、全小写、下划线、数字", BlockDecoFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> TEST_BRICK = BLOCKS.register("test_brick", BlockDecoFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_0 = BLOCKS.register("silentforest_brick_0", BlockDecoFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_1 = BLOCKS.register("silentforest_brick_1", BlockDecoFactory::yAxis);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_2 = BLOCKS.register("silentforest_brick_2", BlockDecoFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_3 = BLOCKS.register("silentforest_brick_3", BlockDecoFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_4 = BLOCKS.register("silentforest_brick_4", BlockDecoFactory::yAxis);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_5 = BLOCKS.register("silentforest_brick_5", BlockDecoFactory::yAxis);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_6 = BLOCKS.register("silentforest_brick_6", BlockDecoFactory::yAxis);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_7 = BLOCKS.register("silentforest_brick_7", BlockDecoFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_8 = BLOCKS.register("silentforest_brick_8", BlockDecoFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_SLAB = BLOCKS.register("silentforest_brick_slab", BlockDecoFactory::slab);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_SLAB_1 = BLOCKS.register("silentforest_brick_slab_1", BlockDecoFactory::slab);

    //vidaplank
    @RegisterItemBlock
    public static RegistryObject<Block> PLANK_VIDA_SLAB = BLOCKS.register("plank_vida_slab", BlockDecoFactory::slab);

    @RegisterItemBlock
    public static RegistryObject<Block> PLANK_VIDA_FENCE = BLOCKS.register("plank_vida_fence", BlockDecoFactory::fence);

    @RegisterItemBlock
    public static RegistryObject<Block> PLANK_VIDA_DOOR = BLOCKS.register("plank_vida_door", BlockDecoFactory::door);
}
