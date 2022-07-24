package teamHTBP.vida.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import teamHTBP.vida.client.model.LayerRegistryHandler;
import teamHTBP.vida.client.model.armor.boots.*;
import teamHTBP.vida.creativetab.ItemGroupRegistry;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ItemArmorElementBoots extends ArmorItem {  //盔甲属于什么元素
    protected int element = 1;

    public ItemArmorElementBoots() {
        super(ArmorMaterials.DIAMOND, EquipmentSlot.FEET, new Properties().tab(ItemGroupRegistry.vidaItemGroup));
    }

    public ItemArmorElementBoots(int element) {
        super(ArmorMaterials.DIAMOND, EquipmentSlot.FEET, new Properties().tab(ItemGroupRegistry.vidaItemGroup));
        this.element = element;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @org.jetbrains.annotations.Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                AbstractModelElementBoots model = switch (element) {
                    case 1 -> LayerRegistryHandler.create(ArmorModelGoldBoots.class);
                    case 2 -> LayerRegistryHandler.create(ArmorModelWoodBoots.class);
                    case 3 -> LayerRegistryHandler.create(ArmorModelGoldBoots.class);
                    case 4 -> LayerRegistryHandler.create(ArmorModelFireBoots.class);
                    case 5 -> LayerRegistryHandler.create(ArmorModelEarthBoots.class);
                    default -> null;
                };

                model.leftLeg.visible = true;
                model.rightLeg.visible = true;
                model.leg_left.visible = true;
                model.leg_right.visible = true;


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
