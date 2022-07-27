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
import teamHTBP.vida.client.model.armor.TestArmorModel;
import teamHTBP.vida.common.item.VidaItemGroupLoader;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class TestHelmet extends ArmorItem {
    public TestHelmet() {
        super(ArmorMaterials.DIAMOND, EquipmentSlot.HEAD, new Properties().tab(VidaItemGroupLoader.vidaItemGroup));
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @org.jetbrains.annotations.Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                TestArmorModel model = LayerRegistryHandler.create(TestArmorModel.class);

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


                return model;
            }
        });
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "vida:textures/model/armor/test_armor.png";
    }

}
