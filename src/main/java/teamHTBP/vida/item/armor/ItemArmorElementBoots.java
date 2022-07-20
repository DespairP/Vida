package teamHTBP.vida.item.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.itemGroup.ItemGroupLoader;
import teamHTBP.vida.modelRender.armormodel.*;

import javax.annotation.Nullable;

import net.minecraft.item.Item.Properties;

public class ItemArmorElementBoots extends ArmorItem {  //盔甲属于什么元素
    protected int element = 1;

    public ItemArmorElementBoots() {
        super(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    public ItemArmorElementBoots(int element) {
        super(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Properties().tab(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        AbstractModelElementBoots model = null;
        switch (element) {
            case 1:
                model = new ArmorModelGoldBoots();
                break;
            case 2:
                model = new ArmorModelWoodBoots();
                break;
            case 3:
                model = new ArmorModelGoldBoots();
                break;
            case 4:
                model = new ArmorModelFireBoots();
                break;
            case 5:
                model = new ArmorModelEarthBoots();
                break;
        }

        model.leftLeg.visible = true;
        model.rightLeg.visible = true;
        model.leg_left.visible = true;
        model.leg_right.visible = true;


        model.young = _default.young;
        model.crouching = _default.crouching;
        model.riding = _default.riding;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;
        return (A) model;
    }


    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        switch (element) {
            case 1:
                return "vida:textures/model/armor/gold_element_armor.png";
            case 2:
                return "vida:textures/model/armor/wood_element_armor.png";
            case 3:
                return "vida:textures/model/armor/aqua_element_armor.png";
            case 4:
                return "vida:textures/model/armor/fire_element_armor.png";
            case 5:
                return "vida:textures/model/armor/earth_element_armor.png";
        }
        return null;
    }


}
