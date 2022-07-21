package teamHTBP.vida.datagen.JsonGenerator.Properties;

import com.google.gson.Gson;
import teamHTBP.vida.datagen.JsonGenerator.impl.IBlockModelJsonWriter;
import teamHTBP.vida.datagen.JsonGenerator.impl.IBlockStateJsonWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class BlockModelProperties {
    private static final String STATE_PATH = "src\\main\\resources\\assets\\vida\\blockstates";
    private static final String MODEL_PATH = "src\\main\\resources\\assets\\vida\\models\\block";
    private static final String ITEM_PATH = "src\\main\\resources\\assets\\vida\\models\\item";
    IBlockStateJsonWriter jsonWriteState;
    IBlockModelJsonWriter jsonWriteModel;
    int generateModelJsonNumber = 1;
    private String blockName = "";


    public BlockModelProperties(String blockName, IBlockStateJsonWriter jsonWriteState, IBlockModelJsonWriter jsonWriteModel) {
        this.blockName = blockName;
        this.jsonWriteState = jsonWriteState;
        this.jsonWriteModel = jsonWriteModel;
    }

    public BlockModelProperties(String blockName, IBlockStateJsonWriter jsonWriteState, IBlockModelJsonWriter jsonWriteModel, int generateModelJsonNumber) {
        this.blockName = blockName;
        this.jsonWriteState = jsonWriteState;
        this.jsonWriteModel = jsonWriteModel;
        this.generateModelJsonNumber = generateModelJsonNumber;
    }


    public void writeBlockState(Gson gson) throws Exception {
        Map varients = jsonWriteState.getVariants(blockName);
        FileWriter writer = new FileWriter(STATE_PATH + "\\" + blockName + ".json");
        BlockStateJson blockStateJson = new BlockStateJson(varients);
        gson.toJson(blockStateJson, BlockStateJson.class, writer);
        writer.close();
    }

    public void writeBlockBasicModel(Gson gson) throws IOException {
        if (generateModelJsonNumber == 1) {
            BlockModelJson blockModelJson = new BlockModelJson(jsonWriteModel.getParent(), jsonWriteModel.getTextures(blockName));
            FileWriter writer = new FileWriter(MODEL_PATH + "\\" + blockName + ".json");
            gson.toJson(blockModelJson, BlockModelJson.class, writer);
            writer.close();
            return;
        }
        for (int i = 0; i < generateModelJsonNumber; i++) {
            BlockModelJson blockModelJson = new BlockModelJson(jsonWriteModel.getParent(), jsonWriteModel.getTextures(blockName + i));
            FileWriter writer = new FileWriter(MODEL_PATH + "\\" + blockName + i + ".json");
            gson.toJson(blockModelJson, BlockModelJson.class, writer);
            writer.close();
        }
    }

    public void writeItem(Gson gson) throws IOException {
        ItemModelJson itemModelJson = new ItemModelJson("vida:block/" + blockName, null);
        FileWriter writer = new FileWriter(ITEM_PATH + "\\" + blockName + ".json");
        gson.toJson(itemModelJson, ItemModelJson.class, writer);
        writer.close();
    }
}
