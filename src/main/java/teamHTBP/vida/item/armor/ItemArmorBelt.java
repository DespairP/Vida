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

import net.minecraft.item.Item.Properties;

/**
 * 图纸腰带
 */
public class ItemArmorBelt extends ArmorItem {
    public ItemArmorBelt() {
        super(new ArmorMaterialBelt(), EquipmentSlotType.LEGS, new Properties().tab(ItemGroupLoader.vidaItemGroup));
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
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

        armorModelBelt.young = _default.young;
        armorModelBelt.crouching = _default.crouching;
        armorModelBelt.riding = _default.riding;
        armorModelBelt.rightArmPose = _default.rightArmPose;
        armorModelBelt.leftArmPose = _default.leftArmPose;
        return (A) armorModelBelt;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        //OPEN THE GUI
        if (!worldIn.isClientSide && !playerIn.isShiftKeyDown() && stack.getItem() instanceof ItemArmorBelt) {
            NetworkHooks.openGui((ServerPlayerEntity) playerIn, new ItemBluePrintBeltProvider("bluePrintBelt", stack), (buffer) -> {
                buffer.writeItem(stack);
            });
            return ActionResult.success(stack);
        } else if (!worldIn.isClientSide && playerIn.isShiftKeyDown() && stack.getItem() instanceof ItemArmorBelt) {
            super.use(worldIn, playerIn, handIn);
        }
        return ActionResult.pass(stack);
    }


}

class ArmorMaterialBelt implements IArmorMaterial {

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotIn) {
        return 10000;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return 1;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
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
