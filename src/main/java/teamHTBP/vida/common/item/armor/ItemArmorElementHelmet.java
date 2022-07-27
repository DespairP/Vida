package teamHTBP.vida.common.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import teamHTBP.vida.client.model.LayerRegistryHandler;
import teamHTBP.vida.client.model.armor.helmet.*;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ItemArmorElementHelmet extends ArmorItem {
    //盔甲属于什么元素
    protected int element = 1;

    public ItemArmorElementHelmet() {
        super(ArmorMaterials.DIAMOND, EquipmentSlot.HEAD, new Properties().tab(VidaItemGroupLoader.vidaItemGroup));
    }

    public ItemArmorElementHelmet(int element) {
        super(ArmorMaterials.DIAMOND, EquipmentSlot.HEAD, new Properties().tab(VidaItemGroupLoader.vidaItemGroup));
        this.element = element;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @org.jetbrains.annotations.Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                AbstractModelElementHelmet model = switch (element) {
                    case 1 -> LayerRegistryHandler.create(ArmorModelGoldHelmet.class);
                    case 2 -> LayerRegistryHandler.create(ArmorModelWoodHelmet.class);
                    case 3 -> LayerRegistryHandler.create(ArmorModelAquaHelmet.class);
                    case 4 -> LayerRegistryHandler.create(ArmorModelFireHelmet.class);
                    case 5 -> LayerRegistryHandler.create(ArmorModelEarthHelmet.class);
                    default -> null;
                };

                //model.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;

                model.young = _default.young;
                model.crouching = _default.crouching;
                model.riding = _default.riding;
                model.rightArmPose = _default.rightArmPose;
                model.leftArmPose = _default.leftArmPose;
                return model;
            }
        });
    }


    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return switch (element) {
            case 1 -> "vida:textures/model/armor/gold_element_armor.png";
            case 2 -> "vida:textures/model/armor/wood_element_armor.png";
            case 3 -> "vida:textures/model/armor/aqua_element_armor.png";
            case 4 -> "vida:textures/model/armor/fire_element_armor.png";
            case 5 -> "vida:textures/model/armor/earth_element_armor.png";
            default -> null;
        };
    }


}
