package teamHTBP.vida.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import teamHTBP.vida.Vida;
import teamHTBP.vida.capability.BlueprintSystem.BlueprintCapabilityProvider;
import teamHTBP.vida.capability.BlueprintSystem.IBlueprintCapability;
import teamHTBP.vida.capability.Energy.IElementEnergyCapability;

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
    public static void registerPlayerCapability(FMLCommonSetupEvent event){
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
