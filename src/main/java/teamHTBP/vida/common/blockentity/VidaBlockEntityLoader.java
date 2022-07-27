package teamHTBP.vida.common.blockentity;


import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.block.VidaBlockLoader;
import teamHTBP.vida.common.blockentity.crystal.*;


public class VidaBlockEntityLoader {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Vida.MOD_ID);
    public static RegistryObject<BlockEntityType<PurifiedCauldronBlockEntity>> TileEntityPurfiedCauldron = TILE_ENTITY_DEFERRED_REGISTER.register("tepurfiedcauldron", () -> {
        return BlockEntityType.Builder.of(PurifiedCauldronBlockEntity::new,
                VidaBlockLoader.purfiedCauldron.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<GemShowerBlockEntity>> TileEntityGemShower = TILE_ENTITY_DEFERRED_REGISTER.register("tegemshower", () -> {
        return BlockEntityType.Builder.of(GemShowerBlockEntity::new, VidaBlockLoader.GEM_STAND.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<ElementCoreAltarBlockEntity>> TileEntityElementCoreAltar = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcorealtar", () -> {
        return BlockEntityType.Builder.of(ElementCoreAltarBlockEntity::new,
                VidaBlockLoader.CORE_ALTAR.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<PrismTableBlockEntity>> TileEntityPrismTable = TILE_ENTITY_DEFERRED_REGISTER.register("teprismtable", () -> {
        return BlockEntityType.Builder.of(PrismTableBlockEntity::new,
                VidaBlockLoader.prismTable.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<OreReationMachineBlockEntity>> TileEntityOreReationMachine = TILE_ENTITY_DEFERRED_REGISTER.register("teorereactionmachine", () -> {
        return BlockEntityType.Builder.of(OreReationMachineBlockEntity::new,
                VidaBlockLoader.oreReactionMachine.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<CollectorBlockEntity>> TileEntityCollector = TILE_ENTITY_DEFERRED_REGISTER.register("tecollector", () -> {
        return BlockEntityType.Builder.of(CollectorBlockEntity::new,
                VidaBlockLoader.collector.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<GoldElementCrystalBlockEntity>> TileEntityCrystalGold = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_gold", () -> {
        return BlockEntityType.Builder.of(GoldElementCrystalBlockEntity::new,
                VidaBlockLoader.GOLD_CRYSTAL.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<WoodElementCrystalBlockEntity>> TileEntityCrystalWood = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_wood", () -> {
        return BlockEntityType.Builder.of(WoodElementCrystalBlockEntity::new,
                VidaBlockLoader.WOOD_CRYSTAL.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<AquaCrystalBlockEntity>> TileEntityCrystalAqua = TILE_ENTITY_DEFERRED_REGISTER
            .register("teelementcrystal_fire", () -> BlockEntityType.Builder.of(AquaCrystalBlockEntity::new,
                    VidaBlockLoader.AQUA_CRYSTAL.get()).build(null));
    public static RegistryObject<BlockEntityType<FireElementCrystalBlockEntity>> TileEntityCrystalFire = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_aqua", () -> {
        return BlockEntityType.Builder.of(FireElementCrystalBlockEntity::new,
                VidaBlockLoader.FIRE_CRYSTAL.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<EarthCrystalBlockEntity>> TileEntityCrystalEarth = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_earth", () -> {
        return BlockEntityType.Builder.of(EarthCrystalBlockEntity::new,
                VidaBlockLoader.EARTH_CRYSTAL.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<InjectTableBlockEntity>> TileEntityInjectTable =
            TILE_ENTITY_DEFERRED_REGISTER.register("teinjecttable",
                    () -> BlockEntityType.Builder.of(InjectTableBlockEntity::new,
                            VidaBlockLoader.injectionTable.get()).build(null));

}
