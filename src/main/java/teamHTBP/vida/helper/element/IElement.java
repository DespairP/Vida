package teamHTBP.vida.helper.element;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import teamHTBP.vida.Vida;
import teamHTBP.vida.utils.color.RGBAColor;

import java.util.List;
import java.util.Objects;

public interface IElement {
    List<RegistryKey<Biome>> getContainsBiomes();

    default boolean contains(Biome biome) {
        return getContainsBiomes().contains(RegistryKey.getOrCreateKey(Registry.BIOME_KEY,Objects.requireNonNull(biome.getRegistryName())));
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

    RGBAColor getElementRGBAColor();

    default boolean conflict(IElement element) {
        return element != null && element == getConflict();
    }

    default boolean interGrowth(IElement element) {
        return element != null && element == getInterGrowth();
    }

    ResourceLocation EMPTY_NAME = new ResourceLocation(Vida.MOD_ID, "empty");

    default ResourceLocation getElementName(){return EMPTY_NAME;}
}
