package teamHTBP.vida.common.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.common.blockentity.base.VidaBaseBlockEntity;
import teamHTBP.vida.common.item.VidaItemLoader;

import java.util.Collections;
import java.util.List;

public class GemShowerBlockEntity extends VidaBaseBlockEntity {
    //展示的宝石
    public ItemStack gemItem = ItemStack.EMPTY;

    public GemShowerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(VidaBlockEntityLoader.TileEntityGemShower.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        if (pTag.contains("gemItem"))
            gemItem = ItemStack.of(pTag.getCompound("gemItem"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!gemItem.isEmpty())
            pTag.put("gemItem", gemItem.serializeNBT());
    }


    public boolean setGem(ItemStack itemStack) {
        if (!gemItem.isEmpty())
            return false;
        else {
            Item putGemItem = itemStack.getItem();
            if (putGemItem == VidaItemLoader.ELEMENTGEM_FIRE.get() || putGemItem == VidaItemLoader.ELEMENTGEM_GOLD.get() ||
                    putGemItem == VidaItemLoader.ELEMENTGEM_WOOD.get() || putGemItem == VidaItemLoader.ELEMENTGEM_AQUA.get() ||
                    putGemItem == VidaItemLoader.ELEMENTGEM_EARTH.get())
                gemItem = new ItemStack(itemStack.getItem(), 1);
            return true;
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public List<ItemStack> getDrops() {
        return Collections.singletonList(gemItem);
    }
}
