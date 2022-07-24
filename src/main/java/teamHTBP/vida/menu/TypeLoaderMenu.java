package teamHTBP.vida.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.data.OreReactionMachineData;
import teamHTBP.vida.blockentity.data.PrismTableData;

public class TypeLoaderMenu {
    public final static DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Vida.MOD_ID);

    public static RegistryObject<MenuType<PrismTableMenu>> prismTable = CONTAINER_TYPES.register("container_prismtable", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new PrismTableMenu(id, inventory, buffer.readBlockPos(), inventory.player.level, new PrismTableData());
        });
    });

    public static RegistryObject<MenuType<OreReactionMachineMenu>> oreReaction = CONTAINER_TYPES.register("container_orereactionmachine", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new OreReactionMachineMenu(id, inventory, buffer.readBlockPos(), inventory.player.level, new OreReactionMachineData());
        });
    });

    public static RegistryObject<MenuType<BottlesMenu>> bottles = CONTAINER_TYPES.register("container_bottles", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new BottlesMenu(id, inventory, buffer.readItem());
        });
    });

    public static RegistryObject<MenuType<BluePrintMenu>> bluePrints = CONTAINER_TYPES.register("container_blueprint", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new BluePrintMenu(id, inventory, buffer.readItem());
        });
    });

    public static RegistryObject<MenuType<InjectTableMenu>> inject = CONTAINER_TYPES.register("container_inject", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new InjectTableMenu(id, inventory, buffer.readItem(), buffer.readBlockPos(), inventory.player.level);
        });
    });
}
