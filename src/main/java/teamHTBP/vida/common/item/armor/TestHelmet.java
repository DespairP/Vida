package teamHTBP.vida.common.item.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;
import teamHTBP.vida.client.modelRender.armormodel.TestArmorModel;

import javax.annotation.Nullable;

public class TestHelmet extends ArmorItem {
    public TestHelmet() {
        super(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }


    @OnlyIn(Dist.CLIENT)
    @Nullable
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        TestArmorModel model = new TestArmorModel();

        model.field_78116_c.showModel = armorSlot == EquipmentSlotType.HEAD;
        model.bipedHeadwear.showModel = armorSlot == EquipmentSlotType.HEAD;
        model.bipedBody.showModel = (armorSlot == EquipmentSlotType.CHEST)
                || (armorSlot == EquipmentSlotType.CHEST);
        model.bipedRightArm.showModel = armorSlot == EquipmentSlotType.CHEST;
        model.bipedLeftArm.showModel = armorSlot == EquipmentSlotType.CHEST;
        model.bipedRightLeg.showModel = (armorSlot == EquipmentSlotType.LEGS)
                || (armorSlot == EquipmentSlotType.FEET);
        model.bipedLeftLeg.showModel = (armorSlot == EquipmentSlotType.LEGS)
                || (armorSlot == EquipmentSlotType.FEET);


        model.isChild = _default.isChild;
        model.isSneak = _default.isSneak;
        model.isSitting = _default.isSitting;
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
