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
import teamHTBP.vida.modelRender.armormodel.TestArmorModel;

import javax.annotation.Nullable;

public class TestHelmet extends ArmorItem {
    public TestHelmet() {
        super(ArmorMaterial.DIAMOND, EquipmentSlot.HEAD, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }


    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
        TestArmorModel model = new TestArmorModel();

        model.head.visible = armorSlot == EquipmentSlot.HEAD;
        model.hat.visible = armorSlot == EquipmentSlot.HEAD;
        model.body.visible = (armorSlot == EquipmentSlot.CHEST)
                || (armorSlot == EquipmentSlot.CHEST);
        model.rightArm.visible = armorSlot == EquipmentSlot.CHEST;
        model.leftArm.visible = armorSlot == EquipmentSlot.CHEST;
        model.rightLeg.visible = (armorSlot == EquipmentSlot.LEGS)
                || (armorSlot == EquipmentSlot.FEET);
        model.leftLeg.visible = (armorSlot == EquipmentSlot.LEGS)
                || (armorSlot == EquipmentSlot.FEET);


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
        return "vida:textures/model/armor/test_armor.png";
    }

}
