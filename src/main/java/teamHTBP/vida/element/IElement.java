package teamHTBP.vida.element;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistryEntry;
import teamHTBP.vida.helper.Allelopathy;

import javax.annotation.Nullable;
import java.util.List;

public interface IElement extends IForgeRegistryEntry<IElement> {
    List<RegistryKey<Biome>> getContainsBiomes();

    default boolean contains(Biome biome) {
        return getContainsBiomes().contains(biome);
    }

    default boolean contains(RegistryKey<Biome> biome) {
        return getContainsBiomes().contains(biome);
    }

    default boolean contains(ResourceLocation name) {
        return name != null && getContainsBiomes().stream().anyMatch((key) -> key.getLocation().getPath().equals(name.getPath()));
    }

    default Allelopathy test(IElement element) {
        if (this == element) {
            return Allelopathy.Identical;
        } else if (conflict(element)) {
            return Allelopathy.Conflict;
        } else if (interGrowth(element)) {
            return Allelopathy.InterGrowth;
        } else {
            return Allelopathy.UnDefined;
        }
    }

    IElement getConflict();

    IElement getInterGrowth();

    default boolean conflict(IElement element) {
        return element != null && element == getConflict();
    }

    default boolean interGrowth(IElement element) {
        return element != null && element == getInterGrowth();
    }

    @Nullable
    @Override
    ResourceLocation getRegistryName();

    @Override
    IElement setRegistryName(ResourceLocation name);

    @Override
    default Class<IElement> getRegistryType() {
        return IElement.class;
    }
}
