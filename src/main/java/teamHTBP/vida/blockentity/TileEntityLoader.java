package teamHTBP.vida.blockentity;


import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.blockentity.crystal.*;


public class TileEntityLoader {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Vida.MOD_ID);
    public static RegistryObject<BlockEntityType<TileEntityPurfiedCauldron>> TileEntityPurfiedCauldron = TILE_ENTITY_DEFERRED_REGISTER.register("tepurfiedcauldron", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityPurfiedCauldron::new,
                BlockLoader.purfiedCauldron.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<TileEntityGemShower>> TileEntityGemShower = TILE_ENTITY_DEFERRED_REGISTER.register("tegemshower", () -> {
        return BlockEntityType.Builder.of(TileEntityGemShower::new, BlockLoader.GEM_STAND.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityElementCoreAltar>> TileEntityElementCoreAltar = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcorealtar", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityElementCoreAltar::new,
                BlockLoader.CORE_ALTAR.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityPrismTable>> TileEntityPrismTable = TILE_ENTITY_DEFERRED_REGISTER.register("teprismtable", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityPrismTable::new,
                BlockLoader.prismTable.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityOreReationMachine>> TileEntityOreReationMachine = TILE_ENTITY_DEFERRED_REGISTER.register("teorereactionmachine", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityOreReationMachine::new,
                BlockLoader.oreReactionMachine.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityCollector>> TileEntityCollector = TILE_ENTITY_DEFERRED_REGISTER.register("tecollector", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityCollector::new,
                BlockLoader.collector.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityGoldElementCrystal>> TileEntityCrystalGold = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_gold", () -> {
        return BlockEntityType.Builder.of(TileEntityGoldElementCrystal::new,
                BlockLoader.elementCrystalGold.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityWoodElementCrystal>> TileEntityCrystalWood = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_wood", () -> {
        return BlockEntityType.Builder.of((p, b) -> {
                    return new TileEntityWoodElementCrystal(p, b, 2);
                },
                BlockLoader.elementCrystalWood.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<TileEntityAquaElementCrystal>> TileEntityCrystalAqua = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_fire", () -> {
        return BlockEntityType.Builder.of((p, b) -> {
                    return new TileEntityAquaElementCrystal(p, b, 3);
                },
                BlockLoader.elementCrystalAqua.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<TileEntityFireElementCrystal>> TileEntityCrystalFire = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_aqua", () -> {
        return BlockEntityType.Builder.of((p, b) -> {
                    return new TileEntityFireElementCrystal(p, b, 4);
                },
                BlockLoader.elementCrystalFire.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<TileEntityEarthElementCrystal>> TileEntityCrystalEarth = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_earth", () -> {
        return BlockEntityType.Builder.of((p, b) -> {
                    return new TileEntityEarthElementCrystal(p, b, 5);
                },
                BlockLoader.elementCrystalEarth.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<TileEntityInjectTable>> TileEntityInjectTable =
            TILE_ENTITY_DEFERRED_REGISTER.register("teinjecttable",
                    () -> BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityInjectTable::new,
                            BlockLoader.injectionTable.get()).build(null));

}
