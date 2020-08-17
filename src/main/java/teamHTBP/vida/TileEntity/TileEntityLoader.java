package teamHTBP.vida.TileEntity;


import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Vida;


public class TileEntityLoader {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Vida.modId);
    public static RegistryObject<TileEntityType<TileEntityPurfiedCauldron>> TileEntityPurfiedCauldron = TILE_ENTITY_DEFERRED_REGISTER.register("tepurfiedcauldron", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityPurfiedCauldron();},
                BlockLoader.purfiedCauldron.get()).build(null);
    });
    public static RegistryObject<TileEntityType<TileEntityGemShower>> TileEntityGemShower = TILE_ENTITY_DEFERRED_REGISTER.register("tegemshower", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityGemShower();},
                BlockLoader.gemShower.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityElementCoreAltar>> TileEntityElementCoreAltar = TILE_ENTITY_DEFERRED_REGISTER.register("teelementcorealtar", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityElementCoreAltar();},
                BlockLoader.elementcoreAltar.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityPrismTable>> TileEntityPrismTable = TILE_ENTITY_DEFERRED_REGISTER.register("teprismtable", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityPrismTable();},
                BlockLoader.prismTable.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityOreReationMachine>> TileEntityOreReationMachine = TILE_ENTITY_DEFERRED_REGISTER.register("teorereactionmachine", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityOreReationMachine();},
                BlockLoader.oreReactionMachine.get()).build(null);
    });

    public static RegistryObject<TileEntityType<TileEntityCollector>> TileEntityCollector = TILE_ENTITY_DEFERRED_REGISTER.register("tecollector", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityCollector();},
                BlockLoader.collector.get()).build(null);
    });
}
