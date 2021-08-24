package teamHTBP.vida.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import teamHTBP.vida.capability.blueprintCapability.IBlueprintCapability;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityLoader {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(
                IElementEnergyCapability.class,
                new Capability.IStorage<IElementEnergyCapability>() {
                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IElementEnergyCapability> capability, IElementEnergyCapability instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IElementEnergyCapability> capability, IElementEnergyCapability instance, Direction side, INBT nbt) {

                    }
                },
                () -> null
        );
    }

    @SubscribeEvent
    public static void registerPlayerCapability(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(IBlueprintCapability.class, new Capability.IStorage<IBlueprintCapability>() {

                    @Nullable
                    @Override
                    public INBT writeNBT(Capability<IBlueprintCapability> capability, IBlueprintCapability instance, Direction side) {
                        return null;
                    }

                    @Override
                    public void readNBT(Capability<IBlueprintCapability> capability, IBlueprintCapability instance, Direction side, INBT nbt) {

                    }
                },
                () -> null
        );
    }

}
