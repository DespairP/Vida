package teamHTBP.vida.common.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import teamHTBP.vida.Vida;

public class EntityLoader {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Vida.MOD_ID);
    public static RegistryObject<EntityType<EntityFaintLight>> faintLight =
            ENTITY_TYPES.register("faintlight", () ->
                 EntityType.Builder.create((EntityType.IFactory<EntityFaintLight>) EntityFaintLight::new, EntityClassification.MISC)
                         .size(0.3F, 0.3F)
                         .build("faintlight"));
    public static final RegistryObject<EntityType<AncientBeliever>> ANCIENT_BELIEVER =
            ENTITY_TYPES.register("ancient_believer", () ->
                    EntityType.Builder.create((EntityType.IFactory<AncientBeliever>) AncientBeliever::new, EntityClassification.MONSTER)
                            .size(1.0F, 2.0F)
                            .build("ancient_believer"));

}
