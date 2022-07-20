package teamHTBP.vida.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;

public class EntityLoader {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Vida.MOD_ID);
    public static RegistryObject<EntityType<EntityFaintLight>> faintLight = ENTITY_TYPES.register("faintlight", () -> {
        return EntityType.Builder.of((EntityType<EntityFaintLight> entityType, Level world) -> {
            return new EntityFaintLight(entityType, world);
        }, MobCategory.MISC).sized(0.3F, 0.3F).build("faintlight");
    });
}
