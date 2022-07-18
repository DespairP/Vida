package teamHTBP.vida.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.util.ResourceLocation;
import teamHTBP.vida.datagen.recipes.AlterRecipes;
import teamHTBP.vida.datagen.recipes.ModGenRecipes;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author DustW
 **/
public class ModRecipes extends RecipeProvider {

    public ModRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected List<ModGenRecipes> recipes = new ArrayList<>();

    protected void addCustomRecipes() {
        recipes.add(new AlterRecipes());
    }

    protected void vanillaRecipes(Consumer<IFinishedRecipe> consumer) {
        //SimpleCookingRecipeBuilder.smelting()
        //        .unlockedBy("has_stone", has(Blocks.DIORITE)).save(consumer);
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        super.act(cache);

        recipes.forEach(recipes -> {
            recipes.getRecipes().forEach((name, entry) -> save(cache, name, entry));
        });
    }

    protected void save(DirectoryCache pCache, ResourceLocation name, Map.Entry<String, String> entry) {
        String json = entry.getKey();
        String subPath = entry.getValue();

        Path path = this.generator.getOutputFolder();

        saveRecipe(pCache, json, path.resolve("data/" + name.getNamespace() + "/recipes/" + subPath + "/" + name.getPath() + ".json"));
    }

    private static void saveRecipe(DirectoryCache pCache, String recipe, Path pPath) {
        try {
            String s1 = HASH_FUNCTION.hashUnencodedChars(recipe).toString();
            if (!Objects.equals(pCache.getPreviousHash(pPath), s1) || !Files.exists(pPath)) {
                Files.createDirectories(pPath.getParent());
                BufferedWriter bufferedwriter = Files.newBufferedWriter(pPath);

                try {
                    bufferedwriter.write(recipe);
                } catch (Throwable throwable1) {
                    try {
                        bufferedwriter.close();
                    } catch (Throwable throwable) {
                        throwable1.addSuppressed(throwable);
                    }

                    throw throwable1;
                }

                bufferedwriter.close();
            }

            pCache.recordHash(pPath, s1);
        } catch (IOException ignored) {
        }
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        addCustomRecipes();
        vanillaRecipes(consumer);
    }
}
