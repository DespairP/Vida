package teamHTBP.vida.TileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.TileEntity.SlotNumberArray.OreReactionMachineArray;
import teamHTBP.vida.gui.GUI.ContainerOreReactionMachine;
import teamHTBP.vida.gui.GUI.ContainerPrismTable;
import teamHTBP.vida.recipe.OreReactionMachineRecipe;
import teamHTBP.vida.recipe.RecipeLoader;

import javax.annotation.Nullable;
import java.util.Optional;

public class TileEntityOreReationMachine extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    //矿石的格子
    protected Inventory smeltSlot = new Inventory(4);
    //烧制矿石的格子
    protected Inventory completeSlot = new Inventory(1);
    //燃料格子
    public Inventory fuel = new Inventory(1);
    //burnTime 和 cookTime
    public OreReactionMachineArray array = new OreReactionMachineArray();
    //最大的cookTime
    public final int MAX_COOKTIME = 200;
    //最大烧炼值
    public final int MAX_BURNTIME = 20000;
    //正在烧制的物品
    //protected ItemStack smeltingItemStack = ItemStack.EMPTY;
    //输出的物品
    private ItemStack outPutItemStack = ItemStack.EMPTY;

    public TileEntityOreReationMachine() {
        super(TileEntityLoader.TileEntityOreReationMachine.get());
    }

    @Override
    public void read(CompoundNBT compound) {
        this.smeltSlot.setInventorySlotContents(0,ItemStack.read(compound.getCompound("smeltslot0")));
        this.smeltSlot.setInventorySlotContents(1,ItemStack.read(compound.getCompound("smeltslot1")));
        this.smeltSlot.setInventorySlotContents(2,ItemStack.read(compound.getCompound("smeltslot2")));
        this.smeltSlot.setInventorySlotContents(3,ItemStack.read(compound.getCompound("smeltslot3")));
        this.fuel.setInventorySlotContents(0,ItemStack.read(compound.getCompound("fuel")));
        this.completeSlot.setInventorySlotContents(0, ItemStack.read(compound.getCompound("completeSlot")));
        array.set(0, compound.getInt("burnTime"));
        array.set(1, compound.getInt("cookTime"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStack smeltSlot0 = this.smeltSlot.getStackInSlot(0).copy();
        ItemStack smeltSlot1 = this.smeltSlot.getStackInSlot(1).copy();
        ItemStack smeltSlot2 = this.smeltSlot.getStackInSlot(2).copy();
        ItemStack smeltSlot3 = this.smeltSlot.getStackInSlot(3).copy();
        compound.put("smeltslot0", smeltSlot0.serializeNBT());
        compound.put("smeltslot1", smeltSlot1.serializeNBT());
        compound.put("smeltslot2", smeltSlot2.serializeNBT());
        compound.put("smeltslot3", smeltSlot3.serializeNBT());
        ItemStack fuel = this.fuel.getStackInSlot(0);
        compound.put("fuel", fuel.serializeNBT());
        ItemStack completeSlot = this.completeSlot.getStackInSlot(0);
        compound.put("completeSlot", completeSlot.serializeNBT());
        compound.putInt("burnTime", array.get(0));
        compound.putInt("cookTime", array.get(1));
        return super.write(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos,1,this.getUpdateTag());
    }


    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());

    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        super.onDataPacket(net,pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        this.smeltSlot.setInventorySlotContents(0,ItemStack.read(tag.getCompound("smeltslot0")));
        this.smeltSlot.setInventorySlotContents(1,ItemStack.read(tag.getCompound("smeltslot1")));
        this.smeltSlot.setInventorySlotContents(2,ItemStack.read(tag.getCompound("smeltslot2")));
        this.smeltSlot.setInventorySlotContents(3,ItemStack.read(tag.getCompound("smeltslot3")));
        this.fuel.setInventorySlotContents(0,ItemStack.read(tag.getCompound("fuel")));
        this.completeSlot.setInventorySlotContents(0, ItemStack.read(tag.getCompound("completeSlot")));
        array.set(0, tag.getInt("burnTime"));
        array.set(1, tag.getInt("cookTime"));
        super.read(tag);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("OreReactionMachine");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new ContainerOreReactionMachine(id,inv,this.pos,this.world,this.array);
    }

    public Inventory getSmeltSlot() {
        return smeltSlot;
    }

    public Inventory getCompleteSlot() {
        return completeSlot;
    }

    //得到array的烧炼值
    protected int getArrayBurnTime(){
        return this.array.get(0);
    }

    //得到烧炼值
    protected int getArrayCookTime(){
        return this.array.get(1);
    }

    //熔炉是否在烧制
    public boolean isBurning(){
        return this.array.get(0) > 0;
    }

    //是否在烧炼
    public boolean isCooking(){
        return this.array.get(1) > 0;
    }



    //得到燃烧值
    protected int getFuelBurnTime(){
        if(fuel.getStackInSlot(0) == ItemStack.EMPTY ||fuel.getStackInSlot(0).isEmpty()){
            return 0;
        }else {
            Item item = fuel.getStackInSlot(0).getItem();
          return  net.minecraftforge.common.ForgeHooks.getBurnTime(fuel.getStackInSlot(0));
        }
    }

    //确定是否是燃料
    protected boolean isFuel(){
        if(fuel.getStackInSlot(0) == ItemStack.EMPTY ||fuel.getStackInSlot(0).isEmpty()){
            return false;
        }else
            return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel.getStackInSlot(0)) > 0;
    }

    //烧炼新燃料
    protected void burnNewFuel(){
        //如果不在燃烧且有燃料时
        if(!isBurning() && isFuel()){
            //设置燃烧值
            array.set(0, this.getFuelBurnTime());
            //设置
            this.fuel.getStackInSlot(0).shrink(1);
        }else
          return;
    }

    //继续燃烧
    protected void burn(){
        //如果正在燃烧
        if(isBurning()){
            //降低Array中的burnTime
            this.array.set(0,this.getArrayBurnTime() - 1);
            if(isCooking()){
                this.array.set(1, getArrayCookTime() + 1);
            }
        }
    }

    //得到合成表
    protected Optional<OreReactionMachineRecipe> getRecipe(){
        return world.getRecipeManager().getRecipe(OreReactionMachineRecipe.RECIPE_TYPE , this.smeltSlot, world);
    }

    protected boolean canSmelt(){
        ItemStack stack = getRecipe().get().getRecipeOutput();
        if(stack == ItemStack.EMPTY || stack.isEmpty()){
            return false;
        }else{
            ItemStack stack1 = completeSlot.getStackInSlot(0);
            if(stack1 == ItemStack.EMPTY || stack1.isEmpty())
                return true;
            else if(stack.getItem() == stack1.getItem())
                return stack.getCount() + stack1.getCount() <= stack.getMaxStackSize();
            else
                return false;
        }
    }


    @Override
    public void tick() {
        boolean flag = false;
        if(this.isBurning()){
            this.burn();
        }
        if(!world.isRemote){
             if(this.smeltSlot.getStackInSlot(0) != ItemStack.EMPTY){
                 System.out.println(getResult(this.smeltSlot.getStackInSlot(0)).orElse(ItemStack.EMPTY));
             }
        }
        if(flag){
            world.notifyBlockUpdate(pos,getBlockState(),getBlockState(),3);
        }

    }
    private Optional<OreReactionMachineRecipe> getRecipe(final IInventory inventory) {
        return world.getRecipeManager().getRecipe(OreReactionMachineRecipe.RECIPE_TYPE , inventory, world);
    }


    private Optional<ItemStack> getResult(final ItemStack input) {
        final Inventory inventory = new Inventory(input);
        return getRecipe(inventory).map(recipe -> recipe.getCraftingResult(inventory));
    }




}
