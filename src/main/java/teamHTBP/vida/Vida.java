package teamHTBP.vida;

import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.block.BlockEventLoaderServer;
import teamHTBP.vida.block.BlockLoader;
import teamHTBP.vida.entity.EntityLoader;
import teamHTBP.vida.Item.ItemElementCoreVoid;
import teamHTBP.vida.Item.ItemLoader;
import teamHTBP.vida.TileEntity.TileEntityLoader;
import teamHTBP.vida.gui.GUI.ContainerTypeLoader;
import teamHTBP.vida.particle.ParticleLoader;
import teamHTBP.vida.recipe.OreReactionMachineRecipe;
import teamHTBP.vida.recipe.RecipeLoader;
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
   public  static  final Logger LOGGER = LogManager.getLogger();
   //mod的ID字符串，用于材质包获取id，或其他用途
   public static final  String modId = "vida";
   public static final ResourceLocation OREACTION = rl("orereaction_recipe");

   //construct
   public Vida(){
    //TODO
      ItemLoader.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
      BlockLoader.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
      ParticleLoader.PARTICLE.register(FMLJavaModLoadingContext.get().getModEventBus());
      GenLoader.FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
      TileEntityLoader.TILE_ENTITY_DEFERRED_REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
      EntityLoader.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
      ContainerTypeLoader.CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
      MinecraftForge.EVENT_BUS.register(ItemElementCoreVoid.class);
      MinecraftForge.EVENT_BUS.register(BlockEventLoaderServer.class);
      RecipeLoader.RECIPES.register(FMLJavaModLoadingContext.get().getModEventBus());
      registerType(OREACTION, OreReactionMachineRecipe.RECIPE_TYPE);
   }


   private static void registerType(ResourceLocation name, IRecipeType<?> recipeType) {
      Registry.register(Registry.RECIPE_TYPE, name, recipeType);
   }

   public static ResourceLocation rl(String path) {
      return new ResourceLocation(modId, path);
   }

}
