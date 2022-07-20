package teamHTBP.vida.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;
import teamHTBP.vida.itemGroup.ItemGroupLoader;
import teamHTBP.vida.modelRender.armormodel.ArmorModelSeasonApprentice;

import java.util.function.Consumer;

public class ItemArmorDemo extends ArmorItem {

    public ItemArmorDemo(EquipmentSlot slot) {
        super(ArmorMaterials.DIAMOND, slot, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @org.jetbrains.annotations.Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                EquipmentSlot slot = getSlot();
                return new ArmorModelSeasonApprentice(slot);
            }
        });
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
    {
        return "vida:textures/model/armor/armor_demo.png";
    }




}
