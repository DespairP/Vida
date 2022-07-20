package teamHTBP.vida.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.itemGroup.ItemGroupLoader;
import teamHTBP.vida.modelRender.armormodel.ArmorModelSeasonApprentice;

import javax.annotation.Nullable;

public class ItemArmorDemo extends ArmorItem {

    public ItemArmorDemo(EquipmentSlot slot) {
        super(ArmorMaterial.DIAMOND, slot, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    @Nullable
    @Override
    public <A extends HumanoidModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default) {
        EquipmentSlot slot = getSlot();
        return (A)new ArmorModelSeasonApprentice(slot);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        return "vida:textures/model/armor/armor_demo.png";
    }




}
