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
import teamHTBP.vida.modelRender.armormodel.TestArmorModel;

import javax.annotation.Nullable;

import net.minecraft.item.Item.Properties;

public class TestHelmet extends ArmorItem {
    public TestHelmet() {
        super(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }


    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        TestArmorModel model = new TestArmorModel();

        model.head.visible = armorSlot == EquipmentSlotType.HEAD;
        model.hat.visible = armorSlot == EquipmentSlotType.HEAD;
        model.body.visible = (armorSlot == EquipmentSlotType.CHEST)
                || (armorSlot == EquipmentSlotType.CHEST);
        model.rightArm.visible = armorSlot == EquipmentSlotType.CHEST;
        model.leftArm.visible = armorSlot == EquipmentSlotType.CHEST;
        model.rightLeg.visible = (armorSlot == EquipmentSlotType.LEGS)
                || (armorSlot == EquipmentSlotType.FEET);
        model.leftLeg.visible = (armorSlot == EquipmentSlotType.LEGS)
                || (armorSlot == EquipmentSlotType.FEET);


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
        return "vida:textures/model/armor/test_armor.png";
    }

}
