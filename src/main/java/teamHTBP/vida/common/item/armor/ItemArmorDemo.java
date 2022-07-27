package teamHTBP.vida.common.item.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;
import teamHTBP.vida.client.modelRender.armormodel.ArmorModelSeasonApprentice;

import javax.annotation.Nullable;

public class ItemArmorDemo extends ArmorItem {

    public ItemArmorDemo(EquipmentSlotType slot) {
        super(ArmorMaterial.DIAMOND, slot, new Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP));
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        EquipmentSlotType slot = getEquipmentSlot();
        return (A)new ArmorModelSeasonApprentice(slot);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        return "vida:textures/model/armor/armor_demo.png";
    }




}
