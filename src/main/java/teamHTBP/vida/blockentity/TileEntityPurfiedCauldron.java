package teamHTBP.vida.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import teamHTBP.vida.blockentity.base.ModBaseBlockEntity;
import teamHTBP.vida.entity.EntityFaintLight;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.helper.elementHelper.ElementHelper;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.helper.elementHelper.IElement;
import teamHTBP.vida.item.potion.ItemCreativeElementPotion;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TileEntityPurfiedCauldron extends ModBaseBlockEntity {
    //满提炼值
    public final int MAX_CONTAINER = 30000;
    //五元素数值
    //public final int ELEMENT_GOLD = 1;
    //public final int ELEMENT_WOOD = 2;
    //public final int ELEMENT_AQUA = 3;
    //public final int ELEMENT_FIRE = 4;
    //public final int ELEMENT_EARTH = 5;
    //提炼值
    public int container = 0;
    //正在提炼的缓冲值
    public int containing = 0;
    //提炼的物品
    public ItemStack meltItem = ItemStack.EMPTY;
    //是否有水
    public boolean isWater = false;
    //是否底下有火
    public boolean isFire = false;
    //加速熔炼速度,speed * 10 =actualSpeed
    public int speed = 1;
    //锅内的元素
    public IElement element = EnumElements.NONE;
    //
    ElementHelper helper = new ElementHelper();

    public TileEntityPurfiedCauldron(BlockPos pWorldPosition, BlockState pBlockState) {
        super(TileEntityLoader.TileEntityPurfiedCauldron.get(), pWorldPosition, pBlockState);
    }

    /**
     * 设置提炼物品
     *
     * @return 是否有其他提炼的物品正在提炼
     * 只有在有水燃烧和火在锅中的情况下才接收物品
     **/
    public boolean setMeltItem(ItemStack meltItem) {
        if (this.meltItem.isEmpty() && this.isWater && this.isFire && ElementHelper.getContainingNum(meltItem) > 0 && (element == EnumElements.NONE || element == this.getContainingElement(meltItem))) {
            this.meltItem = meltItem;
            return true;
        }
        return false;
    }

    /**
     * 设置物品融入速度
     * 初始速度：1
     */
    public void setMeltSpeed() {
        this.speed = this.meltItem.getItem() instanceof ItemCreativeElementPotion ? 100 : 1;
    }

    @Override
    public void load(CompoundTag compound) {
        containing = compound.getInt("containing");
        container = compound.getInt("container");
        isWater = compound.getBoolean("isWater");
        isFire = compound.getBoolean("isFire");
        element = ElementHelper.read(compound);
        if (compound.contains("meltItem"))
            meltItem = ItemStack.of(compound.getCompound("meltItem"));

        super.load(compound);
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        compound.putInt("containing", containing);
        compound.putInt("container", container);
        compound.putBoolean("isWater", isWater);
        compound.putBoolean("isFire", isFire);
        ElementHelper.write(compound, element);
        if (!meltItem.isEmpty())
            compound.put("meltItem", meltItem.serializeNBT());
        super.saveAdditional(compound);
    }

    @Override
    public List<ItemStack> getDrops() {
        return Collections.singletonList(meltItem);
    }

    //内部逻辑实现
    @Override
    public void tick() {
        if (!level.isClientSide) {
            //如果缓冲值>0,先消耗缓冲值
            if (this.isWater && this.isFire) {
                if (this.containing > 0) {
                    consumeContaining();
                    if (containing == 0) {
                        meltItem = ItemStack.EMPTY;
                    }
                } else {
                    getContaining();
                }
                //如果已满出，生成微光
                generateFaintLight();
                level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
            if (containing == 0) {
                meltItem = ItemStack.EMPTY;
            }
        }
    }


    //消耗containing值
    public void consumeContaining() {
        if (containing < speed * 2) {
            container += containing;
            containing = 0;
        } else {
            container += speed * 2;
            containing -= speed * 2;
        }
    }

    public void getContaining() {
        if (meltItem != ItemStack.EMPTY) {
            //如果元素为空的话，先设置元素
            if (element == EnumElements.NONE) {
                element = getContainingElement(meltItem);
            }
            //
            containing = new Random().nextInt(getContainingNum(meltItem)) * 3 + 1;
        }
    }


    //获得当前Containing
    public int getContainingNum(ItemStack itemStack) {
        return ElementHelper.getContainingNum(itemStack);
    }

    //获得投入物品的元素
    public IElement getContainingElement(ItemStack itemStack) {
        return ElementHelper.getContainingElement(itemStack);
    }

    //生成微光
    public void generateFaintLight() {
        if (container > MAX_CONTAINER - 1) {
            EntityFaintLight entityFaintLight = new EntityFaintLight(EntityLoader.faintLight.get(), level, element);
            entityFaintLight.setPos(worldPosition.getX(), worldPosition.above().getY(), worldPosition.getZ() + 0.5);
            entityFaintLight.setFaintLightType(element);
            this.level.addFreshEntity(entityFaintLight);
            clear();
        }
    }

    //清空
    public void clear() {
        this.container = 0;
        this.containing = 0;
        this.element = null;
        this.isWater = false;
        this.isFire = level.getBlockState(worldPosition.below()).getBlock() == Blocks.FIRE || level.getBlockState(worldPosition.below()).getBlock() == Blocks.LAVA;
        this.meltItem = ItemStack.EMPTY;
        setChanged();
    }
}
