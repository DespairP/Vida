package teamHTBP.vida.client.gui.GUI;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.common.TileEntity.SlotNumberArray.OreReactionMachineArray;
import teamHTBP.vida.common.TileEntity.SlotNumberArray.PrismTableArray;
import teamHTBP.vida.Vida;

public class ContainerTypeLoader {
    public final static DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Vida.MOD_ID);

    public static RegistryObject<ContainerType<ContainerPrismTable>> prismTable = CONTAINER_TYPES.register("container_prismtable", () -> {
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer) -> {
            return new ContainerPrismTable(id, inventory, buffer.readBlockPos(), inventory.player.world, new PrismTableArray());
        });
    });

    public static RegistryObject<ContainerType<ContainerOreReactionMachine>> oreReaction = CONTAINER_TYPES.register("container_orereactionmachine", () -> {
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer) -> {
            return new ContainerOreReactionMachine(id, inventory, buffer.readBlockPos(), inventory.player.world, new OreReactionMachineArray());
        });
    });

    public static RegistryObject<ContainerType<ContainerBottles>> bottles = CONTAINER_TYPES.register("container_bottles", () -> {
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer) -> {
            return new ContainerBottles(id, inventory, buffer.readItemStack());
        });
    });

    public static RegistryObject<ContainerType<ContainerBluePrint>> bluePrints = CONTAINER_TYPES.register("container_blueprint", () -> {
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer) -> {
            return new ContainerBluePrint(id, inventory, buffer.readItemStack());
        });
    });

    public static RegistryObject<ContainerType<ContainerInjectTable>> inject = CONTAINER_TYPES.register("container_inject", () -> {
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer) -> {
            return new ContainerInjectTable(id, buffer.readItemStack(), buffer.readBlockPos(), inventory.player.world);
        });
    });
}
