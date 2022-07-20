package teamHTBP.vida.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.base.ModBaseBlockEntity;
import teamHTBP.vida.item.ItemLoader;

import java.util.Collections;
import java.util.List;

public class TileEntityGemShower extends ModBaseBlockEntity {
    //展示的宝石
    public ItemStack gemItem = ItemStack.EMPTY;

    public TileEntityGemShower(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityGemShower.get(), pWorldPosition, pBlockState);
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
            if (putGemItem == ItemLoader.ELEMENTGEM_FIRE.get() || putGemItem == ItemLoader.ELEMENTGEM_GOLD.get() ||
                    putGemItem == ItemLoader.ELEMENTGEM_WOOD.get() || putGemItem == ItemLoader.ELEMENTGEM_AQUA.get() ||
                    putGemItem == ItemLoader.ELEMENTGEM_EARTH.get())
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
