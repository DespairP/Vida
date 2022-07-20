package teamHTBP.vida.helper.elementHelper;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import teamHTBP.vida.utils.color.RGBAColor;

import java.util.List;
import java.util.Objects;

public interface IElement {
    List<ResourceKey<Biome>> getContainsBiomes();

    default boolean contains(Biome biome) {
        return getContainsBiomes().contains(ResourceKey.create(Registry.BIOME_REGISTRY,Objects.requireNonNull(biome.getRegistryName())));
    }

    default boolean contains(ResourceKey<Biome> biome) {
        return getContainsBiomes().contains(biome);
    }

    default boolean contains(ResourceLocation name) {
        return name != null && getContainsBiomes().stream().anyMatch((key) -> key.location().getPath().equals(name.getPath()));
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

    RGBAColor getElementRGBAColor();

    default boolean conflict(IElement element) {
        return element != null && element == getConflict();
    }

    default boolean interGrowth(IElement element) {
        return element != null && element == getInterGrowth();
    }

    ResourceLocation getElementName();
}
