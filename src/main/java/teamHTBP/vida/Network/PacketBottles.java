package teamHTBP.vida.Network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;
import teamHTBP.vida.Item.armor.ItemArmorElementLegginsWithBottles;
import teamHTBP.vida.TileEntity.TileEntityPrismTable;

import java.util.List;
import java.util.function.Supplier;

public class PacketBottles {
    private int mode;
    public PacketBottles(PacketBuffer buffer){
        mode = buffer.readInt();
    }

    public PacketBottles(int mode){
        this.mode = mode;
    }

    public void toBytes(PacketBuffer buffer){
        buffer.writeInt(mode);
    }


    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(()->{
            PlayerEntity entity = ctx.get().getSender();
            ItemStack stack = entity.inventory.armorInventory.get(1);
            if(stack.getItem() instanceof ItemArmorElementLegginsWithBottles){
                ItemStack stack1 = ItemStack.EMPTY;
                CompoundNBT nbt = stack.getOrCreateTag();
                int progress = 0;
                  switch (this.mode){
                      case 1:
                          stack1 = ItemStack.read(nbt.getCompound("bottle1")); progress =nbt.getInt("bottle1Num");break;
                      case 2:
                          stack1 = ItemStack.read(nbt.getCompound("bottle2"));progress =nbt.getInt("bottle2Num");break;
                      case 3:
                          stack1 = ItemStack.read(nbt.getCompound("bottle3"));progress =nbt.getInt("bottle3Num");break;
                  }
                if(progress >= 100 && stack1 != ItemStack.EMPTY && !stack1.isEmpty()){
                 List<EffectInstance> list = PotionUtils.getEffectsFromStack(stack1);
                 for(EffectInstance instance : list){
                     entity.addPotionEffect(new EffectInstance(instance));
                 }
                    switch (this.mode){
                        case 1:
                            nbt.putInt("bottle1Num", progress - 100);break;
                        case 2:
                            nbt.putInt("bottle2Num", progress - 100);break;
                        case 3:
                            nbt.putInt("bottle3Num", progress - 100);break;
                    }

                }

               }
            }
        );
    }
}
