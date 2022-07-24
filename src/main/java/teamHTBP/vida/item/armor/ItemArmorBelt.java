package teamHTBP.vida.item.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.network.NetworkHooks;
import teamHTBP.vida.client.model.LayerRegistryHandler;
import teamHTBP.vida.client.model.armor.belt.ArmorModelBelt;
import teamHTBP.vida.creativetab.ItemGroupRegistry;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * 图纸腰带
 */
public class ItemArmorBelt extends ArmorItem {
    public ItemArmorBelt() {
        super(new ArmorMaterialBelt(), EquipmentSlot.LEGS, new Properties().tab(ItemGroupRegistry.vidaItemGroup));
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return false;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "vida:textures/model/armor/blueprintbelt.png";
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @org.jetbrains.annotations.Nullable
            @Override
            public HumanoidModel<?> getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                ArmorModelBelt armorModelBelt = LayerRegistryHandler.create(ArmorModelBelt.class);

                armorModelBelt.young = _default.young;
                armorModelBelt.crouching = _default.crouching;
                armorModelBelt.riding = _default.riding;
                armorModelBelt.rightArmPose = _default.rightArmPose;
                armorModelBelt.leftArmPose = _default.leftArmPose;
                return armorModelBelt;
            }
        });
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        //OPEN THE GUI
        if (!worldIn.isClientSide && !playerIn.isShiftKeyDown() && stack.getItem() instanceof ItemArmorBelt) {
            NetworkHooks.openGui((ServerPlayer) playerIn, new ItemBluePrintBeltProvider("bluePrintBelt", stack), (buffer) -> {
                buffer.writeItem(stack);
            });
            return InteractionResultHolder.success(stack);
        } else if (!worldIn.isClientSide && playerIn.isShiftKeyDown() && stack.getItem() instanceof ItemArmorBelt) {
            super.use(worldIn, playerIn, handIn);
        }
        return InteractionResultHolder.pass(stack);
    }

    static class ArmorMaterialBelt implements ArmorMaterial {

        @Override
        public int getDurabilityForSlot(EquipmentSlot slotIn) {
            return 10000;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slotIn) {
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
}
