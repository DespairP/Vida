package teamHTBP.vida.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;

public class VidaEntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Vida.MOD_ID);
    public static RegistryObject<EntityType<FaintLight>> faintLight = ENTITY_TYPES.register("faintlight", () ->
            EntityType.Builder.of((EntityType<FaintLight> entityType, Level world) ->
                    new FaintLight(entityType, world), MobCategory.MISC)
                    .sized(0.3F, 0.3F)
                    .build("faintlight"));
}
