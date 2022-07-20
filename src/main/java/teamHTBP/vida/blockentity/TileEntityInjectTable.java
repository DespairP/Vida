package teamHTBP.vida.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.Vida;
import teamHTBP.vida.blockentity.base.ModBaseMenuBlockEntity;
import teamHTBP.vida.gui.menu.ContainerInjectTable;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class TileEntityInjectTable extends ModBaseMenuBlockEntity {
    //将要被注魔的剑
    ItemStack swordStack = ItemStack.EMPTY;

    public TileEntityInjectTable(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityInjectTable.get(), pWorldPosition, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        swordStack = ItemStack.of(pTag.getCompound("swordItem"));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("swordItem", swordStack.serializeNBT());
    }

    @Override
    public List<ItemStack> getDrops() {
        return Collections.singletonList(swordStack);
    }

    public boolean setSwordItem(ItemStack itemStack) {
        try {
            if (itemStack.getItem() instanceof TieredItem) {
                if (this.swordStack == ItemStack.EMPTY || this.swordStack.isEmpty()) {
                    swordStack = itemStack.copy();
                    return true;
                }
            }
        } catch (NullPointerException ex) {
            Vida.LOGGER.error("In TileEntity Inject Table:NULL POINTER EXCEPTION");
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public ItemStack getSwordStack() {
        return this.swordStack;
    }

    public ItemStack getSwordStackToPlayer() {
        ItemStack itemStack = this.swordStack.copy();
        this.swordStack = ItemStack.EMPTY;
        return itemStack;
    }


    public boolean hasSwordItem() {
        return this.swordStack != ItemStack.EMPTY && !this.swordStack.isEmpty();
    }

    @Override
    public void tick() {
        //
        CompoundTag nbt = swordStack.getOrCreateTag();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Override
    public TextComponent getDisplayName() {
        return new TextComponent("OreReactionMachine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_createMenu_1_, Inventory p_createMenu_2_, Player p_createMenu_3_) {
        return new ContainerInjectTable(p_createMenu_1_, p_createMenu_2_, getSwordStack(), worldPosition, level);
    }
}
