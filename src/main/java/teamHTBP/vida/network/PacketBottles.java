package teamHTBP.vida.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import teamHTBP.vida.item.armor.ItemArmorElementLegginsWithBottles;

import java.util.List;
import java.util.function.Supplier;

public class PacketBottles {
    private int mode = 0;

    /***/
    public PacketBottles(FriendlyByteBuf buffer) {
        mode = buffer.readInt();
    }

    public PacketBottles(int mode) {
        this.mode = mode;
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeInt(mode);
    }


    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
                    if (ctx.get().getSender() == null) return;
                    Player entity = ctx.get().getSender();
                    ItemStack stack = entity.inventory.armor.get(1);
                    if (stack.getItem() instanceof ItemArmorElementLegginsWithBottles) {
                        ItemStack stack1 = ItemStack.EMPTY;
                        CompoundTag nbt = stack.getOrCreateTag();
                        int progress = 0;
                        switch (this.mode) {
                            case 1:
                                stack1 = ItemStack.of(nbt.getCompound("bottle1"));
                                progress = nbt.getInt("bottle1Num");
                                break;
                            case 2:
                                stack1 = ItemStack.of(nbt.getCompound("bottle2"));
                                progress = nbt.getInt("bottle2Num");
                                break;
                            case 3:
                                stack1 = ItemStack.of(nbt.getCompound("bottle3"));
                                progress = nbt.getInt("bottle3Num");
                                break;
                        }
                        if (progress >= 100 && stack1 != ItemStack.EMPTY && !stack1.isEmpty()) {
                            List<MobEffectInstance> list = PotionUtils.getMobEffects(stack1);
                            for (MobEffectInstance instance : list) {
                                if (((ItemArmorElementLegginsWithBottles) stack.getItem()).element == 5)
                                    entity.addEffect(new MobEffectInstance(instance.getEffect(), instance.getDuration() * 2, instance.getAmplifier()));
                                else
                                    entity.addEffect(new MobEffectInstance(instance));
                            }
                            if (((ItemArmorElementLegginsWithBottles) stack.getItem()).element == 3) {
                                entity.addEffect(new MobEffectInstance(MobEffect.byId(10), 10));
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
