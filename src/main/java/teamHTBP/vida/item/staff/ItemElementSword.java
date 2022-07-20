package teamHTBP.vida.item.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.itemGroup.ItemGroupLoader;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.item.Item.Properties;

public class ItemElementSword extends SwordItem {
    public int element = 0;

    public ItemElementSword(int element) {
        super(new ElementItemTier(), 3, -2.4f, new Properties().tab(ItemGroupLoader.vidaItemGroup));
        this.element = element;
    }

    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        ItemStack stack = new ItemStack(this);
        stack.getOrCreateTag().putInt("swordEXP", 0);
        stack.getOrCreateTag().putInt("level", 1);
        stack.getOrCreateTag().putInt("ToolElement", element);
        if (this.allowdedIn(group)) {
            items.add(stack);
        }
    }

    public void onCraftedBy(ItemStack stack, World worldIn, PlayerEntity playerIn) {
        CompoundNBT nbt = stack.getOrCreateTag();
        nbt.putInt("swordExp", 0);
        nbt.putInt("level", 1);
        nbt.putInt("ToolElement", element);
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        CompoundNBT nbt = stack.getOrCreateTag();
        int level = nbt.getInt("level");
        int exp = nbt.getInt("swordEXP");
        ITextComponent iTextComponent = new TranslationTextComponent("desc.swordlevel.level", level).withStyle(TextFormatting.GRAY);
        tooltip.add(iTextComponent);
        tooltip.add(new StringTextComponent("(" + exp + "/" + (level * 200 + level * 13) + ")").withStyle(TextFormatting.AQUA));
        //System.out.println(iTextComponent.getFormattedText());
    }


}

