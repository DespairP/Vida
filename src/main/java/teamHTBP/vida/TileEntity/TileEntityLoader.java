package teamHTBP.vida.TileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.CauldronBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Vida;


public class TileEntityLoader {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_DEFERRED_REGISTER = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Vida.modId);
    public static RegistryObject<TileEntityType<TileEntityPurfiedCauldron>> TileEntityPurfiedCauldron = TILE_ENTITY_DEFERRED_REGISTER.register("tepurfiedcauldron", () -> {
        return TileEntityType.Builder.create(() -> {
                    return new TileEntityPurfiedCauldron();},
                BlockLoader.purfiedCauldron.get()).build(null);
    });


}
