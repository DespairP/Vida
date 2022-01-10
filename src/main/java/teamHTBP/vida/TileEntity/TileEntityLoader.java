package teamHTBP.vida.TileEntity;


import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.BlockLoader;


public class TileEntityLoader {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Vida.MOD_ID);
    public static RegistryObject<TileEntityType<TileEntityPurfiedCauldron>> TileEntityPurfiedCauldron = TILE_ENTITY_DEFERRED_REGISTER.register("tepurfiedcauldron", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityPurfiedCauldron();
                },
                BlockLoader.purfiedCauldron.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityGemShower>> TileEntityGemShower = TILE_ENTITY_DEFERRED_REGISTER.register("tegemshower", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityGemShower();
                },
                BlockLoader.gemShower.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityElementCoreAltar>> TileEntityElementCoreAltar = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcorealtar", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityElementCoreAltar();
                },
                BlockLoader.elementcoreAltar.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityPrismTable>> TileEntityPrismTable = TILE_ENTITY_DEFERRED_REGISTER.register("teprismtable", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityPrismTable();
                },
                BlockLoader.prismTable.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityOreReationMachine>> TileEntityOreReationMachine = TILE_ENTITY_DEFERRED_REGISTER.register("teorereactionmachine", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityOreReationMachine();
                },
                BlockLoader.oreReactionMachine.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityCollector>> TileEntityCollector = TILE_ENTITY_DEFERRED_REGISTER.register("tecollector", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityCollector();
                },
                BlockLoader.collector.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityGoldElementCrystal>> TileEntityCrystalGold = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_gold", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityGoldElementCrystal(1);
                },
                BlockLoader.elementCrystalGold.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityWoodElementCrystal>> TileEntityCrystalWood = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_wood", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityWoodElementCrystal(2);
                },
                BlockLoader.elementCrystalWood.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityAquaElementCrystal>> TileEntityCrystalAqua = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_fire", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityAquaElementCrystal(3);
                },
                BlockLoader.elementCrystalAqua.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityFireElementCrystal>> TileEntityCrystalFire = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_aqua", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityFireElementCrystal(4);
                },
                BlockLoader.elementCrystalFire.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityEarthElementCrystal>> TileEntityCrystalEarth = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_earth", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityEarthElementCrystal(5);
                },
                BlockLoader.elementCrystalEarth.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityInjectTable>> TileEntityInjectTable = TILE_ENTITY_DEFERRED_REGISTER.register("teinjecttable", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityInjectTable();
                },
                BlockLoader.injectionTable.get()).build(null);
    });

}
