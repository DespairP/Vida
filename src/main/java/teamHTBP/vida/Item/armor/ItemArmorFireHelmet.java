package teamHTBP.vida.Item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import teamHTBP.vida.ItemGroup.ItemGroupLoader;

import javax.annotation.Nullable;

public class ItemArmorFireHelmet extends ArmorItem {
    public ItemArmorFireHelmet() {
        super(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Properties().group(ItemGroupLoader.vidaItemGroup));
    }



    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
    {
        return "vida:textures/model/armor/helmet_element_fire.png";
    }


}
