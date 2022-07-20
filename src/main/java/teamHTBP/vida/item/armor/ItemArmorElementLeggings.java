package teamHTBP.vida.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import teamHTBP.vida.creativetab.ItemGroupLoader;
import teamHTBP.vida.modelRender.armormodel.*;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ItemArmorElementLeggings extends ArmorItem {
    public int element = 1;

    public ItemArmorElementLeggings() {
        super(ArmorMaterials.DIAMOND, EquipmentSlot.LEGS, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    public ItemArmorElementLeggings(int element) {
        super(ArmorMaterials.DIAMOND, EquipmentSlot.LEGS, new Properties().tab(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @org.jetbrains.annotations.Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                AbstractModelElementLeggings model = switch (element) {
                    case 1 -> new ArmorModelGoldLeggings();
                    case 2 -> new ArmorModelWoodLeggings();
                    case 3 -> new ArmorModelAquaLeggings();
                    case 4 -> new ArmorModelFireLeggings();
                    case 5 -> new ArmorModelEarthLeggings();
                    default -> null;
                };

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
