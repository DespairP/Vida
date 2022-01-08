package teamHTBP.vida.block;

import net.minecraft.block.Block;
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
import teamHTBP.vida.helper.element.EnumElements;
import teamHTBP.vida.helper.ColorHelper;
import teamHTBP.vida.helper.RegisterItemBlock;
import teamHTBP.vida.item.ItemLoader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 注册方块类
 **/
public class BlockLoader {
    public final static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Vida.modId);
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
    public static final List<RegistryObject<Block>> REGISTRY_OBJECT_LIST = new LinkedList<>();
    public static BlockDecoFactory basicFactory = BlockDecoFactory.createBuilder().build();
    //朽木方块
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PLANKS_DECO = BLOCKS.register("lush_planks_deco", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_0 = BLOCKS.register("lush_pillar_0", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_1 = BLOCKS.register("lush_pillar_1", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_2 = BLOCKS.register("lush_pillar_2", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_3 = BLOCKS.register("lush_pillar_3", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    @RegisterItemBlock
    public final static RegistryObject<Block> LUSH_PILLAR_4 = BLOCKS.register("lush_pillar_4", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.YAXIS));
    public final static RegistryObject<Block> LUSH_PLANKS = BLOCKS.register("lush_planks", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    //沃土砖
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS = BLOCKS.register("fertilesoil_bricks", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_0 = BLOCKS.register("fertilesoil_bricks_deco_0", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_1 = BLOCKS.register("fertilesoil_bricks_deco_1", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_2 = BLOCKS.register("fertilesoil_bricks_deco_2", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_3 = BLOCKS.register("fertilesoil_bricks_deco_3", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> FERTILESOIL_BRICKS_DECO_4 = BLOCKS.register("fertilesoil_bricks_deco_4", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    //炎狱砖
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS = BLOCKS.register("netherfire_bricks", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_0 = BLOCKS.register("netherfire_bricks_deco_0", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_1 = BLOCKS.register("netherfire_bricks_deco_1", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_2 = BLOCKS.register("netherfire_bricks_deco_2", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_3 = BLOCKS.register("netherfire_bricks_deco_3", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> NETHERFIRE_BRICKS_DECO_4 = BLOCKS.register("netherfire_bricks_deco_4", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    //白金砖
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS = BLOCKS.register("platinum_bricks", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_0 = BLOCKS.register("platinum_bricks_deco_0", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_1 = BLOCKS.register("platinum_bricks_deco_1", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_2 = BLOCKS.register("platinum_bricks_deco_2", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_3 = BLOCKS.register("platinum_bricks_deco_3", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public final static RegistryObject<Block> PLATINUM_BRICKS_DECO_4 = BLOCKS.register("platinum_bricks_deco_4", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_STRAIGHT = BLOCKS.register("deepsea_bricks_straight", BlockDeepStoneBrickStraight::new);
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_BRICKS_CORE = BLOCKS.register("deepsea_bricks_core", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_0 = BLOCKS.register("deepsea_pillar_0", () -> new BlockDeepStoneBrickPillar(0));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_1 = BLOCKS.register("deepsea_pillar_1", () -> new BlockDeepStoneBrickPillar(1));
    @RegisterItemBlock
    public static RegistryObject<Block> DEEPSEA_PILLAR_2 = BLOCKS.register("deepsea_pillar_2", () -> new BlockDeepStoneBrickPillar(2));
    //砖
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS = BLOCKS.register("verdant_bricks", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_0 = BLOCKS.register("verdant_bricks_deco_0", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_1 = BLOCKS.register("verdant_bricks_deco_1", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_2 = BLOCKS.register("verdant_bricks_deco_2", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_3 = BLOCKS.register("verdant_bricks_deco_3", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
    @RegisterItemBlock
    public static RegistryObject<Block> VERDANT_BRICKS_DECO_4 = BLOCKS.register("verdant_bricks_deco_4", () -> basicFactory.produceDecoBlock(BlockDecoFactory.DecoBlockType.BASIC));
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
    public static RegistryObject<Block> gemShower = BLOCKS.register("gemshower", BlockGemShower::new);
    public static RegistryObject<Block> elementcoreAltar = BLOCKS.register("elementcorealtar", BlockElementCoreAltar::new);
    public static RegistryObject<Block> prismTable = BLOCKS.register("prismtable", BlockPrismTable::new);
    public static RegistryObject<Block> oreReactionMachine = BLOCKS.register("orereactionmachine", BlockOreReactionMachine::new);
    public static RegistryObject<Block> collector = BLOCKS.register("collector", BlockCollecter::new);
    public static RegistryObject<Block> injectionTable = BLOCKS.register("injecttable", BlockInjectTable::new);
    public static RegistryObject<Block> altarcubeMaker = BLOCKS.register("altarcubemaker", BlockAltarCubeMaker::new);
    public static RegistryObject<Block> steleLife = BLOCKS.register("lifestele", BlockSteleLife::new);
    public static RegistryObject<Block> blueprintDesigner = BLOCKS.register("blueprintdesigner", BlockBlueprintDesigner::new);
    //植物
    public static RegistryObject<Block> crimsonCrest = BLOCKS.register("crop_crimsoncrest", () -> new BlockElementCropParticle(5, EnumElements.FIRE, () -> {
        return ItemLoader.crimsonCrest.get();
    }, ColorHelper.DARK_RED));
    public static RegistryObject<Block> heartOfWal = BLOCKS.register("crop_heartofwal", () -> new BlockElementCropParticle(5, EnumElements.WOOD, () -> {
        return ItemLoader.heartOfWal.get();
    }, ColorHelper.LIGHT_BROWN));
    public static RegistryObject<Block> nitriteThorns = BLOCKS.register("crop_nitritethorns", () -> new BlockElementCropParticle(5, EnumElements.GOLD, () -> {
        return ItemLoader.nitriteThorns.get();
    }, ColorHelper.DARK_BROWN));
    public static RegistryObject<Block> plamStem = BLOCKS.register("crop_plamstem", () -> new BlockElementCropParticle(5, EnumElements.AQUA, () -> {
        return ItemLoader.plamStem.get();
    }, ColorHelper.DARK_BLUE));
    public static RegistryObject<Block> sullenHydrangea = BLOCKS.register("crop_sullenhydrangea", () -> new BlockElementCropParticle(5, EnumElements.AQUA, () -> {
        return ItemLoader.sullenHydrangea.get();
    }, ColorHelper.PURPLR));
    public static RegistryObject<Block> sweetCyanReed = BLOCKS.register("crop_sweetcyanreed", () -> new BlockElementCropParticle(5, EnumElements.WOOD, () -> {
        return ItemLoader.sweetCyanReed.get();
    }, ColorHelper.CYAN_GREEN));

    //获取所有可以被注册的字段
    public static void init() {
        Arrays.stream(BlockLoader.class.getDeclaredFields()).forEach(registeredBlock -> {
            if (registeredBlock.getType() == RegistryObject.class && registeredBlock.isAnnotationPresent(RegisterItemBlock.class)) {
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
