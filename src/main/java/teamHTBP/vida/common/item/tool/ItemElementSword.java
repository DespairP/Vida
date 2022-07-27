package teamHTBP.vida.common.item.tool;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.api.common.item.tool.IElementLevelTools;
import teamHTBP.vida.common.item.VidaItemGroupLoader;
import teamHTBP.vida.api.core.element.IElement;

import javax.annotation.Nullable;
import java.util.List;

public class ItemElementSword extends SwordItem implements IElementLevelTools {
    public IElement element;

    public ItemElementSword(IElement element) {
        super(new ItemElementPickaxe.ElementItemTier(), 3, -2.4f, new Properties().tab(VidaItemGroupLoader.vidaItemGroup));
        this.element = element;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        ItemStack stack = new ItemStack(this);
        stack.getOrCreateTag().putInt("swordEXP", 0);
        stack.getOrCreateTag().putInt("level", 1);
        if (this.allowdedIn(group)) {
            items.add(stack);
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn) {
        CompoundTag nbt = stack.getOrCreateTag();
        nbt.putInt("swordExp", 0);
        nbt.putInt("level", 1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        CompoundTag nbt = stack.getOrCreateTag();
        int level = nbt.getInt("level");
        int exp = nbt.getInt("swordEXP");
        Component iTextComponent = new TranslatableComponent("desc.swordlevel.level", level).withStyle(ChatFormatting.GRAY);
        tooltip.add(iTextComponent);
        tooltip.add(new TextComponent("(" + exp + "/" + (level * 200 + level * 13) + ")").withStyle(ChatFormatting.AQUA));
        //System.out.println(iTextComponent.getFormattedText());
    }

    @Override
    public IElement getElement() {
        return element;
    }

    @Override
    public int getCurrentLevel(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getInt("level");
    }

    @Override
    public double getNextLevelRequiredXP(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getDouble("level") * 500.0f;
    }

    @Override
    public double getCurrentLevelXP(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTag();
        return nbt.getDouble("swordEXP");
    }
}

