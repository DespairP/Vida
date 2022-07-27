package teamHTBP.vida.common.TileEntity;


import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.block.VidaBlockLoader;


public class TileEntityLoader {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Vida.MOD_ID);
    public static RegistryObject<TileEntityType<TileEntityPurfiedCauldron>> TileEntityPurfiedCauldron = TILE_ENTITY_DEFERRED_REGISTER.register("tepurfiedcauldron", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityPurfiedCauldron();
                },
                VidaBlockLoader.purfiedCauldron.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityGemShower>> TileEntityGemShower = TILE_ENTITY_DEFERRED_REGISTER.register("tegemshower", () -> {
        return TileEntityType.Builder.create(TileEntityGemShower::new, VidaBlockLoader.GEM_STAND.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityElementCoreAltar>> TileEntityElementCoreAltar = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcorealtar", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityElementCoreAltar();
                },
                VidaBlockLoader.CORE_ALTAR.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityPrismTable>> TileEntityPrismTable = TILE_ENTITY_DEFERRED_REGISTER.register("teprismtable", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityPrismTable();
                },
                VidaBlockLoader.prismTable.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityOreReationMachine>> TileEntityOreReationMachine = TILE_ENTITY_DEFERRED_REGISTER.register("teorereactionmachine", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityOreReationMachine();
                },
                VidaBlockLoader.oreReactionMachine.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityCollector>> TileEntityCollector = TILE_ENTITY_DEFERRED_REGISTER.register("tecollector", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityCollector();
                },
                VidaBlockLoader.collector.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityGoldElementCrystal>> TileEntityCrystalGold = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_gold", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityGoldElementCrystal(1);
                },
                VidaBlockLoader.elementCrystalGold.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityWoodElementCrystal>> TileEntityCrystalWood = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_wood", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityWoodElementCrystal(2);
                },
                VidaBlockLoader.elementCrystalWood.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityAquaElementCrystal>> TileEntityCrystalAqua = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_fire", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityAquaElementCrystal(3);
                },
                VidaBlockLoader.elementCrystalAqua.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityFireElementCrystal>> TileEntityCrystalFire = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_aqua", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityFireElementCrystal(4);
                },
                VidaBlockLoader.elementCrystalFire.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityEarthElementCrystal>> TileEntityCrystalEarth = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_earth", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityEarthElementCrystal(5);
                },
                VidaBlockLoader.elementCrystalEarth.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityInjectTable>> TileEntityInjectTable = TILE_ENTITY_DEFERRED_REGISTER.register("teinjecttable", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityInjectTable();
                },
                VidaBlockLoader.injectionTable.get()).build(null);
    });

}
