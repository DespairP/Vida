package teamHTBP.vida.common.blockentity;


import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.block.VidaBlockLoader;
import teamHTBP.vida.common.blockentity.crystal.*;


public class VidaBlockEntityLoader {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Vida.MOD_ID);
    public static RegistryObject<BlockEntityType<PurifiedCauldronBlockEntity>> TileEntityPurfiedCauldron = BLOCK_ENTITIES.register("tepurfiedcauldron", () -> {
        return BlockEntityType.Builder.of(PurifiedCauldronBlockEntity::new,
                VidaBlockLoader.purfiedCauldron.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<GemShowerBlockEntity>> TileEntityGemShower = BLOCK_ENTITIES.register("tegemshower", () -> {
        return BlockEntityType.Builder.of(GemShowerBlockEntity::new, VidaBlockLoader.GEM_STAND.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<ElementCoreAltarBlockEntity>> TileEntityElementCoreAltar = BLOCK_ENTITIES.register("teelementcorealtar", () -> {
        return BlockEntityType.Builder.of(ElementCoreAltarBlockEntity::new,
                VidaBlockLoader.CORE_ALTAR.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<PrismTableBlockEntity>> TileEntityPrismTable = BLOCK_ENTITIES.register("teprismtable", () -> {
        return BlockEntityType.Builder.of(PrismTableBlockEntity::new,
                VidaBlockLoader.prismTable.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<OreReationMachineBlockEntity>> TileEntityOreReationMachine = BLOCK_ENTITIES.register("teorereactionmachine", () -> {
        return BlockEntityType.Builder.of(OreReationMachineBlockEntity::new,
                VidaBlockLoader.oreReactionMachine.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<CollectorBlockEntity>> TileEntityCollector = BLOCK_ENTITIES.register("tecollector", () -> {
        return BlockEntityType.Builder.of(CollectorBlockEntity::new,
                VidaBlockLoader.collector.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<GoldElementCrystalBlockEntity>> TileEntityCrystalGold = BLOCK_ENTITIES.register("teelementcrystal_gold", () -> {
        return BlockEntityType.Builder.of(GoldElementCrystalBlockEntity::new,
                VidaBlockLoader.GOLD_CRYSTAL.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<WoodElementCrystalBlockEntity>> TileEntityCrystalWood = BLOCK_ENTITIES.register("teelementcrystal_wood", () -> {
        return BlockEntityType.Builder.of(WoodElementCrystalBlockEntity::new,
                VidaBlockLoader.WOOD_CRYSTAL.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<AquaCrystalBlockEntity>> TileEntityCrystalAqua = BLOCK_ENTITIES
            .register("teelementcrystal_fire", () -> BlockEntityType.Builder.of(AquaCrystalBlockEntity::new,
                    VidaBlockLoader.AQUA_CRYSTAL.get()).build(null));
    public static RegistryObject<BlockEntityType<FireElementCrystalBlockEntity>> TileEntityCrystalFire = BLOCK_ENTITIES.register("teelementcrystal_aqua", () -> {
        return BlockEntityType.Builder.of(FireElementCrystalBlockEntity::new,
                VidaBlockLoader.FIRE_CRYSTAL.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<EarthCrystalBlockEntity>> TileEntityCrystalEarth = BLOCK_ENTITIES.register("teelementcrystal_earth", () -> {
        return BlockEntityType.Builder.of(EarthCrystalBlockEntity::new,
                VidaBlockLoader.EARTH_CRYSTAL.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<InjectTableBlockEntity>> TileEntityInjectTable =
            BLOCK_ENTITIES.register("teinjecttable",
                    () -> BlockEntityType.Builder.of(InjectTableBlockEntity::new,
                            VidaBlockLoader.injectionTable.get()).build(null));

    public static final RegistryObject<BlockEntityType<GemsTableBlockEntity>> GEMS_TABLE = BLOCK_ENTITIES.register("gems_table",
            () -> BlockEntityType.Builder.of(GemsTableBlockEntity::new,
                            VidaBlockLoader.GEMS_TABLE.get()).build(null));

}
