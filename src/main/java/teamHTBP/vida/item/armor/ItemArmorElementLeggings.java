package teamHTBP.vida.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.itemGroup.ItemGroupLoader;
import teamHTBP.vida.modelRender.armormodel.*;

import javax.annotation.Nullable;

public class ItemArmorElementLeggings extends ArmorItem {
    public int element = 1;

    public ItemArmorElementLeggings() {
        super(ArmorMaterial.DIAMOND, EquipmentSlot.LEGS, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    public ItemArmorElementLeggings(int element) {
        super(ArmorMaterial.DIAMOND, EquipmentSlot.LEGS, new Properties().tab(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
        AbstractModelElementLeggings model = null;
        switch (element) {
            case 1:
                model = new ArmorModelGoldLeggings();
                break;
            case 2:
                model = new ArmorModelWoodLeggings();
                break;
            case 3:
                model = new ArmorModelAquaLeggings();
                break;
            case 4:
                model = new ArmorModelFireLeggings();
                break;
            case 5:
                model = new ArmorModelEarthLeggings();
                break;
        }

        model.young = _default.young;
        model.crouching = _default.crouching;
        model.riding = _default.riding;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;
        return (A) model;
    }


    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
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
