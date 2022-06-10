package teamHTBP.vida.helper.itemHelper;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import teamHTBP.vida.event.client.HoldItemClientTickHandler;

public class PlayerInventoryHelper {
    /**获取玩家某个部位的盔甲*/
    public static ItemStack getPlayerArmor(PlayerEntity entity, EquipmentSlotType part){
        return entity.inventory.armorInventory.get(part.getIndex());
    }

    /**获取玩家*/
    public static PlayerHoldData getPlayerHoldItem(){
        return HoldItemClientTickHandler.toData();
    }


}

