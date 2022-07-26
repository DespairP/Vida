package teamHTBP.vida.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;

public class VidaEntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Vida.MOD_ID);
    public static RegistryObject<EntityType<FaintLight>> faintLight = ENTITY_TYPES.register("faintlight", () ->
            EntityType.Builder.of((EntityType.EntityFactory<FaintLight>) FaintLight::new, MobCategory.MISC)
                    .sized(0.3F, 0.3F)
                    .build("faintlight"));

    static <T extends Entity> RegistryObject<EntityType<T>> registerMisc(String name, EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder
                .of(factory, MobCategory.MISC)
                .sized(.3F, .3F)
                .setUpdateInterval(1)
                .build(name));
    }
}
