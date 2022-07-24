package teamHTBP.vida.blockentity;


import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.block.VidaBlockRegistry;
import teamHTBP.vida.blockentity.crystal.*;


public class TileEntityLoader {
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Vida.MOD_ID);
    public static RegistryObject<BlockEntityType<TileEntityPurfiedCauldron>> TileEntityPurfiedCauldron = TILE_ENTITY_DEFERRED_REGISTER.register("tepurfiedcauldron", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityPurfiedCauldron::new,
                VidaBlockRegistry.purfiedCauldron.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<TileEntityGemShower>> TileEntityGemShower = TILE_ENTITY_DEFERRED_REGISTER.register("tegemshower", () -> {
        return BlockEntityType.Builder.of(TileEntityGemShower::new, VidaBlockRegistry.GEM_STAND.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityElementCoreAltar>> TileEntityElementCoreAltar = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcorealtar", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityElementCoreAltar::new,
                VidaBlockRegistry.CORE_ALTAR.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityPrismTable>> TileEntityPrismTable = TILE_ENTITY_DEFERRED_REGISTER.register("teprismtable", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityPrismTable::new,
                VidaBlockRegistry.prismTable.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityOreReationMachine>> TileEntityOreReationMachine = TILE_ENTITY_DEFERRED_REGISTER.register("teorereactionmachine", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityOreReationMachine::new,
                VidaBlockRegistry.oreReactionMachine.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<TileEntityCollector>> TileEntityCollector = TILE_ENTITY_DEFERRED_REGISTER.register("tecollector", () -> {
        return BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityCollector::new,
                VidaBlockRegistry.collector.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<GoldElementCrystalBlockEntity>> TileEntityCrystalGold = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_gold", () -> {
        return BlockEntityType.Builder.of(GoldElementCrystalBlockEntity::new,
                VidaBlockRegistry.elementCrystalGold.get()).build(null);
    });

    public static RegistryObject<BlockEntityType<WoodElementCrystalBlockEntity>> TileEntityCrystalWood = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_wood", () -> {
        return BlockEntityType.Builder.of(WoodElementCrystalBlockEntity::new,
                VidaBlockRegistry.elementCrystalWood.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<AquaCrystalBlockEntity>> TileEntityCrystalAqua = TILE_ENTITY_DEFERRED_REGISTER
            .register("teelementcrystal_fire", () -> BlockEntityType.Builder.of(AquaCrystalBlockEntity::new,
                    VidaBlockRegistry.elementCrystalAqua.get()).build(null));
    public static RegistryObject<BlockEntityType<FireElementCrystalBlockEntity>> TileEntityCrystalFire = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_aqua", () -> {
        return BlockEntityType.Builder.of(FireElementCrystalBlockEntity::new,
                VidaBlockRegistry.elementCrystalFire.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<EarthCrystalBlockEntity>> TileEntityCrystalEarth = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcrystal_earth", () -> {
        return BlockEntityType.Builder.of(EarthCrystalBlockEntity::new,
                VidaBlockRegistry.elementCrystalEarth.get()).build(null);
    });
    public static RegistryObject<BlockEntityType<TileEntityInjectTable>> TileEntityInjectTable =
            TILE_ENTITY_DEFERRED_REGISTER.register("teinjecttable",
                    () -> BlockEntityType.Builder.of(teamHTBP.vida.blockentity.TileEntityInjectTable::new,
                            VidaBlockRegistry.injectionTable.get()).build(null));

}
