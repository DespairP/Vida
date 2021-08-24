package teamHTBP.vida.item.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import teamHTBP.vida.itemGroup.ItemGroupLoader;
import teamHTBP.vida.modelRender.armormodel.ArmorModelBelt;

import javax.annotation.Nullable;

/**
 * 图纸腰带
 */
public class ItemArmorBelt extends ArmorItem {
    public ItemArmorBelt() {
        super(new ArmorMaterialBelt(), EquipmentSlotType.LEGS, new Properties().group(ItemGroupLoader.vidaItemGroup));
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return "vida:textures/model/armor/blueprintbelt.png";
    }

    @OnlyIn(Dist.CLIENT)
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        ArmorModelBelt armorModelBelt = new ArmorModelBelt();

        armorModelBelt.isChild = _default.isChild;
        armorModelBelt.isSneak = _default.isSneak;
        armorModelBelt.isSitting = _default.isSitting;
        armorModelBelt.rightArmPose = _default.rightArmPose;
        armorModelBelt.leftArmPose = _default.leftArmPose;
        return (A) armorModelBelt;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        //OPEN THE GUI
        if (!worldIn.isRemote && !playerIn.isSneaking() && stack.getItem() instanceof ItemArmorBelt) {
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, new ItemBluePrintBeltProvider("bluePrintBelt", stack), (buffer) -> {
                buffer.writeItemStack(stack);
            });
            return ActionResult.resultSuccess(stack);
        } else if (!worldIn.isRemote && playerIn.isSneaking() && stack.getItem() instanceof ItemArmorBelt) {
            super.onItemRightClick(worldIn, playerIn, handIn);
        }
        return ActionResult.resultPass(stack);
    }


}

class ArmorMaterialBelt implements IArmorMaterial {

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return 10000;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return null;
    }

    @Override
    public String getName() {
        return "belt";
    }

    @Override
    public float getToughness() {
        return 0;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
