package teamHTBP.vida.Capability;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.HashMap;

/**
 * 主要用于元素的计算
 * @Version 0.0.1
 * **/
public class ElementHelper {
    //五元素数值
    public final int ELEMENT_GOLD = 1;
    public final int ELEMENT_WOOD = 2;
    public final int ELEMENT_AQUA = 3;
    public final int ELEMENT_FIRE = 4;
    public final int ELEMENT_EARTH = 5;

    static HashMap<Item,mapItem> map = new HashMap<Item,mapItem>();

    public ElementHelper(){
        map.put(Items.STONE, new mapItem(1,10,"stone"));
        map.put(Items.DIAMOND, new mapItem(1,10000,"diamond"));
        map.put(Items.BEEF, new mapItem(3,10000,"diamond"));
        map.put(Items.BONE, new mapItem(3,10000,"diamond"));
        map.put(Items.DIRT, new mapItem(5,10000,"dirt"));
        map.put(Items.GOLD_INGOT, new mapItem(1,10000,"diamond"));
        map.put(Items.OAK_WOOD, new mapItem(2,10000,"diamond"));
        map.put(Items.NETHER_WART, new mapItem(4,10000,"diamond"));
    }

    //获得当前Containing
    public static int getContainingNum(ItemStack itemStack){
        if(map.containsKey(itemStack.getItem())) return map.get(itemStack.getItem()).containing*itemStack.getCount();
        return 0;
    }


    public static int getContainingElement(ItemStack itemStack){
        if(map.containsKey(itemStack.getItem())) return  map.get(itemStack.getItem()).element;
        return 0;
    }

}
class mapItem{
    //所处的元素
    int element = 0;
    //所含的元素值
    int containing = 0;
    //物品名字
    String name = "";
    mapItem(){

    }
    //注册物品对应的元素
    mapItem(int element,int containing,String name){
        this.element = element;
        this.containing = containing;
        this.name = name;
    }
}