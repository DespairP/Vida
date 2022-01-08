package teamHTBP.vida.helper.element;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import teamHTBP.vida.Vida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumElements implements IElement {
    VOID,
    GOLD(2, 3,
            Biomes.MOUNTAINS,
            Biomes.MOUNTAIN_EDGE),
    WOOD(5, 4,
            Biomes.PLAINS,
            Biomes.FOREST,
            Biomes.BAMBOO_JUNGLE,
            Biomes.FLOWER_FOREST,
            Biomes.JUNGLE,
            Biomes.BIRCH_FOREST,
            Biomes.SAVANNA),
    AQUA(4, 2,
            Biomes.OCEAN,
            Biomes.RIVER,
            Biomes.SNOWY_TAIGA,
            Biomes.COLD_OCEAN,
            Biomes.SNOWY_TUNDRA),
    FIRE(1, 5,
            Biomes.MOUNTAINS,
            Biomes.DESERT,
            Biomes.MOUNTAIN_EDGE),
    EARTH(3, 1,
            Biomes.ERODED_BADLANDS,
            Biomes.DARK_FOREST),
    NONE;

    List<RegistryKey<Biome>> biomes = new ArrayList<>();
    int conflict;
    int interGrowth;

    EnumElements() {

    }

    // todo 重新设计元素和群系的关系之后修改biomes
    @SafeVarargs
    EnumElements(int conflictIndex, int interGrowthIndex, RegistryKey<Biome>... biomes) {
        this.biomes = Arrays.asList(biomes);
        this.interGrowth = interGrowthIndex;
        this.conflict = conflictIndex;
    }

    /**/
    @Override
    public List<RegistryKey<Biome>> getContainsBiomes() {
        return biomes;
    }

    /**/
    @Override
    public IElement getConflict() {
        return values()[conflict];
    }

    /**/
    @Override
    public IElement getInterGrowth() {
        return values()[interGrowth];
    }


    /**/
    @Override
    public IElement setRegistryName(ResourceLocation name) {
        throw new IllegalArgumentException("不允许设置registry name");
    }

    @Override
    public String getElementName() {
        return name();
    }

    /**/
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Vida.modId, name());
    }


}
