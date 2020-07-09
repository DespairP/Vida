package teamHTBP.vida;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import teamHTBP.vida.teamHTBP.vida.Item.ItemLoader;

/**
 * @version 0.0.1
 * @author DespairP
 * Credits： DespairP thousvillages
 * this is a description class of this mod
 * 这是一个mod介绍类（核心类）
 * **/
@Mod("vida")
public class Vida {

   //mod的ID字符串，用于材质包获取id，或其他用途
   public static final  String modId = "vida";


   //construct
   public Vida(){
    //TODO
      ItemLoader.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
   }


}
