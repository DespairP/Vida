package teamHTBP.vida.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.network.NetworkEvent;
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;

import java.util.List;
import java.util.function.Supplier;

public class PacketBottles {
    private int mode = 0;

    /***/
    public PacketBottles(PacketBuffer buffer) {
        mode = buffer.readInt();
    }

    public PacketBottles(int mode) {
        this.mode = mode;
    }

    public void toBytes(PacketBuffer buffer) {
        buffer.writeInt(mode);
    }


    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
                    if (ctx.get().getSender() == null) return;
                    PlayerEntity entity = ctx.get().getSender();
                    ItemStack stack = entity.inventory.armorInventory.get(1);
                    if (stack.getItem() instanceof ItemArmorElementLegginsWithBottles) {
                        ItemStack stack1 = ItemStack.EMPTY;
                        CompoundNBT nbt = stack.getOrCreateTag();
                        int progress = 0;
                        switch (this.mode) {
                            case 1:
                                stack1 = ItemStack.read(nbt.getCompound("bottle1"));
                                progress = nbt.getInt("bottle1Num");
                                break;
                            case 2:
                                stack1 = ItemStack.read(nbt.getCompound("bottle2"));
                                progress = nbt.getInt("bottle2Num");
                                break;
                            case 3:
                                stack1 = ItemStack.read(nbt.getCompound("bottle3"));
                                progress = nbt.getInt("bottle3Num");
                                break;
                        }
                        if (progress >= 100 && stack1 != ItemStack.EMPTY && !stack1.isEmpty()) {
                            List<EffectInstance> list = PotionUtils.getEffectsFromStack(stack1);
                            for (EffectInstance instance : list) {
                                if (((ItemArmorElementLegginsWithBottles) stack.getItem()).element == 5)
                                    entity.addPotionEffect(new EffectInstance(instance.getPotion(), instance.getDuration() * 2, instance.getAmplifier()));
                                else
                                    entity.addPotionEffect(new EffectInstance(instance));
                            }
                            if (((ItemArmorElementLegginsWithBottles) stack.getItem()).element == 3) {
                                entity.addPotionEffect(new EffectInstance(Effect.get(10), 10));
                            }
                            switch (this.mode) {
                                case 1:
                                    nbt.putInt("bottle1Num", progress - 100);
                                    break;
                                case 2:
                                    nbt.putInt("bottle2Num", progress - 100);
                                    break;
                                case 3:
                                    nbt.putInt("bottle3Num", progress - 100);
                                    break;
                            }

                        }

                    }
                }
        );
        ctx.get().setPacketHandled(true);
    }
}
