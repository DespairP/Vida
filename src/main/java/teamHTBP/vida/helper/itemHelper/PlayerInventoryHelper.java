package teamHTBP.vida.helper.itemHelper;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.client.event.listener.HoldItemClientTickHandler;

public class PlayerInventoryHelper {
    /**获取玩家某个部位的盔甲*/
    public static ItemStack getPlayerArmor(Player entity, EquipmentSlot part){
        return entity.getInventory().armor.get(part.getIndex());
    }

    /**获取玩家*/
    public static PlayerHoldData getPlayerHoldItem(){
        return HoldItemClientTickHandler.toData();
    }


}

