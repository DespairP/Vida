package teamHTBP.vida.block;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.deco.*;
import teamHTBP.vida.block.environment.DeepStoneBlock;
import teamHTBP.vida.block.environment.VidaLeavesBlock;
import teamHTBP.vida.block.environment.VidaLogBlock;
import teamHTBP.vida.block.environment.ElementOreBlock;
import teamHTBP.vida.block.environment.crop.ParticleElementCropBlock;
import teamHTBP.vida.block.environment.crop.VidaSaplingBlock;
import teamHTBP.vida.block.function.*;
import teamHTBP.vida.block.function.crystal.*;
import teamHTBP.vida.helper.RegisterItemBlock;
import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.item.BlockItemAutoRegister;
import teamHTBP.vida.item.VidaItemRegistry;
import teamHTBP.vida.utils.color.ColorHelper;

/**
 * 注册方块类
 *
 * 如果方块带有@RegisterItemBlock注释,无需再注册相关的BlockItem,注册的BlockItem实例可在{@link BlockItemAutoRegister#registerBlockItems(RegistryEvent.Register)}中注册
 *
 **/
public class VidaBlockRegistry {
    public final static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vida.MOD_ID);
    //深潜石方块
    @RegisterItemBlock
    public final static RegistryObject<Block> DEEPSEA_STONE = BLOCKS.register("deepsea_stone", DeepStoneBlock::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DEEPSEA_BRICKS_CORNER = BLOCKS.register("deepsea_bricks_corner", DeepStoneBrickCornerBlock::new);
    //黯淡石砖
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS = BLOCKS.register("dim_bricks", DimRockBrickBlock::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS_DECO_0 = BLOCKS.register("dim_bricks_deco_0", DimRockBrickDecoBlock::new);
    @RegisterItemBlock
    public final static RegistryObject<Block> DIM_BRICKS_DECO_1 = BLOCKS.register("dim_bricks_deco_1", DimRockBrickDecoBlock::new);
    //朽木方块
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PLANKS_DECO = BLOCKS.register("lush_planks_deco", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_0 = BLOCKS.register("lush_pillar_0", DecoBlockFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_1 = BLOCKS.register("lush_pillar_1", DecoBlockFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_2 = BLOCKS.register("lush_pillar_2", DecoBlockFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_3 = BLOCKS.register("lush_pillar_3", DecoBlockFactory::yAxis);
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_4 = BLOCKS.register("lush_pillar_4", DecoBlockFactory::yAxis);

    public final static RegistryObject<Block> LUSH_PLANKS = BLOCKS.register("lush_planks", DecoBlockFactory::normal);
    //沃土砖
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS = BLOCKS.register("fertilesoil_bricks", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_0 = BLOCKS.register("fertilesoil_bricks_deco_0", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_1 = BLOCKS.register("fertilesoil_bricks_deco_1", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_2 = BLOCKS.register("fertilesoil_bricks_deco_2", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_3 = BLOCKS.register("fertilesoil_bricks_deco_3", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_4 = BLOCKS.register("fertilesoil_bricks_deco_4", DecoBlockFactory::normal);
    //炎狱砖
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS = BLOCKS.register("netherfire_bricks", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_0 = BLOCKS.register("netherfire_bricks_deco_0", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_1 = BLOCKS.register("netherfire_bricks_deco_1", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_2 = BLOCKS.register("netherfire_bricks_deco_2", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_3 = BLOCKS.register("netherfire_bricks_deco_3", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_4 = BLOCKS.register("netherfire_bricks_deco_4", DecoBlockFactory::normal);
    //白金砖
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS = BLOCKS.register("platinum_bricks", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_0 = BLOCKS.register("platinum_bricks_deco_0", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_1 = BLOCKS.register("platinum_bricks_deco_1", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_2 = BLOCKS.register("platinum_bricks_deco_2", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_3 = BLOCKS.register("platinum_bricks_deco_3", DecoBlockFactory::normal);
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_4 = BLOCKS.register("platinum_bricks_deco_4", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_STRAIGHT = BLOCKS.register("deepsea_bricks_straight", DeepStoneBrickStraightBlock::new);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_CORE = BLOCKS.register("deepsea_bricks_core", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_0 = BLOCKS.register("deepsea_pillar_0", () -> new DeepStoneBrickPillarBlock(0));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_1 = BLOCKS.register("deepsea_pillar_1", () -> new DeepStoneBrickPillarBlock(1));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_2 = BLOCKS.register("deepsea_pillar_2", () -> new DeepStoneBrickPillarBlock(2));
    //砖
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS = BLOCKS.register("verdant_bricks", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_0 = BLOCKS.register("verdant_bricks_deco_0", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_1 = BLOCKS.register("verdant_bricks_deco_1", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_2 = BLOCKS.register("verdant_bricks_deco_2", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_3 = BLOCKS.register("verdant_bricks_deco_3", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_4 = BLOCKS.register("verdant_bricks_deco_4", DecoBlockFactory::normal);
    //水晶
    public static RegistryObject<Block> elementCrystalGold = BLOCKS.register("goldelementcrystal", GoldCrystalBlock::new);
    public static RegistryObject<Block> elementCrystalWood = BLOCKS.register("woodelementcrystal", WoodCrystalBlock::new);
    public static RegistryObject<Block> elementCrystalAqua = BLOCKS.register("aquaelementcrystal", AquaCrystalBlock::new);
    public static RegistryObject<Block> elementCrystalFire = BLOCKS.register("fireelementcrystal", FireCrystalBlock::new);
    public static RegistryObject<Block> elementCrystalEarth = BLOCKS.register("earthelementcrystal", EarthCrystalBlock::new);
    //生命原木
    public static RegistryObject<Block> logVida = BLOCKS.register("logvida", () -> new VidaLogBlock());
    public static RegistryObject<Block> leavesVida = BLOCKS.register("leavesvida", () -> new VidaLeavesBlock());
    public static RegistryObject<Block> saplingVida = BLOCKS.register("saplingvida", () -> new VidaSaplingBlock());
    public static RegistryObject<Block> purfiedCauldron = BLOCKS.register("purfiedcauldron", () -> new PurfiedCauldronBlock());
    public static RegistryObject<Block> plankVida_0 = BLOCKS.register("plankvida0", () -> new VidaPlankBlock(0));
    public static RegistryObject<Block> plankVida_1 = BLOCKS.register("plankvida1", () -> new VidaPlankBlock(1));
    public static RegistryObject<Block> plankVida_2 = BLOCKS.register("plankvida2", () -> new VidaPlankBlock(2));
    //矿石
    public static RegistryObject<Block> goldElementOre = BLOCKS.register("goldelementore", () -> new ElementOreBlock());
    public static RegistryObject<Block> woodElementOre = BLOCKS.register("woodelementore", () -> new ElementOreBlock());
    public static RegistryObject<Block> aquaElementOre = BLOCKS.register("aquaelementore", () -> new ElementOreBlock());
    public static RegistryObject<Block> fireElementOre = BLOCKS.register("fireelementore", () -> new ElementOreBlock());
    public static RegistryObject<Block> earthElementOre = BLOCKS.register("earthelementore", () -> new ElementOreBlock());
    //功能性方块
    /**宝石展示架*/
    @RegisterItemBlock
    public static RegistryObject<Block> GEM_STAND = BLOCKS.register("gemshower", GemShowerBlock::new);
    /**核心祭坛*/
    @RegisterItemBlock
    public static RegistryObject<Block> CORE_ALTAR = BLOCKS.register("elementcorealtar", ElementCoreAltarBlock::new);
    public static RegistryObject<Block> prismTable = BLOCKS.register("prismtable", PrismTableBlock::new);
    public static RegistryObject<Block> oreReactionMachine = BLOCKS.register("orereactionmachine", OreReactionMachineBlock::new);
    public static RegistryObject<Block> collector = BLOCKS.register("collector", CollecterBlock::new);
    public static RegistryObject<Block> injectionTable = BLOCKS.register("injecttable", InjectTableBlock::new);
    public static RegistryObject<Block> altarcubeMaker = BLOCKS.register("altarcubemaker", AltarCubeMakerBlock::new);
    public static RegistryObject<Block> steleLife = BLOCKS.register("lifestele", SteleLifeBlock::new);
    public static RegistryObject<Block> blueprintDesigner = BLOCKS.register("blueprintdesigner", BlueprintDesignerBlock::new);

    //植物
    public static RegistryObject<Block> CROP_CRISMCREST = BLOCKS.register("crop_crimsoncrest", () -> new ParticleElementCropBlock(5, EnumElements.FIRE, () -> VidaItemRegistry.CROP_CRISMCREST.get(), ColorHelper.DARK_RED));
    public static RegistryObject<Block> CROP_HEARTOFWAL = BLOCKS.register("crop_heartofwal", () -> new ParticleElementCropBlock(5, EnumElements.WOOD, () -> VidaItemRegistry.CROP_HEARTOFWAL.get(), ColorHelper.LIGHT_BROWN));
    public static RegistryObject<Block> CROP_NITRITETHORNS = BLOCKS.register("crop_nitritethorns", () -> new ParticleElementCropBlock(5, EnumElements.GOLD, () -> VidaItemRegistry.CROP_NITRITETHORNS.get(), ColorHelper.DARK_BROWN));
    public static RegistryObject<Block> CROP_PLAMSTEM = BLOCKS.register("crop_plamstem", () -> new ParticleElementCropBlock(5, EnumElements.AQUA, () -> VidaItemRegistry.CROP_PLAMSTEM.get(), ColorHelper.DARK_BLUE));
    public static RegistryObject<Block> CROP_SULLENHYDRANGEA = BLOCKS.register("crop_sullenhydrangea", () -> new ParticleElementCropBlock(5, EnumElements.AQUA, () -> VidaItemRegistry.CROP_SULLENHYDRANGEA.get(), ColorHelper.PURPLR));
    public static RegistryObject<Block> CROP_SWEETCYANREED = BLOCKS.register("crop_sweetcyanreed", () -> new ParticleElementCropBlock(5, EnumElements.WOOD, () -> VidaItemRegistry.CROP_SWEETCYANREED.get(), ColorHelper.CYAN_GREEN));

    //这里添加新的方块
    //@RegisterItemBlock
    //public static RegistryObject<Block> 方块ID = BLOCKS.register("方块ID、全小写、下划线、数字", DecoBlockFactory::normal);
    @RegisterItemBlock
    public static RegistryObject<Block> TEST_BRICK = BLOCKS.register("test_brick", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_0 = BLOCKS.register("silentforest_brick_0", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_1 = BLOCKS.register("silentforest_brick_1", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_2 = BLOCKS.register("silentforest_brick_2", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_3 = BLOCKS.register("silentforest_brick_3", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_4 = BLOCKS.register("silentforest_brick_4", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_5 = BLOCKS.register("silentforest_brick_5", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_6 = BLOCKS.register("silentforest_brick_6", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_7 = BLOCKS.register("silentforest_brick_7", DecoBlockFactory::normal);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_SLAB = BLOCKS.register("silentforest_brick_slab", DecoBlockFactory::slab);

    @RegisterItemBlock
    public static RegistryObject<Block> SILENTFOREST_BRICK_SLAB_1 = BLOCKS.register("silentforest_brick_slab_1", DecoBlockFactory::slab);

    //vidaplank
    @RegisterItemBlock
    public static RegistryObject<Block> PLANK_VIDA_SLAB = BLOCKS.register("plank_vida_slab", DecoBlockFactory::slab);

    @RegisterItemBlock
    public static RegistryObject<Block> PLANK_VIDA_FENCE = BLOCKS.register("plank_vida_fence", DecoBlockFactory::fence);
}
