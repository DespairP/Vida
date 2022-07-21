package teamHTBP.vida.client.gui.components;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

/**上下文,当gui关闭时需要打开另一个gui并需要传递信息时,可以使用此类进行传递*/
public class GuidebookContext {
    /**储存,使用特有的nbt进行储存,可以直接代替Map*/
    public CompoundTag store = new CompoundTag();

    /**是否为空*/
    public boolean isStoreEmpty(){
        return store.isEmpty();
    }

    public int getInt(String key){
        return store.getInt(key);
    }

    public String getString(String key){
        return store.getString(key);
    }

    public static GuidebookContext empty(){
        return new GuidebookContext();
    }

    /**将context中的所有值复制到此context中,需要注意会覆盖原有的值*/
    public GuidebookContext merge(GuidebookContext fromContext){
        CompoundTag fromStore = fromContext != null ? fromContext.store : GuidebookContext.empty().store;
        // 复制不包括null的值进入到compound中
        for(String key : fromStore.getAllKeys()){
            Tag fromValue = fromStore.get(key);
            if(fromValue == null){ continue; }
            store.put(key, fromValue);
        }
        return this;
    }

}
