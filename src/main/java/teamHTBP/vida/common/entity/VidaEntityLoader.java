package teamHTBP.vida.common.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import teamHTBP.vida.Vida;
import teamHTBP.vida.common.entity.projectile.BarrageShooter;
import teamHTBP.vida.common.entity.projectile.ElementBallProjectile;

public class VidaEntityLoader {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Vida.MOD_ID);
    public static RegistryObject<EntityType<FaintLight>> faintLight = ENTITY_TYPES.register("faintlight", () ->
            EntityType.Builder.of((EntityType.EntityFactory<FaintLight>) FaintLight::new, MobCategory.MISC)
                    .sized(0.3F, 0.3F)
                    .build("faintlight"));

    public static final RegistryObject<EntityType<AncientBeliever>> ANCIENT_BELIEVER =
            ENTITY_TYPES.register("ancient_believer", () -> EntityType.Builder.of(AncientBeliever::new, MobCategory.MONSTER)
                    .sized(1, 2)
                    .build("ancient_believer"));

    public static final RegistryObject<EntityType<SilentBeliever>> SILENT_BELIEVER =
            ENTITY_TYPES.register("silent_believer", () -> EntityType.Builder.of(SilentBeliever::new, MobCategory.MONSTER)
                    .sized(1, 2)
                    .build("silent_believer"));

    public static final RegistryObject<EntityType<ElementBallProjectile>> ELEMENT_BALL =
            registerMisc("element_ball", ElementBallProjectile::new);

    public static final RegistryObject<EntityType<BarrageShooter>> BARRAGE_SHOOTER =
            registerMisc("barrage_shooter", BarrageShooter::new);

    static <T extends Entity> RegistryObject<EntityType<T>> registerMisc(String name, EntityType.EntityFactory<T> factory) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder
                .of(factory, MobCategory.MISC)
                .sized(.3F, .3F)
                .setUpdateInterval(1)
                .build(name));
    }
}
