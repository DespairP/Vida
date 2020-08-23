package teamHTBP.vida.Entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

public class EntityLoader {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Vida.modId);
    public static RegistryObject<EntityType<EntityFaintLight>> faintLight = ENTITY_TYPES.register("faintlight", () -> {
        return EntityType.Builder.create((EntityType<EntityFaintLight> entityType, World world) -> {
            return new EntityFaintLight(entityType, world);
        }, EntityClassification.MISC).size(0.3F, 0.3F).build("faintlight");
    });
}
