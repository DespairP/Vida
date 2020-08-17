package teamHTBP.vida.gui.GUI;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.TileEntity.SlotNumberArray.OreReactionMachineArray;
import teamHTBP.vida.TileEntity.SlotNumberArray.PrismTableArray;
import teamHTBP.vida.Vida;

public class ContainerTypeLoader {
    public final static DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Vida.modId);

    public static RegistryObject<ContainerType<ContainerPrismTable>> prismTable = CONTAINER_TYPES.register("container_prismtable", () ->{
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer)->{
            return new ContainerPrismTable(id, inventory, buffer.readBlockPos(), inventory.player.world,new PrismTableArray());
        });
    });

    public static RegistryObject<ContainerType<ContainerOreReactionMachine>> oreReaction = CONTAINER_TYPES.register("container_orereactionmachine", () ->{
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer)->{
            return new ContainerOreReactionMachine(id, inventory, buffer.readBlockPos(), inventory.player.world,new OreReactionMachineArray());
        });
    });

    public static RegistryObject<ContainerType<ContainerBottles>> bottles = CONTAINER_TYPES.register("container_bottles", () ->{
        return IForgeContainerType.create((int id, PlayerInventory inventory, PacketBuffer buffer)->{
            return new ContainerBottles(id, inventory, buffer.readItemStack());
        });
    });

}
