package teamHTBP.vida.TileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import teamHTBP.vida.TileEntity.SlotNumberArray.OreReactionMachineArray;
import teamHTBP.vida.capability.VidaCapabilities;
import teamHTBP.vida.capability.energyCapability.IElementEnergyCapability;
import teamHTBP.vida.gui.GUI.ContainerOreReactionMachine;
import teamHTBP.vida.helper.elementHelper.EnumElements;

import javax.annotation.Nullable;
import java.util.Optional;

public class TileEntityOreReationMachine extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    //最大的cookTime
    public final int MAX_COOKTIME = 200;
    //最大烧炼值
    public final int MAX_BURNTIME = 20000;
    //最大金元素值
    public final int MAX_GOLDENERGY = 1000;
    //燃料格子
    public Inventory fuel = new Inventory(1);
    //burnTime 和 cookTime
    public OreReactionMachineArray array = new OreReactionMachineArray();
    //矿石的格子
    protected Inventory smeltSlot = new Inventory(4);
    //烧制矿石的格子
    protected Inventory completeSlot = new Inventory(1);
    //正在烧制的物品
    //protected ItemStack smeltingItemStack = ItemStack.EMPTY;
    //金元素值
    protected int goldEnergy = 0;
    IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn = IRecipeType.SMELTING;
    //输出的物品
    private ItemStack outPutItemStack = ItemStack.EMPTY;

    public TileEntityOreReationMachine() {
        super(TileEntityLoader.TileEntityOreReationMachine.get());
    }

    public Inventory getSmeltSlotInv() {
        return this.smeltSlot;
    }

    public Inventory getFuelInv() {
        return this.fuel;
    }

    public Inventory getCompleteInv() {
        return this.completeSlot;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        this.smeltSlot.setItem(0, ItemStack.of(compound.getCompound("smeltslot0")));
        this.smeltSlot.setItem(1, ItemStack.of(compound.getCompound("smeltslot1")));
        this.smeltSlot.setItem(2, ItemStack.of(compound.getCompound("smeltslot2")));
        this.smeltSlot.setItem(3, ItemStack.of(compound.getCompound("smeltslot3")));
        this.fuel.setItem(0, ItemStack.of(compound.getCompound("fuel")));
        this.completeSlot.setItem(0, ItemStack.of(compound.getCompound("completeSlot")));
        array.set(0, compound.getInt("burnTime1"));
        array.set(1, compound.getInt("cookTime"));
        goldEnergy = compound.getInt("goldEnergy");
        super.load(state, compound);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        ItemStack smeltSlot0 = this.smeltSlot.getItem(0).copy();
        ItemStack smeltSlot1 = this.smeltSlot.getItem(1).copy();
        ItemStack smeltSlot2 = this.smeltSlot.getItem(2).copy();
        ItemStack smeltSlot3 = this.smeltSlot.getItem(3).copy();
        compound.put("smeltslot0", smeltSlot0.serializeNBT());
        compound.put("smeltslot1", smeltSlot1.serializeNBT());
        compound.put("smeltslot2", smeltSlot2.serializeNBT());
        compound.put("smeltslot3", smeltSlot3.serializeNBT());
        ItemStack fuel = this.fuel.getItem(0);
        compound.put("fuel", fuel.serializeNBT());
        ItemStack completeSlot = this.completeSlot.getItem(0);
        compound.put("completeSlot", completeSlot.serializeNBT());
        compound.putInt("burnTime1", array.get(0));
        compound.putInt("cookTime", array.get(1));
        compound.putInt("goldEnergy", goldEnergy);
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
        this.smeltSlot.setItem(0, ItemStack.of(tag.getCompound("smeltslot0")));
        this.smeltSlot.setItem(1, ItemStack.of(tag.getCompound("smeltslot1")));
        this.smeltSlot.setItem(2, ItemStack.of(tag.getCompound("smeltslot2")));
        this.smeltSlot.setItem(3, ItemStack.of(tag.getCompound("smeltslot3")));
        this.fuel.setItem(0, ItemStack.of(tag.getCompound("fuel")));
        this.completeSlot.setItem(0, ItemStack.of(tag.getCompound("completeSlot")));
        array.set(0, tag.getInt("burnTime1"));
        array.set(1, tag.getInt("cookTime"));
        goldEnergy = tag.getInt("goldEnergy");
        super.load(state, tag);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("OreReactionMachine");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new ContainerOreReactionMachine(id, inv, this.worldPosition, this.level, this.array);
    }

    public Inventory getSmeltSlot() {
        return smeltSlot;
    }

    public Inventory getCompleteSlot() {
        return completeSlot;
    }

    //得到array的烧炼值
    protected int getArrayBurnTime() {
        return this.array.get(0);
    }

    //得到烧炼值
    protected int getArrayCookTime() {
        return this.array.get(1);
    }

    //熔炉是否在烧制
    public boolean isBurning() {
        return this.array.get(0) > 0;
    }

    //是否在烧炼
    public boolean isCooking() {
        return this.array.get(1) > 0;
    }

    //重置cook
    //是否在烧炼
    public void resetCooking() {
        this.array.set(1, 0);
    }


    //得到燃烧值
    protected int getFuelBurnTime() {
        if (fuel.getItem(0) == ItemStack.EMPTY || fuel.getItem(0).isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem(0).getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel.getItem(0));
        }
    }

    //确定是否是燃料
    protected boolean isFuel() {
        if (fuel.getItem(0) == ItemStack.EMPTY || fuel.getItem(0).isEmpty()) {
            return false;
        } else
            return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel.getItem(0)) > 0;
    }

    //烧炼新燃料,返回是否有新燃料可以烧
    protected boolean burnNewFuel() {
        //如果不在燃烧且有燃料时
        if (!isBurning() && isFuel()) {
            //设置燃烧值
            array.set(0, this.getFuelBurnTime());
            //设置
            if (this.fuel.getItem(0).getItem() != Items.LAVA_BUCKET)
                this.fuel.getItem(0).shrink(1);
            else
                this.fuel.setItem(0, new ItemStack(Items.BUCKET, 1));
            return true;
        } else
            return false;
    }

    //继续燃烧
    protected void burn() {
        //如果正在燃烧
        if (isBurning()) {
            //降低Array中的burnTime
            this.array.set(0, this.getArrayBurnTime() - 1);
            if (isCooking()) {
                this.array.set(1, getArrayCookTime() + 1);
            }
        }
    }

    //得到合成表
    protected Optional<AbstractCookingRecipe> getRecipe() {
        return level.getRecipeManager().getRecipeFor((IRecipeType<AbstractCookingRecipe>) recipeTypeIn, this.smeltSlot, level);
    }

    protected Optional<ItemStack> getOutPutItemStack(ItemStack stack) {
        Inventory inv = new Inventory(stack);
        return level.getRecipeManager().getRecipeFor(recipeTypeIn, inv, level).map(recipe -> recipe.assemble(inv));
    }

    protected boolean canSmelt() {
        ItemStack stack = getOutPutItemStack(this.smeltSlot.getItem(0)).orElse(ItemStack.EMPTY);
        if (stack == ItemStack.EMPTY || stack.isEmpty() || this.outPutItemStack != ItemStack.EMPTY || !this.outPutItemStack.isEmpty()) {
            return false;
        } else {
            ItemStack stack1 = completeSlot.getItem(0);
            if (stack1 == ItemStack.EMPTY || stack1.isEmpty())
                return true;
            else if (stack.getItem() == stack1.getItem())
                return stack.getCount() + stack1.getCount() <= stack.getMaxStackSize();
            else
                return false;
        }
    }

    //设置烧炼
    protected void setSmelt(boolean canSmelt) {
        if (canSmelt && isBurning()) {
            this.outPutItemStack = this.smeltSlot.getItem(0).copy();
            this.outPutItemStack.setCount(1);
            this.smeltSlot.getItem(0).shrink(1);
            this.array.set(1, 1);
        } else {
            return;
        }
    }

    protected void smelt() {
        ItemStack itemStack = getOutPutItemStack(this.outPutItemStack).orElse(ItemStack.EMPTY);
        if (this.completeSlot.getItem(0).isEmpty()) {
            itemStack.setCount(2);
            this.completeSlot.setItem(0, itemStack.copy());
        } else {
            this.completeSlot.getItem(0).grow(2);
        }
        this.outPutItemStack = ItemStack.EMPTY;
    }

    public boolean isItemIn() {
        return !(this.smeltSlot.getItem(0) == ItemStack.EMPTY || this.smeltSlot.getItem(0).isEmpty());
    }

    public boolean hasOutPutItem() {
        return !(this.outPutItemStack == ItemStack.EMPTY || this.outPutItemStack.isEmpty());
    }


    public int getGoldEnergy() {
        return this.goldEnergy;
    }

    @Override
    public void tick() {
        boolean flag = false;
        if (this.isBurning() && isCooking()) {
            this.burn();
            this.goldEnergy += 1;
            if (this.goldEnergy >= MAX_GOLDENERGY) this.goldEnergy = MAX_GOLDENERGY;
        }
        if (!level.isClientSide) {
            //如果不在烧炼东西且有东西可以烧的时候,且在燃烧的时候
            if (isItemIn() && !isCooking() && canSmelt() && isBurning()) {
                this.setSmelt(true);
                flag = true;
            }
            //如果cook值>MAXCook值，产出东西
            if (isBurning() && this.getArrayCookTime() >= this.MAX_COOKTIME) {
                resetCooking();
                smelt();
                flag = true;
            }
            //如果熔炉不在燃烧,添加燃料，否则cook值变为0
            if (!isBurning()) {
                if (!burnNewFuel() && !hasOutPutItem()) {
                    this.resetCooking();
                    flag = true;
                }
            }
            //熔炉检测机制
            if (!this.isBurning() && this.isCooking() && this.getArrayCookTime() != 1 && hasOutPutItem()) {
                this.array.set(1, 1);
                flag = true;
            }
            if (this.goldEnergy >= MAX_GOLDENERGY && this.level.getBlockEntity(worldPosition.above()) != null) {
                LazyOptional<IElementEnergyCapability> cap = this.level.getBlockEntity(this.worldPosition.above()).getCapability(VidaCapabilities.elementEnergy_Capability);
                cap.ifPresent((T) -> {
                    T.receiveEnergy(150, false, EnumElements.GOLD);
                    this.goldEnergy = 0;
                });
                flag = true;
            }
        }
        if (flag) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            this.setChanged();
        }
        // resetCooking();
    }


}
