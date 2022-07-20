package teamHTBP.vida.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.Vida;
import teamHTBP.vida.gui.GUI.ContainerInjectTable;

import javax.annotation.Nullable;

public class TileEntityInjectTable extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    //将要被注魔的剑
    ItemStack swordStack = ItemStack.EMPTY;

    public TileEntityInjectTable() {
        super(TileEntityLoader.TileEntityInjectTable.get());
    }

    @Override
    public void load(BlockState blockState, CompoundNBT compound) {
        swordStack = ItemStack.of(compound.getCompound("swordItem"));
        super.load(blockState, compound);
    }

    public CompoundNBT save(CompoundNBT compound) {
        compound.put("swordItem", swordStack.serializeNBT());
        return super.save(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 1, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(level.getBlockState(worldPosition), pkt.getTag());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        swordStack = ItemStack.of(tag.getCompound("swordItem"));
        super.handleUpdateTag(state, tag);
        super.load(state, tag);
    }

    public boolean setSwordItem(ItemStack itemStack) {
        try {
            if (itemStack.getItem() instanceof SwordItem || itemStack.getItem() instanceof ToolItem) {
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
        CompoundNBT nbt = swordStack.getOrCreateTag();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("OreReactionMachine");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new ContainerInjectTable(p_createMenu_1_, getSwordStack(), worldPosition, level);
    }
}
