package teamHTBP.vida.gui.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.SlotNumberArray.OreReactionMachineArray;
import teamHTBP.vida.blockentity.SlotNumberArray.PrismTableArray;

public class ContainerTypeLoader {
    public final static DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Vida.MOD_ID);

    public static RegistryObject<MenuType<ContainerPrismTable>> prismTable = CONTAINER_TYPES.register("container_prismtable", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new ContainerPrismTable(id, inventory, buffer.readBlockPos(), inventory.player.level, new PrismTableArray());
        });
    });

    public static RegistryObject<MenuType<ContainerOreReactionMachine>> oreReaction = CONTAINER_TYPES.register("container_orereactionmachine", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new ContainerOreReactionMachine(id, inventory, buffer.readBlockPos(), inventory.player.level, new OreReactionMachineArray());
        });
    });

    public static RegistryObject<MenuType<ContainerBottles>> bottles = CONTAINER_TYPES.register("container_bottles", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new ContainerBottles(id, inventory, buffer.readItem());
        });
    });

    public static RegistryObject<MenuType<ContainerBluePrint>> bluePrints = CONTAINER_TYPES.register("container_blueprint", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new ContainerBluePrint(id, inventory, buffer.readItem());
        });
    });

    public static RegistryObject<MenuType<ContainerInjectTable>> inject = CONTAINER_TYPES.register("container_inject", () -> {
        return IForgeMenuType.create((int id, Inventory inventory, FriendlyByteBuf buffer) -> {
            return new ContainerInjectTable(id, inventory, buffer.readItem(), buffer.readBlockPos(), inventory.player.level);
        });
    });
}
