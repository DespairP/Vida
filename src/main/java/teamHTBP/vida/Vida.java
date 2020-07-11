package teamHTBP.vida;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import teamHTBP.vida.Block.BlockLoader;
import teamHTBP.vida.Item.ItemLoader;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.worldGen.GenLoader;


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
      BlockLoader.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
      ParticleLoader.PARTICLE.register(FMLJavaModLoadingContext.get().getModEventBus());
      GenLoader.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
   }


}
